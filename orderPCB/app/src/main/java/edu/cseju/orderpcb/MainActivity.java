package edu.cseju.orderpcb;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.cseju.orderpcb.TestModel.TestClass;
import edu.cseju.orderpcb.controller.HelperClass;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private static final int REQUEST_PARAM = 5;

    RadioGroup rgLayers, rgMasking;
    RadioButton rbSingle, rbDouble, rbYes, rbNo;
    EditText edtWidth, edtHeight;
    TextView tvQuantity, tvCost, tvUploadStatus;
    Button btnSub, btnAdd, btnUploadFile, btnCart;
    TestClass testClass = new TestClass();
    HelperClass helperClass = new HelperClass();
    private int mQuantity, mCost;
    private boolean isLayerSingle, isMaskingYes;
    private final boolean uploadStatus = false;
    private Uri fileUri;

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

        rgMasking.setOnCheckedChangeListener(this);
        rgLayers.setOnCheckedChangeListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnUploadFile.setOnClickListener(this);
        btnCart.setOnClickListener(this);

    }

    void message(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

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


    private void addItem() {
        mQuantity = mQuantity + 1;
        tvQuantity.setText(String.valueOf(mQuantity));
    }

    private void removeItem() {
        mQuantity = mQuantity - 1;
        if (testClass.checkNegetive(mQuantity) == true)
            mQuantity = 0;
        tvQuantity.setText(String.valueOf(mQuantity));
    }

    private void uploadSchematic() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PARAM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PARAM && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
        }
    }

    private String getFileExtension(Uri fileUri)
    {
        ContentResolver contentResolver = getContentResolver();
        String details = contentResolver.getType(fileUri);
        return details.substring(details.indexOf('/') + 1);
    }

    private void addToCart() {
        if (fileUri == null) {
            message(getApplicationContext(), "upload schemaic");
            return;
        }
        String fileExtension = getFileExtension(fileUri);
        message(getApplicationContext(), fileExtension);
        if (!rgLayers.isClickable()){
            message(getApplicationContext(), "choose layer type");
        }

    }
}