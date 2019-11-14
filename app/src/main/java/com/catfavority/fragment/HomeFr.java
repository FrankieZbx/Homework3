package com.catfavority.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.catfavority.CatListBean;
import com.catfavority.acti.CatDes;
import com.catfavority.acti.MainActivity;
import com.catfavority.OkNet;
import com.catfavority.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HomeFr extends Fragment implements View.OnClickListener {

    private RecyclerView rvcontent;
    private EditText searchcontent;
    private Button bsearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView = View.inflate(getContext(), R.layout.fr_home, null);
        rvcontent = homeView.findViewById(R.id.rv_content);
        searchcontent = homeView.findViewById(R.id.searchip);
        bsearch = homeView.findViewById(R.id.bsearch);
        bsearch.setOnClickListener(this);
        initData();
        return homeView;
    }

    private void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvcontent.setLayoutManager(manager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bsearch:
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                String searchc = searchcontent.getText().toString().trim();
                if (TextUtils.isEmpty(searchc)){
                    Toast.makeText(getActivity(), "input is null", Toast.LENGTH_SHORT)
                            .show();
                }


                OkNet.getInstance().GetaDatasync("https://api.thecatapi.com/v1/images/search?breed_ids=" + searchc, new OkNet.AsyncCallback() {
                    @Override
                    public void onSucceedData(String result) {
                        try {
                            Type type = new TypeToken<List<CatListBean>>() {
                            }.getType();
                            final List<CatListBean> catlist = new Gson().fromJson(result, type);
                            if (catlist.size() == 0){
                                Toast.makeText(getActivity(), "Nofound", Toast.LENGTH_SHORT)
                                        .show();
                                return;
                            }
                            HomeFrRVAdapter catRVAdapter = new HomeFrRVAdapter(getContext(), catlist);
                            catRVAdapter.setonItemClickListner(new HomeFrRVAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int position) {
                                    Intent intent = new Intent(getContext(), CatDes.class);
                                    intent.putExtra("catdata", catlist.get(position));
                                    startActivity(intent);
                                }
                            });
                            rvcontent.setAdapter(catRVAdapter);

                        }catch (Exception e){
                            Toast.makeText(getActivity(), "Nofound", Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }

                    @Override
                    public void onFailed(int i, String error) {

                    }
                });
                break;
        }
    }
}
