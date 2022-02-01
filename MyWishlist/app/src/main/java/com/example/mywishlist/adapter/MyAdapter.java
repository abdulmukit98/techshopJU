package com.example.mywishlist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mywishlist.MainActivity;
import com.example.mywishlist.R;
import com.example.mywishlist.database.Wishlist;
import com.example.mywishlist.modal.List_Data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<List_Data>list_data;
    Context context;

    public MyAdapter(List<List_Data> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final List_Data ld=list_data.get(i);

        Picasso.with(context)
                .load(ld.getImageurl())
                .into(viewHolder.img);
        int id=ld.getId();
        viewHolder.tv.setText(ld.getName());
        if (MainActivity.myDatabase.wishListDao().isWish(id)==1)

            viewHolder.img_btn.setText("Remove");
        else {
            viewHolder.img_btn.setText("Add To Wishlist");
        }
        viewHolder.img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wishlist wishlist=new Wishlist();
                int id=ld.getId();
                String imageurl=ld.getImageurl();
                String nsme=ld.getName();
                wishlist.setId(id);
                wishlist.setImageid(imageurl);
                wishlist.setName(nsme);
                if (MainActivity.myDatabase.wishListDao().isWish(id)!=1){
                    viewHolder.img_btn.setText("Remove");
                    MainActivity.myDatabase.wishListDao().addTowishdata(wishlist);

                }else {
                    viewHolder.img_btn.setText("Add To Wishlist");
                    MainActivity.myDatabase.wishListDao().delete(wishlist);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        Button img_btn;
        private TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.image_view);
            img_btn=(Button)itemView.findViewById(R.id.img_btn);
            tv=(TextView)itemView.findViewById(R.id.tv_name);
        }
    }
}
