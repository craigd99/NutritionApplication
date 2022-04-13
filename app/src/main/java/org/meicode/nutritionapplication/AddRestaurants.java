package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRestaurants extends AppCompatActivity {

    EditText restaurantName, restaurantSubcategory;
    DBHelper DB;
    Button submitRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurants);

        restaurantName = (EditText) findViewById(R.id.addRestaurantName);
        restaurantSubcategory = (EditText) findViewById(R.id.addRestaurantSubcategory);
        submitRestaurant = (Button) findViewById(R.id.submitRestaurants);

        DB = new DBHelper(this);


        submitRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strRestaurantName = restaurantName.getText().toString();
                String strRestaurantSubcategory = restaurantSubcategory.getText().toString();


                if (restaurantName.equals("") || restaurantSubcategory.equals("")) {
                    Toast.makeText(AddRestaurants.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    DB.insertDataRestaurants(strRestaurantName, strRestaurantSubcategory);
                    Toast.makeText(AddRestaurants.this, "Item successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}