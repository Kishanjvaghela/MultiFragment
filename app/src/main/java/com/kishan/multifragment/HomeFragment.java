package com.kishan.multifragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kishan.multfragment.BaseMultiFragment;
import com.kishan.multifragment.databinding.HomeFragmentBinding;

/**
 * Created by DSK02 on 12/18/2015.
 */
public class HomeFragment extends BaseMultiFragment {
    private HomeFragmentBinding mBinding;

    public static HomeFragment getFragment() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setFragmentTitle(R.string.home);
        setBackEnable(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_home, container, false);

        mBinding.viewpager.setAdapter(new HomePageAdapter(getChildFragmentManager()));
        mBinding.viewpagertab.setupWithViewPager(mBinding.viewpager);
        return mBinding.getRoot();
    }

    public class HomePageAdapter extends FragmentStatePagerAdapter {

        private String[] title = new String[]{"tab1", "tab2", "tab3"};

        public HomePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = DemoButtonFragment.get();
                    break;
                case 1:
                    fragment = DemoFragment.get(title[position], false);
                    break;
                case 2:
                    fragment = DemoFragment.get(title[position], false);
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }


    }
}
