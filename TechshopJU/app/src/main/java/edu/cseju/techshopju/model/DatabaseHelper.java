package edu.cseju.techshopju.model;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private final Context context;
    private final String key;
    private final Uri fileUri;
    private final Product product;
    private final DatabaseReference databaseReference;
    private final StorageReference storageReference;
    ProgressBar progressBar;


    // constructor for upload
    public DatabaseHelper(Context context, String key, Uri fileUri, Product product, DatabaseReference databaseReference, StorageReference storageReference, ProgressBar progressBar) {
        this.context = context;
        this.key = key;
        this.fileUri = fileUri;
        this.product = product;
        this.databaseReference = databaseReference;
        this.storageReference = storageReference;
        this.progressBar = progressBar;
    }

    public String getExtentionType(Uri fileUri) {
        ContentResolver contentResolver = context.getContentResolver();
        String extension = contentResolver.getType(fileUri);
        return extension.substring(extension.indexOf('/') + 1);
    }


    public void upload() {
        progressBar.setVisibility(View.VISIBLE);
        storageReference.child(key + "." + getExtentionType(fileUri)).putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String httpUrl = uri.toString();
                                product.setProductImageLink(httpUrl);
                                databaseReference.child(key).setValue(product)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(context, "Database Failure" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Image Failure" + e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }


}
