package tech.xana.androidskilltree.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


import com.tr4android.support.extension.widget.CircleImageView;

import java.util.List;

/**
 * Created by Xana Hopper on 2016-08-05.
 *
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonAdapter.CommonViewHolder> {

    protected List<T> mItems;
    protected Context mContext;
    protected boolean mAnimateItems = true;
    protected int mLastAnimatedPosition = -1;

    public CommonAdapter(Context context, List<T> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(getItemLayoutID(viewType), parent, false);
        CommonViewHolder viewHolder = new CommonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonAdapter.CommonViewHolder holder, int position) {
//        runEnterAnimation(holder.itemView, position);
        onBindDataToView(holder, mItems.get(position));
    }

    protected abstract void onBindDataToView(CommonViewHolder viewHolder, T item);

    public abstract int getItemLayoutID(int viewType);

    @Override
    public abstract int getItemViewType(int position);

    @Override
    public int getItemCount() {
        // TODO: Add Header and Footer
        return mItems.size();
    }

    public void add(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    private void runEnterAnimation(View view, int position) {
        if (!mAnimateItems) {
            return;
        }
//        if (position > mLastAnimatedPosition) {
//            mLastAnimatedPosition = position;
////            view.setTranslationY(ViewUtils.getScreenHeight(mContext));
//            view.animate()
//                    .translationY(50)
//                    .setStartDelay(100)
//                    .setInterpolator(new DecelerateInterpolator(3.f))
//                    .setDuration(300)
//                    .start();
//        }
    }

    protected void onItemClick(int position) {

    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {

        private final SparseArray<View> mViews;
        private View itemView;

        public CommonViewHolder(View itemView) {
            super(itemView);
            this.mViews = new SparseArray<>();
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick(getAdapterPosition());
                }
            });
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }

            return (T) view;
        }

        public void setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
        }

        public void setImage(int viewId, int resId) {
            ImageView iv = getView(viewId);
            iv.setImageResource(resId);
        }

        public void setPlachholder(int viewId, int resId) {
            CircleImageView civ = getView(viewId);
            civ.setPlaceholder(resId);
        }

        public void setPlachholder(int viewId, int resId, int colorId) {
            CircleImageView civ = getView(viewId);
            civ.setPlaceholder(resId, mContext.getResources().getColor(colorId));
        }

        public void setVisible(int viewId, int visibility) {
            View v = getView(viewId);
            v.setVisibility(visibility);
        }

        // TODO: More set methods
    }
}
