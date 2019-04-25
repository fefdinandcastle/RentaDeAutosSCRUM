package com.colorapps.code.test1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.colorapps.code.test1.Model.Album;
import com.colorapps.code.test1.R;

import java.util.ArrayList;

public class AlbumsAdapter extends BaseAdapter {

    private ArrayList<Album> albums;
    private Context context;
    private LayoutInflater thisInflater;

    public AlbumsAdapter(Context ctx, ArrayList<Album> albums){
        this.context=ctx;
        this.thisInflater=LayoutInflater.from(ctx);
        this.albums=albums;
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(     Context.LAYOUT_INFLATER_SERVICE );
            v=thisInflater.inflate(R.layout.albums_item,parent,false);
        }else {
            v = (View) convertView;
        }
        TextView textHeading=(TextView) v.findViewById(R.id.album_item_name);
        textHeading.setText(albums.get(position).getTitle());
        return v;
    }
}
