package com.catfavority.acti;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.catfavority.CatListBean;
import com.catfavority.FarCatData;
import com.catfavority.ImgUtils;
import com.catfavority.R;

import java.util.ArrayList;
import java.util.List;

public class CatDes extends BaseActivity implements View.OnClickListener {
    private ActionBar actionBar;
    private CatListBean catdata;
    private ImageView farimg;

    @Override
    protected int initLayout() {
        return R.layout.ac_catdes;
    }

    @Override
    protected void initData() {

        initActionBar();
        catdata = (CatListBean) getIntent().getSerializableExtra("catdata");
        TextView name = this.findViewById(R.id.name);
        TextView description = this.findViewById(R.id.description);
        TextView Weight = this.findViewById(R.id.Weight);
        TextView Temperament = this.findViewById(R.id.Temperament);
        TextView Origin = this.findViewById(R.id.Origin);
        TextView Lifespan = this.findViewById(R.id.Lifespan);
        TextView Wikipedialink = this.findViewById(R.id.Wikipedialink);
        TextView Dogfriendlinesslevel = this.findViewById(R.id.Dogfriendlinesslevel);
        ImageView catimg = this.findViewById(R.id.catimg);
        farimg = this.findViewById(R.id.farimg);


        name.setText("" + catdata.getBreeds().get(0).getName());
        description.setText("" + catdata.getBreeds().get(0).getDescription());

        Weight.setText("" + catdata.getBreeds().get(0).getWeight().toString());
        Temperament.setText("" + catdata.getBreeds().get(0).getTemperament());
        Origin.setText("" + catdata.getBreeds().get(0).getOrigin());
        Lifespan.setText("" + catdata.getBreeds().get(0).getLife_span());
        Wikipedialink.setText("" + catdata.getBreeds().get(0).getWikipedia_url());
        Dogfriendlinesslevel.setText("" + catdata.getBreeds().get(0).getDog_friendly());

        ImgUtils.setimgulr(this, catimg, catdata.getUrl());
//        setPicBitmap(mIvCat, "" + catdata.getUrl());

        farimg.setOnClickListener(this);
    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("SearchResult");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.farimg:
                List<CatListBean> listdata = FarCatData.catListData;
                if (listdata != null) {
//                    listdata.add(catdata);
                    for (int i = 0; i < listdata.size(); i++) {
                        if (listdata.get(i).getId().equals(catdata.getId())) {
                            farimg.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_mine_select));
                            Toast.makeText(CatDes.this, "Add Success", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            listdata.add(catdata);
                        }

                    }
                } else {
                    listdata = new ArrayList<>();
                    listdata.add(catdata);
                }

                farimg.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_mine_select));
                Toast.makeText(CatDes.this, "Add Success", Toast.LENGTH_SHORT)
                        .show();
                FarCatData.catListData = listdata;
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
