package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Entry extends AppCompatActivity {


    //Declare variables to be used in future functions below

    EditText username, password, repassword, insulin;
    Button signup, signin, restaurant, barcode;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);


        //Initialize the variables
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        insulin = (EditText) findViewById(R.id.insulin);
        DB = new DBHelper(this);


        //Onclick method for the signup method
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String insulinvalue = insulin.getText().toString();


                //If statement controlling validation of the registration information
                //including if fields are empty, if the user exists
                //And conflicting user details
                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(Entry.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass,insulinvalue);
                            if (insert == true) {
                                Toast.makeText(Entry.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Entry.this, "Registration failed", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(Entry.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Entry.this, "Password Does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //Onclick method handling the sign in button

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}