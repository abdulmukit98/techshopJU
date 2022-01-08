package edu.cseju.techshopju;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.cseju.techshopju.interfaces.IRefresh;
import edu.cseju.techshopju.model.DatabaseHelper;
import edu.cseju.techshopju.model.Product;
import edu.cseju.techshopju.recyclar.RecyclarViewAdaptar;

public class DashboardActivity extends AppCompatActivity implements IRefresh {

    DatabaseReference dDatabaseReference;
    List<Product> products = new ArrayList<Product>();
    RecyclerView dRecyclerView;
    RecyclarViewAdaptar dRecyclarViewAdaptar;
    IRefresh iRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        iRefresh = this;
        dDatabaseReference = FirebaseDatabase.getInstance().getReference("product");
        dRecyclerView = findViewById(R.id.dashboardRecyclar);
        dRecyclarViewAdaptar = new RecyclarViewAdaptar(getApplicationContext(), products, iRefresh);
        dRecyclerView.setAdapter(dRecyclarViewAdaptar);

        dDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot single : snapshot.getChildren()) {
                    Product product = single.getValue(Product.class);
                    products.add(product);
                    dRecyclarViewAdaptar.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void RefreshActivity() {
        startActivity(getIntent());
    }

    @Override
    public void UpdateProduct(Product product) {
        Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }

}