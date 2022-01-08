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

/**
 * The adaptar class to represent the recyclar view in DashboardActiviy <br>
 * It recieve a context - where the recyclar view is placed
 * and the Product list to Display
 * and an interface provided by Parent activity to perform specified action
 */
public class RecyclarViewAdaptar extends RecyclerView.Adapter<RecyclarViewHolder> {

    Context context;
    List<Product> products = new ArrayList<Product>();
    IRefresh iRefresh;

    /**
     * Constructor for adaptar
     *
     * @param context   parent context
     * @param products  List of Products to Display in recyclar view
     * @param iRefresh  Interface to perform some task delivered from outside the parent class.
     */
    public RecyclarViewAdaptar(Context context, List<Product> products, IRefresh iRefresh) {
        this.context = context;
        this.products = products;
        this.iRefresh = iRefresh;
    }

    /**
     * pair the adapter with individual recyclar_item <br>
     * map the resource file with viewholder
     *
     * @param parent        parent context that hold the recyclar view
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.dashboard_recyclar_item, parent, false);
        RecyclarViewHolder recyclarViewHolder = new RecyclarViewHolder(view, products, iRefresh);
        return recyclarViewHolder;
    }

    /**
     *  map data and image to each viewholder item
     *
     * @param holder    ViewHolder Item
     * @param position  index number
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclarViewHolder holder, int position) {
        holder.textViewName.setText(products.get(position).getProductName());
        Picasso.get().load(products.get(position).getProductImageLink())
                .placeholder(R.drawable.waiting).fit().into(holder.imageView);
        holder.textViewPrice.setText("$" + products.get(position).getProductPrice());

    }

    /**
     * size of products
     *
     * @return     total number of products that show in recyclar view
     */
    @Override
    public int getItemCount() {
        return products.size();
    }
}
