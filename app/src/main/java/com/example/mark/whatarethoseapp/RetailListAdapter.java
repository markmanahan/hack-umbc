package com.example.mark.whatarethoseapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class RetailListAdapter extends RecyclerView.Adapter<RetailListAdapter.ViewHolder> {
    private Context mContext;
    private List<RetailerInfo> list;

    public RetailListAdapter(Context mContext, List<RetailerInfo> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_retailer, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RetailerInfo retailerInfo = list.get(i);

        viewHolder.name.setText(retailerInfo.getTitle());
        viewHolder.thumbnail.setImageBitmap(retailerInfo.getImg());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Provides reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;
        public RelativeLayout parentLayout;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtView_itemRetailer_title);
            thumbnail = (ImageView) view.findViewById(R.id.imgView_itemRetailer_icon);
            parentLayout = (RelativeLayout) view.findViewById(R.id.parent_layout_itemRetailer);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
