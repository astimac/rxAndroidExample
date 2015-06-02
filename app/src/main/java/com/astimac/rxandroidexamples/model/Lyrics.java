package com.astimac.rxandroidexamples.model;

/**
 * Created by alex on 5/27/15.
 */
public class Lyrics {

    private String artist;

    private String song;

    private String lyrics;

    private String url;

    private int page_namespace;

    private int page_id;

    private String isOnTakedownList;

    public Lyrics() {

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage_namespace() {
        return page_namespace;
    }

    public void setPage_namespace(int page_namespace) {
        this.page_namespace = page_namespace;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public String getIsOnTakedownList() {
        return isOnTakedownList;
    }

    public void setIsOnTakedownList(String isOnTakedownList) {
        this.isOnTakedownList = isOnTakedownList;
    }

    @Override
    public String toString() {
        return "Lyrics{" +
                "artist='" + artist + '\'' +
                ", song='" + song + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", url='" + url + '\'' +
                ", page_namespace=" + page_namespace +
                ", page_id=" + page_id +
                ", isOnTakedownList='" + isOnTakedownList + '\'' +
                '}';
    }
}
