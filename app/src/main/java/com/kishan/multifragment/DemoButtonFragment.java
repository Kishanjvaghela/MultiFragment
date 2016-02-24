package com.kishan.multifragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kishan.multfragment.BaseMultiFragment;
import com.kishan.multifragment.databinding.DemoFragmentBinding;

/**
 * Created by DSK02 on 2/24/2016.
 */
public class DemoButtonFragment extends BaseMultiFragment {

    public static DemoButtonFragment get() {
        DemoButtonFragment fragment = new DemoButtonFragment();
        return fragment;
    }

    private DemoFragmentBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragmentTitle("Demo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_demo, container, false);
        mBinding.openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragmentForResult(123, DemoFragment.get("This back enable fragment", true));
            }
        });
        return mBinding.getRoot();
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle bundle) {
        super.onFragmentResult(requestCode, resultCode, bundle);
        if (requestCode == 123 && resultCode == DemoFragment.RESULT_CODE) {
            bundle.getString(DemoFragment.RESULT_ARG);
            mBinding.resultText.setText(bundle.getString(DemoFragment.RESULT_ARG));
        }
    }
}
