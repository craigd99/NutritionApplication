package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button profileButton, restaurantButton, barcodeButton, addItemRedirect, addRestaurantItemRedirect,
            restaurantItemSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profileButton = (Button) findViewById(R.id.btnProfileSettings);
        restaurantButton = (Button) findViewById(R.id.btnRestaurantInformation);
        barcodeButton = (Button) findViewById(R.id.Barcode);
        addItemRedirect = (Button) findViewById(R.id.btnItemRedirect);
        addRestaurantItemRedirect = (Button) findViewById(R.id.btnRestaurantItemRedirect);
        restaurantItemSelection = (Button) findViewById(R.id.btnRestaurantItemSelection);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

        barcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Barcode.class);
                startActivity(intent);
            }
        });

        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                intent.putExtra("name1", "Dave");
                intent.putExtra("name2", "Ben");
                startActivity(intent);
            }
        });
        addItemRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItems.class);
                startActivity(intent);
            }
        });

        addRestaurantItemRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddRestaurantItems.class);
                startActivity(intent);
            }
        });

        restaurantItemSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RestaurantItems.class);
                startActivity(intent);
            }
        });
    }
}