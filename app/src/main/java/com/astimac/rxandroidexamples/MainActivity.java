package com.astimac.rxandroidexamples;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.astimac.rxandroidexamples.base.activity.BaseActivity;
import com.astimac.rxandroidexamples.ui.fragment.WelcomeFragment;

import butterknife.InjectView;


public class MainActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {

    @InjectView(R.id.my_awesome_toolbar)
    Toolbar mToolbar;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_toolbar_fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        if(savedInstanceState == null) {
            replace(R.id.fragment_container, WelcomeFragment.getInstance(), false);
        }

    }

    @Override
    public void onBackStackChanged() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            mToolbar.setTitle("RxAndroid Examples");
            mToolbar.setSubtitle("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
