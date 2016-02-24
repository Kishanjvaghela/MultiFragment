package com.kishan.multfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Stack;

/**
 * Created by DSK02 on 2/24/2016.
 */
public abstract class MultiFragActivity extends AppCompatActivity implements FragmentListener {
    private Stack<BaseMultiFragment> mFragmentStacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentStacks = new Stack<>();
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setBackButton(boolean isBack) {

    }

    @Override
    public void loadFragment(BaseMultiFragment fragment) {
        if (fragment != null) {
            hideSoftInputWindow();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(getContentId(), fragment);
            if (!mFragmentStacks.isEmpty()) {
                Fragment lastFragment = mFragmentStacks.lastElement();
                lastFragment.onPause();
                transaction.hide(lastFragment);
            }

            mFragmentStacks.push(fragment);
//            transaction.commit();
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        if (!onFragmentBack()) {
            super.onBackPressed();
        }
    }

    public boolean onFragmentBack() {
        hideSoftInputWindow();
        if (mFragmentStacks.size() > 1) {
            //first check the home is enable or not
            if (mFragmentStacks.lastElement().isBackEnable()) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                mFragmentStacks.lastElement().onPause();
                ft.remove(mFragmentStacks.pop());
                // show previous fragment
                BaseMultiFragment fragmentObject = mFragmentStacks.lastElement();

                /**
                 * We need to update the title before we show the fragment
                 * other wise the previous loaded fragment could not able to
                 * find the title view
                 */
                setTitle(fragmentObject.getTitle());
                setBackButton(fragmentObject.isBackEnable());
                fragmentObject.onResume();
                ft.show(fragmentObject);
//                ft.add(R.id.main_frame, fragmentObject.fragment, fragmentObject.fragment.getClass().toString());
                ft.commitAllowingStateLoss();
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public void hideSoftInputWindow() {
        if (getCurrentFocus() != null && getCurrentFocus() instanceof EditText) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected abstract int getContentId();

}
