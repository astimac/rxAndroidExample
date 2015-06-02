package com.astimac.rxandroidexamples.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.astimac.rxandroidexamples.MainActivity;
import com.astimac.rxandroidexamples.R;
import com.astimac.rxandroidexamples.adapter.AlbumAdapter;
import com.astimac.rxandroidexamples.api.LyricWikiApi;
import com.astimac.rxandroidexamples.base.fragment.BaseFragment;
import com.astimac.rxandroidexamples.model.Album;
import com.astimac.rxandroidexamples.model.Artist;

import java.util.concurrent.TimeUnit;

import butterknife.InjectView;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Func1;

/**
 * Created by alex on 5/26/15.
 */
public class WelcomeFragment extends BaseFragment implements AlbumAdapter.ItemClickListener {

    @InjectView(R.id.edit_text)
    EditText mEditText;

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private AlbumAdapter mAdapter;

    private Subscription mSubscription;

    public WelcomeFragment() {
        //empty
    }

    public static WelcomeFragment getInstance() {
        return new WelcomeFragment();
    }

    @Override
    protected int provideLayoutResource() {
        return R.layout.fragment_welcome;
    }

    @Override
    protected void bindUI(@NonNull View view) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AlbumAdapter();
        mAdapter.addItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Observable<OnTextChangeEvent> mSearchObsercable = WidgetObservable.text(mEditText, false);
        mSubscription = mSearchObsercable.debounce(500, TimeUnit.MILLISECONDS)
                .map(new Func1<OnTextChangeEvent, String>() {

                    @Override
                    public String call(OnTextChangeEvent onTextChangeEvent) {
                        return onTextChangeEvent.text().toString();
                    }
                })
                .flatMap(new Func1<String, Observable<Artist>>() {
                    @Override
                    public Observable<Artist> call(String s) {
                        return LyricWikiApi.getInstance().run().searchArtist(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Artist>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Artist artist) {
                        mAdapter.setArtist(artist.getArtist());
                        mAdapter.setAlbums(artist.getAlbums());
                    }
                });


    }

    @Override
    public void onDestroy() {
        if(mSubscription != null) {
            Log.i("tag", "unsubscribe");
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void onItemClick(View v, int position) {
        final Album mAlbum = mAdapter.getAlbum(position);
        final String mArist = mAdapter.getArtist();
        ((MainActivity) getActivity()).addFragment(R.id.fragment_container, SongListFragment.getInstance(mAlbum, mArist), true);
    }
}
