package com.astimac.rxandroidexamples.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astimac.rxandroidexamples.R;
import com.astimac.rxandroidexamples.model.Album;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alex on 5/27/15.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ItemHolder>{

    private final List<Album> mDataSource;

    private String mArtist;

    private ItemClickListener mClickListener;

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }

    public void addItemClickListener(ItemClickListener listener) {
        mClickListener = listener;
    }

    public AlbumAdapter() {
        mDataSource = new ArrayList<>();
    }

    public void setAlbums(List<Album> albums) {
        mDataSource.clear();
        mDataSource.addAll(albums);
        notifyDataSetChanged();
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getArtist() {
        return mArtist;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_album, viewGroup, false);

        return new ItemHolder(mView, mClickListener);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        final Album mAlbum = mDataSource.get(i);

        itemHolder.AlbumTitle.setText(mAlbum.getAlbum());
        itemHolder.AlbumYear.setText(mAlbum.getYear());
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public Album getAlbum(int position) {
        return position < getItemCount() ? mDataSource.get(position) : null;
    }

    static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.list_item_album_title)
        TextView AlbumTitle;

        @InjectView(R.id.list_item_album_year)
        TextView AlbumYear;

        private ItemClickListener mClickListener;

        public ItemHolder(View itemView, ItemClickListener mClickListener) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.mClickListener = mClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null) {
                mClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}
