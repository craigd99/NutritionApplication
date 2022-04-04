package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.meicode.nutritionapplication.pojo.User;


public class LoginActivity extends AppCompatActivity {


    //Declare variables to be used in future functions below
    EditText username, password;
    Button btnlogin;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize the variables
        username= (EditText) findViewById(R.id.username1);
        password= (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);

        //Onclick method for the login button
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                User user = new User(username.getText().toString(),password.getText().toString());

            //If statement below checks that all fields have been filled in and if the credentials
                //match the database
            if(user.getUsername().equals("")||user.getPassword().equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB.checkusernamepassword(user.getUsername(), user.getPassword());


                if (checkuserpass==true){
                    editor.putString("Login",user.getUsername()).commit();
                    editor.putString("password", user.getPassword()).commit();
                    Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

    }
}