package com.example.anna.neptis;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Implementazione efficiente dell'adapter(con uso della classe interna ViewHolder)
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
        return imageView;
    }

    //references to or images
    private Integer[]mThumbIds = {
            R.drawable.treasures,R.drawable.treasures,
            R.drawable.treasures,R.drawable.treasures,
            R.drawable.treasures,R.drawable.treasures,
            R.drawable.treasures,R.drawable.treasures
    };


    /*
    private class ViewHolder{
        public ImageView image;
        public String info;
        public String place;
    }*/

}