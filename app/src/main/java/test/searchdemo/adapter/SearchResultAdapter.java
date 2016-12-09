package test.searchdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import test.searchdemo.R;

/**
 * Created by whm on 16/12/8.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {

    private Activity activity;

    private List<String> resultData;

    public SearchResultAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchResultViewHolder(activity.getLayoutInflater().inflate(R.layout.view_search_result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.resultItem.setText(resultData.get(position));
    }

    @Override
    public int getItemCount() {
        return resultData.size();
    }

    public void setResultData(List<String> resultData) {
        this.resultData = resultData;
    }
}
