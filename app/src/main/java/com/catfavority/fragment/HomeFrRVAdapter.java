package com.catfavority.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.catfavority.CatListBean;
import com.catfavority.R;

import java.util.List;

public class HomeFrRVAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CatListBean> catListBean;

    public HomeFrRVAdapter(Context context, List<CatListBean> catListBean) {
        this.mContext = context;
        this.catListBean = catListBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item, viewGroup, false);
        return new CatItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((CatItemHolder) viewHolder).mTvCatname.setText(catListBean.get(i).getBreeds().get(0).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {

                    onItemClickListener.onClick(i);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return catListBean.size();
    }

    private class CatItemHolder extends RecyclerView.ViewHolder {

        private TextView mTvCatname;


        public CatItemHolder(final View itemView) {
            super(itemView);
            mTvCatname = (TextView) itemView.findViewById(R.id.rv_item_text);


        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setonItemClickListner(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
