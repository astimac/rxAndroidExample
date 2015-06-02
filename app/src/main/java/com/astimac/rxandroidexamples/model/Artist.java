package com.astimac.rxandroidexamples.model;

import java.util.List;

/**
 * Created by alex on 5/27/15.
 */
public class Artist {

    private String artist;

    private List<Album> albums;

    public Artist() {

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artist='" + artist + '\'' +
                ", albums=" + albums +
                '}';
    }
}
