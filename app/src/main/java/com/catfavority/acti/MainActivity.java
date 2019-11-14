package com.catfavority.acti;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.catfavority.FarCatData;
import com.catfavority.R;
import com.catfavority.fragment.FaFr;
import com.catfavority.fragment.HomeFr;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mainBottomeSwitcherContainer;
    private FrameLayout mainFragmentContainer;
    private List<Fragment> fragmentList;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mainFragmentContainer = findViewById(R.id.main_fragment_container);
        mainBottomeSwitcherContainer = findViewById(R.id.main_bottome_switcher_container);

        initFragment();
        initClick();
        View view = mainBottomeSwitcherContainer.getChildAt(0);
        onClick(view);
    }

    private void initClick() {

        int childCount = mainBottomeSwitcherContainer.getChildCount();

        for (int i = 0; i < childCount; i++) {

            View view = mainBottomeSwitcherContainer.getChildAt(i);
            view.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {

        int indexOfChild = mainBottomeSwitcherContainer.indexOfChild(v);


        changeUI(indexOfChild);
    }

    private void changeUI(int indexOfChild) {
        switchFragment(fragmentList.get(indexOfChild)).commit();
        for (int i = 0; i < mainBottomeSwitcherContainer.getChildCount(); i++) {
            View view = mainBottomeSwitcherContainer.getChildAt(i);
            if (i == indexOfChild) {

                setEnable(view, false);
            } else {
                setEnable(view, true);
            }
        }
    }

    private Fragment currentFragment = new Fragment();

    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.main_fragment_container, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    private void setEnable(View view, boolean b) {
        if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ((ViewGroup) view).getChildAt(i);
                childAt.setEnabled(b);
            }
        }
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new HomeFr());
        fragmentList.add(new FaFr());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FarCatData.catListData = null;
    }
}
