package com.astimac.rxandroidexamples.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by alex on 5/27/15.
 */
public class Album implements Parcelable {

    private String album;

    private String year;

    private String amazonLink;

    private ArrayList<String> songs;

    public Album() {

    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAmazonLink() {
        return amazonLink;
    }

    public void setAmazonLink(String amazonLink) {
        this.amazonLink = amazonLink;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album='" + album + '\'' +
                ", year='" + year + '\'' +
                ", amazonLink='" + amazonLink + '\'' +
                ", songs=" + songs +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.album);
        dest.writeString(this.year);
        dest.writeString(this.amazonLink);
        dest.writeSerializable(this.songs);
    }

    private Album(Parcel in) {
        this.album = in.readString();
        this.year = in.readString();
        this.amazonLink = in.readString();
        this.songs = (ArrayList<String>) in.readSerializable();
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
