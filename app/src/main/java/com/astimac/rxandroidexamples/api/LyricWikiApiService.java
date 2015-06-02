package com.astimac.rxandroidexamples.api;

import com.astimac.rxandroidexamples.model.Artist;
import com.astimac.rxandroidexamples.model.Lyrics;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by alex on 5/27/15.
 */
public interface LyricWikiApiService {

    @GET("/api.php?fmt=json")
    Observable<Artist> searchArtist(@Query("artist") String artistName);

    @GET("/api.php?fmt=realjson")
    Observable<Lyrics> getSongLyrics(@Query("artist") String artistName, @Query("song") String songName);
}
