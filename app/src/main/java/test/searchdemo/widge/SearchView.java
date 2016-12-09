package test.searchdemo.widge;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.searchdemo.R;
import test.searchdemo.adapter.SearchTipAdapter;

/**
 * Created by whm on 16/12/7.
 */

public class SearchView extends LinearLayout implements View.OnClickListener{

    private EditText editText;

    private ImageView clearSearchText;

    private Context mContext;

    private RecyclerView searchTips;

    private SearchTipAdapter searchTipAdapter;

    private SearchViewListener mListener;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.search_layout, this);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.search_input);
        clearSearchText = (ImageView) findViewById(R.id.search_delete);
        searchTips = (RecyclerView) findViewById(R.id.search_tips);

        searchTips.setLayoutManager(new LinearLayoutManager(mContext));

        clearSearchText.setOnClickListener(this);

        editText.setOnClickListener(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!"".equals(charSequence.toString())) {
                    clearSearchText.setVisibility(VISIBLE);
                    searchTips.setVisibility(VISIBLE);
                    if (searchTipAdapter != null && searchTips.getAdapter() != searchTipAdapter) {
                        searchTips.setAdapter(searchTipAdapter);
                    }

                    if (mListener != null) {
                        mListener.onRefreshSearchTip(charSequence + "");
                    }

                } else {
                    clearSearchText.setVisibility(GONE);
                    searchTips.setVisibility(GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchTips.setVisibility(GONE);
                    nofityStartSearching();
                }
                return true;
            }
        });
    }

    private void nofityStartSearching() {
        if (mListener != null) {
            mListener.onSearch(editText.getText().toString());
        }

        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_input:
                searchTips.setVisibility(VISIBLE);
                break;
            case R.id.search_delete:
                editText.setText("");
                searchTipAdapter.clearData();
                searchTipAdapter.notifyDataSetChanged();
                clearSearchText.setVisibility(GONE);
                break;
        }
    }

    public void setSearchTipAdapter(SearchTipAdapter searchTipAdapter) {
        this.searchTipAdapter = searchTipAdapter;
    }

    public void setSearchViewListener(SearchViewListener listener) {
        mListener = listener;
    }

    public interface SearchViewListener {
        void onRefreshSearchTip(String text);

        void onSearch(String text);
    }
}
