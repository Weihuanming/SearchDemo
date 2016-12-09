package test.searchdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import test.searchdemo.R;
import test.searchdemo.adapter.SearchResultAdapter;
import test.searchdemo.adapter.SearchTipAdapter;
import test.searchdemo.widge.SearchView;

public class MainActivity extends AppCompatActivity implements SearchView.SearchViewListener{

    private RecyclerView searchResult;

    private SearchView searchView;

    private SearchResultAdapter searchResultAdapter;

    private SearchTipAdapter searchTipAdapter;

    private List<String> testData;

    private List<String> searchTipData;

    private List<String> searchResultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        searchResult = (RecyclerView) findViewById(R.id.search_list);
        searchView = (SearchView) findViewById(R.id.search_view);

        searchResult.setLayoutManager(new LinearLayoutManager(this));

        searchView.setSearchTipAdapter(searchTipAdapter);
        searchView.setSearchViewListener(this);
    }

    private void initData() {
        getTestData();

        getSearchTipData(null);

        getSearchResultData(null);
    }

    private void getTestData() {
        int size = 100;
        testData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            testData.add("测试数据" + (i + 1));
        }
    }

    private void getSearchTipData(String text) {
        if (searchTipData == null) {
            searchTipData = new ArrayList<>();
        } else {
            searchTipData.clear();
            for (int i = 0; i < testData.size(); i++) {
                if (testData.get(i).contains(text.trim())) {
                    searchTipData.add(testData.get(i));
                }
            }
//            searchTipAdapter.tipData = searchTipData;
//            searchTipAdapter.notifyDataSetChanged();
        }
        if (searchTipAdapter == null) {
            searchTipAdapter = new SearchTipAdapter(this);
        } else {
            searchTipAdapter.setTipData(searchTipData);
            searchTipAdapter.notifyDataSetChanged();
        }
    }

    private void getSearchResultData(String text) {
        if (searchResultData == null) {
            searchResultData = new ArrayList<>();
        } else {
            searchResultData.clear();
            for (int i = 0; i < testData.size(); i++) {
                if (testData.get(i).contains(text.trim())) {
                    searchResultData.add(testData.get(i));
                }
            }
        }
        if (searchResultAdapter == null) {
            searchResultAdapter = new SearchResultAdapter(this);
        } else {
            searchResultAdapter.setResultData(searchResultData);
            searchResultAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefreshSearchTip(String text) {
        getSearchTipData(text);
    }

    @Override
    public void onSearch(String text) {
        getSearchResultData(text);
        searchResult.setVisibility(View.VISIBLE);
        if (searchResult.getAdapter() == null) {
            searchResult.setAdapter(searchResultAdapter);
        } else {
            searchResultAdapter.notifyDataSetChanged();
        }
    }
}