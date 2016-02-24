package com.kishan.multfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by DSK02 on 2/24/2016.
 */
public class BaseMultiFragment extends Fragment {
    private String title;
    private boolean backEnable;
    private FragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            listener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.getClass() + " Must implement FragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragmentTitle("");
        setBackEnable(false);
    }

    protected void setFragmentTitle(String title) {
        this.title = title;
        listener.setTitle(title);
    }

    protected void setFragmentTitle(int titleRes) {
        this.title = getString(titleRes);
        listener.setTitle(title);
    }

    public boolean isBackEnable() {
        return backEnable;
    }

    protected void setBackEnable(boolean backEnable) {
        this.backEnable = backEnable;
        listener.setBackButton(backEnable);
    }


    public String getTitle() {
        return title;
    }

    protected void loadFragment(BaseMultiFragment baseFragment) {
        listener.loadFragment(baseFragment);
    }
}
