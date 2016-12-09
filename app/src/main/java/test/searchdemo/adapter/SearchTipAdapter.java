package test.searchdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import test.searchdemo.R;

/**
 * Created by whm on 16/12/9.
 */

public class SearchTipAdapter extends RecyclerView.Adapter<SearchTipViewHolder> {

    private Activity activity;

    public List<String> tipData;

    public SearchTipAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public SearchTipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchTipViewHolder(activity.getLayoutInflater().inflate(R.layout.view_search_tip_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchTipViewHolder holder, int position) {
        holder.tipItem.setText(tipData.get(position));
    }

    @Override
    public int getItemCount() {
        return tipData.size();
    }

    public void setTipData(List<String> tipData) {
        this.tipData = tipData;
    }

    public void clearData() {
        tipData.clear();
    }
}