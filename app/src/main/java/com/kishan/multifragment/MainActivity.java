package com.kishan.multifragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kishan.multfragment.MultiFragActivity;
import com.kishan.multifragment.databinding.MainScreenBinding;

public class MainActivity extends MultiFragActivity {

    private MainScreenBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        loadFragment(HomeFragment.getFragment());
        resetTab(mBinding.tab3);
    }

    @Override
    protected int getContentId() {
        return R.id.main_content;
    }

    private void initView() {
        mBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBinding.tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tab1.isSelected()) {
                    loadFragment(DemoFragment.get("Fragment 1", false));
                    resetTab(mBinding.tab1);
                }
            }
        });
        mBinding.tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tab2.isSelected()) {
                    loadFragment(DemoButtonFragment.get());
                    resetTab(mBinding.tab2);
                }
            }
        });
        mBinding.tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tab3.isSelected()) {
                    loadFragment(HomeFragment.getFragment());
                    resetTab(mBinding.tab3);
                }
            }
        });
        mBinding.tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tab4.isSelected()) {
                    loadFragment(DemoFragment.get("Fragment 2", false));
                    resetTab(mBinding.tab4);
                }
            }
        });
        mBinding.tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tab5.isSelected()) {
                    loadFragment(DemoFragment.get("Fragment 3", false));
                    resetTab(mBinding.tab5);
                }
            }
        });
    }

    private void resetTab(Button imageButton) {
        mBinding.tab1.setSelected(false);
        mBinding.tab2.setSelected(false);
        mBinding.tab3.setSelected(false);
        mBinding.tab4.setSelected(false);
        mBinding.tab5.setSelected(false);
        imageButton.setSelected(true);
    }

    @Override
    public void setTitle(String title) {
        mBinding.title.setText(title.toUpperCase());
    }

    @Override
    public void setBackButton(boolean isBack) {
        super.setBackButton(isBack);
        mBinding.backButton.setVisibility(isBack ? View.VISIBLE : View.INVISIBLE);
    }
}
