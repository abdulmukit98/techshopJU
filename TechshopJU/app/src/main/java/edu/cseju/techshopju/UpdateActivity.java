package edu.cseju.techshopju;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import edu.cseju.techshopju.model.DatabaseHelper;
import edu.cseju.techshopju.model.Product;

/**
 * @author <h1>Abdul Mukit <br>
 * Department of Computer Science of Engineering <br>
 * Jahangirnagar University </h1>
 *
 * This activity is exactly same as MainActivity, The main difference is it will update the product in
 * stead of adding a new one
 */
public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private final int IMG_REQ = 6;
    Product product;
    Button uBtnUpload, uBtnChoose;
    EditText uEdtName, uEdtDescription, uEdtPrice;
    ImageView uImage;
    DatabaseReference uDatabaseReference;
    StorageReference uStorageReference;
    ProgressBar uProgressBar;
    private Uri uImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        product = (Product) getIntent().getSerializableExtra("product");
        System.out.println(product.toString());

        uBtnChoose = findViewById(R.id.uBtnChoose);
        uBtnUpload = findViewById(R.id.uBtnUpload);
        uEdtName = findViewById(R.id.uEdtName);
        uEdtDescription = findViewById(R.id.uEdtDescription);
        uEdtPrice = findViewById(R.id.uEdtPrice);
        uImage = findViewById(R.id.uImageView);
        uBtnChoose.setOnClickListener(this);
        uBtnUpload.setOnClickListener(this);
        uProgressBar = findViewById(R.id.uProgress);
        uProgressBar.setVisibility(View.INVISIBLE);

        uDatabaseReference = FirebaseDatabase.getInstance().getReference("product");
        uStorageReference = FirebaseStorage.getInstance().getReference("product");

        uEdtName.setText(product.getProductName());
        uEdtPrice.setText(String.valueOf(product.getProductPrice()));
        uEdtDescription.setText(product.getProductDescription());
        Picasso.get().load(product.getProductImageLink()).fit().into(uImage);

        uImageUri = null;

    }

    /**
     * activate when a view is clicked
     * @param v represent the view id
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uBtnChoose:
                chooseImage();
                break;
            case R.id.uBtnUpload:
                updateProduct();
                break;
        }
    }

    /**
     * Exacty same as Main activity choose image <br>
     * But it will send a different request code to distinguish from previous one
     */
    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMG_REQ);
    }

    /**
     * Activate if a content is received to buffer
     *
     * @param requestCode      code send with choose intent
     * @param resultCode       check if file is completely taken
     * @param data             contain the file URI
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQ && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uImageUri = data.getData();
            Picasso.get().load(uImageUri).fit().into(uImage);
        }
    }

    /**
     * Update the information of a product <br>
     * if a discount offer is available- reduce price <br>
     * if description is changed <br>
     * if product image updated <br>
     *
     * receive the updated info from user. if Image is not uploaded by the use,
     * it use the previously give one, only update product details info
     * <br>
     * if image is changed, it store the updated image into firebase storage along with product detail.
     */

    private void updateProduct() {
        String name = uEdtName.getText().toString().trim();
        String description = uEdtDescription.getText().toString();
        if (name.isEmpty()) {
            uEdtName.setError("Insert name");
            uEdtName.requestFocus();
        }
        if (uEdtPrice.getText().toString().isEmpty()) {
            uEdtPrice.setError("Insert Price");
            uEdtPrice.requestFocus();
        }
        int price = Integer.parseInt(uEdtPrice.getText().toString());

        // No New image, use previous one
        if (uImageUri == null) {
            uProgressBar.setVisibility(View.VISIBLE);
            Product p = new Product(product.getProductId(), name, price, product.getProductImageLink(), description);
            uDatabaseReference.child(product.getProductId()).setValue(p).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    uProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    uProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (uImageUri != null) {
            Product p = new Product(product.getProductId(), name, price, null, description);
            DatabaseHelper helper = new DatabaseHelper(getApplicationContext(), product.getProductId(),
                    uImageUri, p, uDatabaseReference, uStorageReference, uProgressBar);
            helper.upload();
        }

    }

    /**
     * Used to redirect to Dashboard (Product Display) activity
     * @param menu          map with menu_resource file
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Trigger the menu which is clicked
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDashboard:
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}