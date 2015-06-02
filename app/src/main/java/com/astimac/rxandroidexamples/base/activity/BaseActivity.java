package com.astimac.rxandroidexamples.base.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.astimac.rxandroidexamples.R;

import butterknife.ButterKnife;

/**
 * Created by alex on 5/26/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int provideLayoutResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutResource());
        ButterKnife.inject(this);
    }

    protected void replace(int containerId, Fragment fragment, boolean addToBackstack) {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(containerId, fragment);
        if(addToBackstack) {
            mFragmentTransaction.addToBackStack(null);
        }
        mFragmentTransaction.commit();
    }

    public void addFragment(int containerId, Fragment fragment, boolean addToBackstack) {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.translate_left_in, R.anim.stay, R.anim.stay, R.anim.translate_right_out);
        mFragmentTransaction.add(containerId, fragment);
        if(addToBackstack) {
            mFragmentTransaction.addToBackStack(null);
        }
        mFragmentTransaction.commit();
    }
}
