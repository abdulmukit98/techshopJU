package edu.cseju.techshopju.recyclar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.cseju.techshopju.R;
import edu.cseju.techshopju.interfaces.IRefresh;
import edu.cseju.techshopju.model.Product;

public class RecyclarViewAdaptar extends RecyclerView.Adapter<RecyclarViewHolder> {

    Context context;
    List<Product> products = new ArrayList<Product>();
    IRefresh iRefresh;

    public RecyclarViewAdaptar(Context context, List<Product> products, IRefresh iRefresh) {
        this.context = context;
        this.products = products;
        this.iRefresh = iRefresh;
    }

    @NonNull
    @Override
    public RecyclarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.dashboard_recyclar_item, parent, false);
        RecyclarViewHolder recyclarViewHolder = new RecyclarViewHolder(view, products, iRefresh);
        return recyclarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclarViewHolder holder, int position) {
        holder.textViewName.setText(products.get(position).getProductName());
        Picasso.get().load(products.get(position).getProductImageLink())
                .placeholder(R.drawable.waiting).fit().into(holder.imageView);
        holder.textViewPrice.setText("$" + products.get(position).getProductPrice());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
