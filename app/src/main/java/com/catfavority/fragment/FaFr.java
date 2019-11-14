package com.catfavority.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.catfavority.CatListBean;
import com.catfavority.FarCatData;
import com.catfavority.R;

import java.util.List;

public class FaFr extends Fragment {

    private RecyclerView recyclerView;
    private TextView tv_haveorno;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frFaView = View.inflate(getContext(), R.layout.fr_fa, null);
        recyclerView = frFaView.findViewById(R.id.fa_rv);
        tv_haveorno = frFaView.findViewById(R.id.tv_haveorno);
        initRV();
        return frFaView;
    }

    private void initRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onResume() {
        super.onResume();
        List<CatListBean> listdata = FarCatData.catListData;
        if (listdata != null){
            tv_haveorno.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            FaFrRVAdapter faFrRVAdapter = new FaFrRVAdapter(getActivity(), listdata);
            recyclerView.setAdapter(faFrRVAdapter);
        }else {
            tv_haveorno.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }
    }
}
