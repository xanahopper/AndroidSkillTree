package tech.xana.androidskilltree.common.data;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import tech.xana.androidskilltree.R;
import tech.xana.androidskilltree.common.CommonAdapter;

/**
 * Created by Xana Hopper on 2016-08-05.
 */
public class DemoAdapter extends CommonAdapter<DemoItem> {
    public DemoAdapter(Context context, List<DemoItem> items) {
        super(context, items);

    }

    @Override
    protected void onItemClick(int position) {
        DemoItem item = mItems.get(position);
        Intent intent = new Intent(mContext, item.getActivityClass());
        mContext.startActivity(intent);
    }

    @Override
    protected void onBindDataToView(CommonViewHolder viewHolder, DemoItem item) {
        if (item.getIconId() == -1) {
            viewHolder.setPlachholder(R.id.item_icon, R.drawable.ic_unspecified_icon, R.color.textSecondary);
        } else {
            viewHolder.setPlachholder(R.id.item_icon, item.getIconId(), R.color.textSecondary);
        }
        viewHolder.setText(R.id.primary_text, item.getName());
        viewHolder.setText(R.id.secondary_text, item.getDescription());
        if (item.getInfo() == null) {
            viewHolder.setVisible(R.id.item_info, View.GONE);
        } else {
            viewHolder.setVisible(R.id.item_info, View.VISIBLE);
            viewHolder.setImage(R.id.item_info, R.drawable.ic_info_grey_400_18dp);
        }
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.demo_item;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
