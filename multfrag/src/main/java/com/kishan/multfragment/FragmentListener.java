package com.kishan.multfragment;


/**
 * Created by DSK02 on 12/18/2015.
 */
public interface FragmentListener {
    void setTitle(String title);

    void setBackButton(boolean isBack);

    void loadFragment(BaseMultiFragment fragment);

}
