package com.astimac.rxandroidexamples.api;

import retrofit.RestAdapter;

/**
 * Created by alex on 5/27/15.
 */
public class LyricWikiApi {

    private static final String END_POINT = "http://lyrics.wikia.com";

    private static LyricWikiApi sInstance;

    private final LyricWikiApiService mService;

    public static LyricWikiApi getInstance() {
        if(sInstance == null) {
            sInstance = new LyricWikiApi();
        }

        return sInstance;
    }

    public LyricWikiApiService run() {
        return mService;
    }

    private LyricWikiApi() {
        RestAdapter mAdapter = buildRestAdapter();
        mService = mAdapter.create(LyricWikiApiService.class);
    }

    private RestAdapter buildRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(LyricWikiApi.END_POINT)
                .build();
    }
}
