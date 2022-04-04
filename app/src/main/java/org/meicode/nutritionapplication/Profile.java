package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnInsulin;

        btnInsulin = (Button) findViewById(R.id.btnUpdateInsulin);

        DBHelper DB;
        DB = new DBHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String username = sharedPreferences.getString("Login", "null");
        String password = sharedPreferences.getString("password", "null");

        String insulinRetrieve = DB.retrieveInsulin(username);
        System.out.println(username);
        System.out.println(insulinRetrieve);

        editor.putString("insulin", insulinRetrieve).commit();
        System.out.println(sharedPreferences.getString("insulin", "no"));


        TextView usernameText = (TextView) findViewById(R.id.profileusername);
        usernameText.setText(username);
        TextView passwordText = (TextView) findViewById(R.id.profilepassword);
        passwordText.setText(password);
        TextView insulinText = (TextView) findViewById(R.id.profileinsulin);
        insulinText.setText(insulinRetrieve);

        String insulinUpdate = insulinRetrieve;


        btnInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isUpdate = DB.updateInsulin(usernameText.getText().toString(), insulinText.getText().toString());

                if(isUpdate == true)
                    Toast.makeText(Profile.this, "Data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Profile.this, "Data not updated", Toast.LENGTH_LONG).show();
            }

            //create onsubmit button functions
            //take value and update in database
            //update session storage with the new value
            //editor.putFloat("insulin", )
        });
    }
}