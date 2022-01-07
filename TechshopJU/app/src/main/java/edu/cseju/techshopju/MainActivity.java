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

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private final int IMAGE_REQUEST = 5;
    Button mBtnUpload, mBtnChoose;
    EditText mEdtName, mEdtDescription, mEdtPrice;
    ImageView mImage;
    private Uri mImageUri;
    DatabaseReference mDatabaseReference;
    StorageReference mStorageReference;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.mBtnChoose:
                chooseImage();
                break;
            case R.id.mBtnUpload:
                uploadData();
                break;
        }
    }


    public void chooseImage()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null)
        {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).fit().into(mImage);
        }
    }


    private void uploadData()
    {
        String name = mEdtName.getText().toString().trim();
        if (name.isEmpty())
        {
            mEdtName.setError("insert name");
            mEdtName.requestFocus();
            return;
        }

        if (mEdtPrice.getText().toString().isEmpty())
        {
            mEdtPrice.setError("insert price");
            mEdtPrice.requestFocus();
            return;
        }
        int price = Integer.parseInt(mEdtPrice.getText().toString().trim());
        String description = mEdtDescription.getText().toString().trim();

        if (mImageUri == null)
        {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuDashboard:
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}