package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRestaurantItems extends AppCompatActivity {
    EditText restaurantName, restaurantItemName, restaurantItemCategory, restaurantItemCarbohydrates;
    Button submitRestaurantItem;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant_items);



        restaurantName = (EditText) findViewById(R.id.AddRestaurantName);
        restaurantItemName = (EditText) findViewById(R.id.AddRestaurantItemName);
        restaurantItemCategory = (EditText) findViewById(R.id.AddRestaurantItemCategory);
        restaurantItemCarbohydrates = (EditText) findViewById(R.id.AddRestaurantItemCarbohydrates);
        submitRestaurantItem = (Button) findViewById(R.id.SubmitRestaurantItem);
        DB = new DBHelper(this);

        submitRestaurantItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strRestaurantName = restaurantName.getText().toString();
                String strRestaurantItemName = restaurantItemName.getText().toString();
                String strRestaurantItemCategory = restaurantItemCategory.getText().toString();
                Double intRestaurantItemCarbohydrates = Double.parseDouble(restaurantItemCarbohydrates.getText().toString());

                if (restaurantItemName.equals("") || restaurantItemName.equals("") ||
                        restaurantItemCategory.equals("") || restaurantItemCarbohydrates.equals("")) {
                    Toast.makeText(AddRestaurantItems.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    DB.insertDataRestaurants(strRestaurantName, strRestaurantItemName, strRestaurantItemCategory, intRestaurantItemCarbohydrates);
                    Toast.makeText(AddRestaurantItems.this, "Item successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}