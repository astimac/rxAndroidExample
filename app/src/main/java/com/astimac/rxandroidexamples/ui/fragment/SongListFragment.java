package com.astimac.rxandroidexamples.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.astimac.rxandroidexamples.MainActivity;
import com.astimac.rxandroidexamples.R;
import com.astimac.rxandroidexamples.adapter.SongAdapter;
import com.astimac.rxandroidexamples.base.fragment.BaseFragment;
import com.astimac.rxandroidexamples.model.Album;

import butterknife.InjectView;

/**
 * Created by alex on 5/27/15.
 */
public class SongListFragment extends BaseFragment implements SongAdapter.ItemClickListener {

    private static final String KEY_ALBUM = "key_album";

    private static final String KEY_ARTIST = "key_artist";

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private SongAdapter mAdapter;

    private Album mAlbum;

    private String mArtist;

    public SongListFragment() {

    }

    public static SongListFragment getInstance(Album album, String artist) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_ALBUM, album);
        args.putString(KEY_ARTIST, artist);

        SongListFragment mFragment = new SongListFragment();
        mFragment.setArguments(args);

        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlbum = getArguments() != null ? (Album) getArguments().getParcelable(KEY_ALBUM) : null;
        mArtist = getArguments() != null ? getArguments().getString(KEY_ARTIST) : null;
    }

    @Override
    protected int provideLayoutResource() {
        return R.layout.fragment_song_list;
    }

    @Override
    protected void bindUI(@NonNull View view) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SongAdapter();
        mAdapter.addItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mAlbum == null) {
            return;
        }

        final ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if(mActionBar != null) {
            mActionBar.setTitle(mAlbum.getAlbum());
            mActionBar.setSubtitle(mAlbum.getYear());
        }

        mAdapter.setSongs(mAlbum.getSongs());
    }

    @Override
    public void onItemClick(View v, int position) {
        final String mSong = mAdapter.getSong(position);
        ((MainActivity) getActivity()).addFragment(R.id.fragment_container, LyricsFragment.getInstance(mArtist, mSong), true);
    }
}
