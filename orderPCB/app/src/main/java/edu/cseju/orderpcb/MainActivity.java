package edu.cseju.orderpcb;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import edu.cseju.orderpcb.TestModel.TestClass;
import edu.cseju.orderpcb.model.PCBDetails;

/**
 * @author <h3>Abdul Mukit <br> Department of Computer Science and Engineering<br>
 *     Jahangirnagar University</h3>
 *
 * Main Activity is the launcher activity for this features.
 * It connects the layout with the program. Runs the logic over them.
 * make them interactive.
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private static final int REQUEST_PARAM = 5;

    RadioGroup rgLayers, rgMasking;
    RadioButton rbSingle, rbDouble, rbYes, rbNo;
    EditText edtWidth, edtHeight;
    TextView tvQuantity, tvCost, tvUploadStatus;
    Button btnSub, btnAdd, btnUploadFile, btnCart;
    ProgressBar progressBar;

    TestClass testClass = new TestClass();

    DatabaseReference databaseReference;
    StorageReference storageReference;
    private int mQuantity, mCost;
    private boolean isLayerSingle, isMaskingYes;
    private final boolean uploadStatus = false;
    private Uri fileUri;

    /**
     * Runs at the start of the activity. <br>
     * All the layout_view will be defined here.
     * initialize setup
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgLayers = findViewById(R.id.rgroupLayer);
        rgMasking = findViewById(R.id.rgroupMasking);
        rbSingle = findViewById(R.id.rbSingle);
        rbDouble = findViewById(R.id.rbDouble);
        rbYes = findViewById(R.id.rbYes);
        rbNo = findViewById(R.id.rbNo);
        edtHeight = findViewById(R.id.edtHeight);
        edtWidth = findViewById(R.id.edtWidth);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvCost = findViewById(R.id.tvCost);
        tvUploadStatus = findViewById(R.id.tvUploadStatus);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnCart = findViewById(R.id.btnCart);
        btnUploadFile = findViewById(R.id.btnUpload);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);

        rgMasking.setOnCheckedChangeListener(this);
        rgLayers.setOnCheckedChangeListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnUploadFile.setOnClickListener(this);
        btnCart.setOnClickListener(this);

        mCost = 0;
        databaseReference = FirebaseDatabase.getInstance().getReference("pcb");
        storageReference = FirebaseStorage.getInstance().getReference("pcb");

    }

    /**
     * A simple Toast show with given message
     * @param context   The contex where toast will be shown.
     * @param msg   Message which will display
     */
    void message(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method trigger when any radio_button in the radio_group is checked.
     * @param group         contain the radio_group which is checked
     * @param checkedId     the id of the button.
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == rgLayers) {
            switch (checkedId) {
                case R.id.rbSingle:
                    isLayerSingle = true;
                    break;
                case R.id.rbDouble:
                    isLayerSingle = false;
                    break;
            }
        } else if (group == rgMasking) {
            switch (checkedId) {
                case R.id.rbYes:
                    isMaskingYes = true;
                    break;
                case R.id.rbNo:
                    isMaskingYes = false;
                    break;
            }
        }
        //
    }


    /**
     * Trigger if any button is clicked.
     * @param v represent the button.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addItem();
                break;
            case R.id.btnSub:
                removeItem();
                break;
            case R.id.btnUpload:
                uploadSchematic();
                break;
            case R.id.btnCart:
                addToCart();
                break;
        }
        //
    }


    /**
     * when button add is clicked this method run <br>
     * it will add the pcb quantity for order, then show in the textview
     */
    private void addItem() {
        mQuantity = mQuantity + 1;
        tvQuantity.setText(String.valueOf(mQuantity));

        mCost = calculateCost(edtWidth.getText().toString(), edtHeight.getText().toString(), mQuantity);
        tvCost.setText(mCost + "");
    }

    /**
     * This method run to reduce an quantity of pcb ordered.
     * the quantity can not be negetive.
     */
    private void removeItem() {
        mQuantity = mQuantity - 1;
        if (testClass.checkNegetive(mQuantity) == true)
            mQuantity = 0;
        tvQuantity.setText(String.valueOf(mQuantity));

        mCost = calculateCost(edtWidth.getText().toString(), edtHeight.getText().toString(), mQuantity);
        tvCost.setText(mCost + "");
    }

    /**
     * This button send an intent to read pcb schematic file from android filestorage.
     * this intent is set for any type of file. <br>
     *     a reques_param constant is add to uniquely identify the received file
     */
    private void uploadSchematic() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_PARAM);
    }

    /**
     * If the schematic is successfully picked from storage, this method activate. <br>
     *     it match the request code with the parameter sent with intent to uniquely identify file.
     *     an Uri (Unique resource identifier) generate to hold the file.
     * @param requestCode The constant passed with intent
     * @param resultCode  check is file is fully received
     * @param data  contain the data of the file.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PARAM && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
            tvUploadStatus.setText("Upload Successful");
        }
    }

    /**
     * This method receive the file Uri and assume the extension type of the file.
     * @param fileUri   The Uri of the file
     * @return          String containing the extension type
     */
    public String getFileExtension(Uri fileUri)
    {
        ContentResolver contentResolver = getContentResolver();
        String details = contentResolver.getType(fileUri);
        return details.substring(details.indexOf('/') + 1);
    }

    /**
     * This method calculate cost required to place order pcb
     * @param width     pcb width
     * @param length    pcb length
     * @param quantity  no of pcb ordered
     * @return          Cost to place order
     */
    public int calculateCost(String width, String length, int quantity)
    {
        double wid = Double.parseDouble(width);
        double len = Double.parseDouble(length);

        double cost = wid * len * 20 * quantity;

        return (int) cost;
    }

    /**
     * This method run to place the order and save the order information into database (Firebase)
     * At first check if user properly complete the order process. <br>
     *     it generate an unique key to identify the order in database <br>
     *     A progressbar show the database store progress <br>
     *     if order successfully uploaded , success message given.
     *     if there is any problem uploading order, a toast is shown containing failure message.
     */
    private void addToCart() {

        if (rbSingle.isChecked() == false && rbDouble.isChecked() == false)
        {
            message(getApplicationContext(), "Select Layers type");
            return;
        }
        if (rbYes.isChecked() == false && rbNo.isChecked() == false)
        {
            message(getApplicationContext(), "Select Masking");
        }

        if (fileUri == null) {
            message(getApplicationContext(), "upload schemaic");
            return;
        }
        String fileExtension = getFileExtension(fileUri);

        String wid = edtWidth.getText().toString();
        String len = edtHeight.getText().toString();
        mCost = calculateCost(wid, len, mQuantity);
        tvCost.setText(mCost + "");

        String key = databaseReference.push().getKey();
        PCBDetails pcbDetails = new PCBDetails(key, isLayerSingle, isMaskingYes,
                                    Double.parseDouble(wid), Double.parseDouble(len), mQuantity);

        progressBar.setVisibility(View.VISIBLE);
        storageReference.child(key + fileExtension + "").putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println(uri.toString());

                        pcbDetails.setFileURL(uri.toString());
                        databaseReference.child(key).setValue(pcbDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                message(getApplicationContext(), "success");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                message(getApplicationContext(), e.getMessage());
                            }
                        });
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                message(getApplicationContext(), e.getMessage());
            }
        });



    }
}