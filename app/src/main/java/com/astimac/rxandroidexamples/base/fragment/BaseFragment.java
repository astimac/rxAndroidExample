package com.astimac.rxandroidexamples.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astimac.rxandroidexamples.application.RxAndroidExampleApplication;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * Created by alex on 5/26/15.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract int provideLayoutResource();

    protected abstract void bindUI(@NonNull View view);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(provideLayoutResource(), container, false);
        ButterKnife.inject(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindUI(view);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = RxAndroidExampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
