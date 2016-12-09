package test.searchdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import test.searchdemo.R;

/**
 * Created by whm on 16/12/9.
 */

public class SearchTipViewHolder extends RecyclerView.ViewHolder {

    public TextView tipItem;

    public SearchTipViewHolder(View itemView) {
        super(itemView);
        tipItem = (TextView) itemView.findViewById(R.id.search_tip_item);
    }
}
