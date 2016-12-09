package test.searchdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import test.searchdemo.R;

/**
 * Created by whm on 16/12/8.
 */

public class SearchResultViewHolder extends RecyclerView.ViewHolder {

    public TextView resultItem;

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        resultItem = (TextView) itemView.findViewById(R.id.search_content);
    }
}
