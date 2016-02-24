package com.kishan.multifragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kishan.multfragment.BaseMultiFragment;
import com.kishan.multifragment.databinding.DummyFragmentBinding;


/**
 * Created by DSK02 on 2/24/2016.
 */
public class DemoFragment extends BaseMultiFragment {
    private static final String ARG_TEXT = "text";
    private static final String ARG_BACK = "back";

    public static DemoFragment get(String text, boolean back) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        bundle.putBoolean(ARG_BACK, back);
        DemoFragment fragment = new DemoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private String text;
    private DummyFragmentBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString(ARG_TEXT);
        setFragmentTitle(text);
        setBackEnable(getArguments().getBoolean(ARG_BACK));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_dummy, container, false);
        mBinding.text.setText(text);
        return mBinding.getRoot();
    }
}
