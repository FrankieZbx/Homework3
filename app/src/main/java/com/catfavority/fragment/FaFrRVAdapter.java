package com.catfavority.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.catfavority.CatListBean;
import com.catfavority.ImgUtils;
import com.catfavority.R;

import java.util.List;

public class FaFrRVAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CatListBean> catListBean;

    public FaFrRVAdapter(Context context, List<CatListBean> catListBean) {
        this.mContext = context;
        this.catListBean = catListBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.favor_item, viewGroup, false);
        return new CatItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((CatItemHolder) viewHolder).catname.setText(catListBean.get(i).getBreeds().get(0).getName());
        ImgUtils.setimgulr((Activity) mContext,((CatItemHolder) viewHolder).catimg,catListBean.get(i).getUrl());
    }

    @Override
    public int getItemCount() {
        return catListBean.size();
    }

    private class CatItemHolder extends RecyclerView.ViewHolder {

        private ImageView catimg;
        private TextView catname;


        public CatItemHolder(final View itemView) {
            super(itemView);
            catimg = (ImageView) itemView.findViewById(R.id.catimg);
            catname = (TextView) itemView.findViewById(R.id.catname);


        }
    }

}
