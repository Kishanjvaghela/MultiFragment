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
    private int reqCode;
    private int resCode;
    private Bundle resultBundle;
    private int enterStart = -1, enterEnd = -1, exitStart = -1, exitEnd = -1;

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

    protected void setEnterAnimation(int start, int end) {
        this.enterStart = start;
        this.enterEnd = end;
    }

    protected void setExitAnimation(int start, int end) {
        this.exitStart = start;
        this.exitEnd = end;
    }

    public int getEnterStart() {
        return enterStart;
    }

    public int getEnterEnd() {
        return enterEnd;
    }

    public int getExitStart() {
        return exitStart;
    }

    public int getExitEnd() {
        return exitEnd;
    }

    public String getTitle() {
        return title;
    }

    protected void loadFragment(BaseMultiFragment baseFragment) {
        listener.loadFragment(baseFragment);
    }

    public void setReqCode(int reqCode) {
        this.reqCode = reqCode;
    }

    public void setFragmentResult(int resCode, Bundle resultBundle) {
        this.resCode = resCode;
        this.resultBundle = resultBundle;
    }

    public Bundle getResultBundle() {
        return resultBundle;
    }

    public int getReqCode() {
        return reqCode;
    }

    public int getResCode() {
        return resCode;
    }

    protected void loadFragmentForResult(int requestCode, BaseMultiFragment fragment) {
        listener.loadFragmentForResult(requestCode, fragment);
    }

    protected void onFragmentResult(int requestCode, int resultCode, Bundle bundle) {

    }

    protected void onBack() {
        listener.onBack();
    }
}
