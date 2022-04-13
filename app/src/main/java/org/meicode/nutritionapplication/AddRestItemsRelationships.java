package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRestItemsRelationships extends AppCompatActivity {

    EditText restaurantID, restaurantFoodID;
    Button submitRelationship;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rest_items_relationships);

        restaurantID = (EditText) findViewById(R.id.AddRestaurantID);
        restaurantFoodID = (EditText) findViewById(R.id.AddRestaurantFoodID);
        submitRelationship = (Button) findViewById(R.id.submitRestaurantFoodRelation);

        DB = new DBHelper(this);


        submitRelationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int intRestaurantID = Integer.parseInt(restaurantID.getText().toString()) ;
                int intRestaurantFoodID =Integer.parseInt(restaurantFoodID.getText().toString()) ;


                if (restaurantID.equals("") ||restaurantFoodID.equals("")) {
                    Toast.makeText(AddRestItemsRelationships.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    DB.insertDataRestaurantFoodRelation(intRestaurantID, intRestaurantFoodID);
                    Toast.makeText(AddRestItemsRelationships.this, "Item successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}