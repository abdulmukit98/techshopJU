package edu.cseju.techshopju.recyclar;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import edu.cseju.techshopju.R;
import edu.cseju.techshopju.interfaces.IRefresh;
import edu.cseju.techshopju.model.Product;

public class RecyclarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {

    TextView textViewName, textViewPrice;
    ImageView imageView;
    List<Product> products = new ArrayList<Product>();
    IRefresh iRefresh;

    public RecyclarViewHolder(@NonNull View itemView, List<Product> products, IRefresh iRefresh) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.driName);
        imageView = itemView.findViewById(R.id.driImage);
        textViewPrice = itemView.findViewById(R.id.driPrice);
        this.products = products;
        this.iRefresh = iRefresh;

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //System.out.println(products.get(getAdapterPosition()).toString());
    }

    @Override
    public boolean onLongClick(View v) {
        PopupMenu popupMenu = new PopupMenu(itemView.getContext(), v);
        popupMenu.inflate(R.menu.recyclar_popup);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.popup_update:
                iRefresh.UpdateProduct(products.get(getAdapterPosition()));
                break;
            case R.id.popup_delete:
                deleteItem();
                iRefresh.RefreshActivity();
                break;
        }
        return false;
    }


    private void deleteItem() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(products.get(getAdapterPosition()).getProductImageLink());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("product");
        databaseReference.child(products.get(getAdapterPosition()).getProductId()).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(itemView.getContext(), "removed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(itemView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
