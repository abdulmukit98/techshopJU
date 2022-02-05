package com.example.text;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.text.testModel.TestClass;

public class MainActivity extends AppCompatActivity {

    private EditText name, email, phone, add1, add2, post, quantity, code;
    private Button button, payment;
    private TextView textView, check;
    private Boolean VALIDATOR = true;

    AlertDialog.Builder builder;
    //TestClass testClass = new TestClass();

    Button payment1, payment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText) findViewById(R.id.name_id);
        email = (EditText) findViewById(R.id.email_id);
        phone = (EditText) findViewById(R.id.phone_id);
        add1 = (EditText) findViewById(R.id.add1_id);
        add2 = (EditText) findViewById(R.id.add2_id);
        post = (EditText) findViewById(R.id.post_id);
        quantity = (EditText) findViewById(R.id.quantity_id);
        code = (EditText) findViewById(R.id.code_id);
        textView = (TextView) findViewById(R.id.textView_id);
        check = (TextView) findViewById(R.id.isCorrect);

        button = (Button) findViewById(R.id.button_id);
        payment = findViewById(R.id.payment);
        builder = new AlertDialog.Builder(getApplicationContext());

        payment1 = findViewById(R.id.payment1);
        payment2 = findViewById(R.id.payment2);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String name1 = name.getText().toString();
                String emaill = email.getText().toString();
                String phonee = phone.getText().toString();
                String add11 = add1.getText().toString();
                String add22 = add2.getText().toString();
                String postt = post.getText().toString();
                String quantityy = quantity.getText().toString();
                String codee = code.getText().toString();


                if (codee.isEmpty()) {
                //if (testClass.msgCheck(codee)) {
                    code.setError("Product Code...?");
                    code.requestFocus();
                    isValid(false);
                }

                if (quantityy.isEmpty()) {
               // if (testClass.msgCheck(quantityy)) {
                    quantity.setError("Number of Products?");
                    quantity.requestFocus();
                    isValid(false);

                }

                if (add11.isEmpty()) {
                //if (testClass.msgCheck(add11)) {
                    add1.setError("Your Address");
                    add1.requestFocus();
                    isValid(false);
                }

                if(phonee.isEmpty()){
                //if (testClass.msgCheck(phonee)) {
                    phone.setError("Your Phone Number Please");
                    phone.requestFocus();
                    isValid(false);
                }

                if (emaill.isEmpty()) {
                //if (testClass.msgCheck(emaill)) {
                    email.setError("Please provide an email");
                    email.requestFocus();
                    isValid(false);

                }

                if (name1.isEmpty()) {
                //if (testClass.msgCheck(name1)) {
                    name.setError("Enter your name");
                    name.requestFocus();
                    isValid(false);


                }

                if (VALIDATOR) {
                    textView.setText("Name:   " + name1 + "\nEmail:  " + emaill + "\nPhone:  " + phonee + "\nAddress_1:  " + add11 + "\nAddress_2:  " + add22 + "\nPostal Code:  " + postt + "\nQuantity of Product:  " + quantityy + "\nProduct Code no :  " + codee);
                    findViewById(R.id.isCorrect).setVisibility(View.VISIBLE);
                    findViewById(R.id.textView_id).setVisibility(View.VISIBLE);
                    payment1.setVisibility(View.VISIBLE);
                    payment2.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_SHORT).show();

                }


                else {
                    findViewById(R.id.isCorrect).setVisibility(View.GONE);
                    findViewById(R.id.textView_id).setVisibility(View.GONE);
                    payment1.setVisibility(View.GONE);
                    payment2.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Not Valid", Toast.LENGTH_SHORT).show();
                    VALIDATOR = true;
                }


            }
        });


        payment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Payment.class);
                startActivity(intent);
            }
        });

        payment1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Payment.class);
                startActivity(intent);
            }
        });

        payment2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name.requestFocus();
                name.setError("Start again");

            }
        });


    }



    public void isValid(Boolean validation) {
        VALIDATOR = validation;
    }

}