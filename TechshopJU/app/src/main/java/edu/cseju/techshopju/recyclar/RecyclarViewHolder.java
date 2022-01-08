package edu.cseju.techshopju.recyclar;

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

/**
 * Represent Each View_item in the Recyclar_view
 */
public class RecyclarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {

    TextView textViewName, textViewPrice;
    ImageView imageView;
    List<Product> products = new ArrayList<Product>();
    IRefresh iRefresh;

    /**
     * Constructor for viewholder item
     * @param itemView      hold the data of view holder itself
     * @param products      the list of products passed from adaptar view for further manipulation
     * @param iRefresh      send from parent context to Perform some task in the Dashboard activity
     */
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

    /**
     * Trigger if view_item is clicked
     * @param v     contain view information
     */
    @Override
    public void onClick(View v) {
        //System.out.println(products.get(getAdapterPosition()).toString());
    }

    /**
     * Trigger if view_item is pressed long time <br>
     * it will invoke a popup menu to update or delete the item
     * @param v     the view item which clicked
     * @return
     */
    @Override
    public boolean onLongClick(View v) {
        PopupMenu popupMenu = new PopupMenu(itemView.getContext(), v);
        popupMenu.inflate(R.menu.recyclar_popup);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
        return false;
    }

    /**
     * when a manu_item of the popup menu , UPDATE/ DELETE is clicked , this method trigger
     * @param item      contain menu info
     * @return
     */
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

    /**
     * Execute if <b>xDELETE<br> item from the popup menu is clicked <br>
     * This remove the Product item from firebase along with the product Image
     *
     */
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
