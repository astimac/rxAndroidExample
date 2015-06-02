package com.astimac.rxandroidexamples.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.astimac.rxandroidexamples.R;
import com.astimac.rxandroidexamples.base.fragment.BaseFragment;

import butterknife.InjectView;

/**
 * Created by alex on 5/27/15.
 */
public class LyricsFragment extends BaseFragment {

    private static final String KEY_ARTIST_NAME = "key_artist_name";
    private static final String KEY_SONG_NAME = "key_song_name";

    private String mArtistName;

    private String mSongName;

    @InjectView(R.id.lyrics_text_view)
    TextView mLyricsTextView;

    public LyricsFragment() {

    }

    public static LyricsFragment getInstance(String artistName, String songName) {
        Bundle args = new Bundle();
        args.putString(KEY_ARTIST_NAME, artistName);
        args.putString(KEY_SONG_NAME, songName);

        LyricsFragment mFragment = new LyricsFragment();
        mFragment.setArguments(args);

        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtistName = getArguments() != null ? getArguments().getString(KEY_ARTIST_NAME) : null;
        mSongName = getArguments() != null ? getArguments().getString(KEY_SONG_NAME) : null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if(mActionBar != null) {
            mActionBar.setTitle(mArtistName);
            mActionBar.setSubtitle(mSongName);
        }
    }

    @Override
    protected int provideLayoutResource() {
        return R.layout.fragment_lyrics;
    }

    @Override
    protected void bindUI(@NonNull View view) {

    }
}
