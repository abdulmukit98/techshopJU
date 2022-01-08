package edu.cseju.techshopju;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import edu.cseju.techshopju.model.DatabaseHelper;
import edu.cseju.techshopju.model.Product;

/**
 * @author <h1>Abdul Mukit <br> Department of Computer Science of Engineering <br>
 * Jahangirnagar University</h1>
 *
 * <br> <br>
 * Main Activity is the launcher acticity that initiate that program. <br>
 * It is designed for Creating Product. <br>
 * <b>Only Admin can access this activity </b>
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int IMAGE_REQUEST = 5;
    Button mBtnUpload, mBtnChoose;
    EditText mEdtName, mEdtDescription, mEdtPrice;
    ImageView mImage;
    DatabaseReference mDatabaseReference;
    StorageReference mStorageReference;
    ProgressBar mProgressBar;
    private Uri mImageUri;

    /**
     * This method execute at the begining of the activity.<br>
     * All the component designed in the layout resource file need to bind java format to access <br>
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnChoose = findViewById(R.id.mBtnChoose);
        mBtnUpload = findViewById(R.id.mBtnUpload);
        mEdtName = findViewById(R.id.mEdtName);
        mEdtDescription = findViewById(R.id.mEdtDescription);
        mEdtPrice = findViewById(R.id.mEdtPrice);
        mImage = findViewById(R.id.mImageView);
        mBtnChoose.setOnClickListener(this);
        mBtnUpload.setOnClickListener(this);
        mProgressBar = findViewById(R.id.mProgress);
        mProgressBar.setVisibility(View.INVISIBLE);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("product");
        mStorageReference = FirebaseStorage.getInstance().getReference("product");

    }

    /**
     * Activate if OnclickListener is added to a view and the view is clicked.
     *
     * @param v return the view which is clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtnChoose:
                chooseImage();
                break;
            case R.id.mBtnUpload:
                uploadData();
                break;
        }
    }

    /**
     * This method start an intent that used to open storage the android device <br>
     * Intnet.ACTION_GET_CONTENT invoke to get a content from storage
     * A request code need to attach to uniquely identify file
     */
    public void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    /**
     * After choosing a file from storage this method execute to receive it
     * data.getData() contain a URI that is the mapping of the file.
     *
     * @param requestCode   Uniquely identify the file
     * @param resultCode    Check if full content received or not
     * @param data          hold the content of the file
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).fit().into(mImage);
        }
    }

    /**
     * This gather all the Product information inputted by user as <br>
     * product name, price, Image URI that contain the image data, and product description.
     * <br>
     * It then save the information in the <b>Product</b> class object <br>
     * Finally it call DatabaseHelper to upload the product in firebase databasse
     *
     */

    public void uploadData() {
        String name = mEdtName.getText().toString().trim();
        if (name.isEmpty()) {
            mEdtName.setError("insert name");
            mEdtName.requestFocus();
            return;
        }

        if (mEdtPrice.getText().toString().isEmpty()) {
            mEdtPrice.setError("insert price");
            mEdtPrice.requestFocus();
            return;
        }
        int price = Integer.parseInt(mEdtPrice.getText().toString().trim());
        String description = mEdtDescription.getText().toString().trim();

        if (mImageUri == null) {
            Toast.makeText(getApplicationContext(), "Choose Image First", Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);
        String key = mDatabaseReference.push().getKey();
        Product product = new Product(key, name, price, null, description);

        DatabaseHelper helper = new DatabaseHelper(getApplicationContext(), key,
                mImageUri, product, mDatabaseReference, mStorageReference, mProgressBar);

        helper.upload();


    }

    /**
     * This is menu method that used to go to Dashboard activity
     *
     * @param menu     this menu is mapped with R.menu.main_menu resource file
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * When a menu item is clicked this method activate to perform action
     * @param item  represents the data of which manu_item is selected.
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