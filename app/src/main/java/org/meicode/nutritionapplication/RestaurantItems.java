package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantItems extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Spinner restaurantSelection, restaurantItemSelection;
    DBHelper DB;

    Map<Integer,String> restaurants;
    ArrayList<String> restaurantItems;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_items);

        restaurantSelection = (Spinner) findViewById(R.id.spinnerRestaurantSelection);
        restaurantItemSelection = (Spinner) findViewById(R.id.spinnerRestaurantItemSelection);

        DB = new DBHelper(this);

        restaurantSelection.setOnItemSelectedListener(this);
        restaurantItemSelection.setOnItemSelectedListener(this);

        loadRestaurantSpinnerData();

    }

    private void loadRestaurantSpinnerData() {
        restaurants = DB.getAllRestaurants();

        List<String> list = new ArrayList<>();

        for(Integer key: restaurants.keySet()){
            list.add(restaurants.get(key));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        restaurantSelection.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int id = 0;
        String restaurant = adapterView.getItemAtPosition(i).toString();


        for (Map.Entry entry : restaurants.entrySet()) {
            if (restaurant.equals(entry.getValue())) {

                id = (int) entry.getKey();

            }


        }
        System.out.println(id + " " + restaurant);
        restaurantItems = DB.getAllRestaurantItems(id);

        Intent intent = new Intent(RestaurantItems.this, RestaurantItemSelection.class);
        intent.putExtra("restaurantItems", restaurantItems);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}