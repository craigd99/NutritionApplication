package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RestaurantItems extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Spinner restaurantSelection;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_items);

        restaurantSelection = (Spinner) findViewById(R.id.spinnerRestaurantSelection);
        DB = new DBHelper(this);

        restaurantSelection.setOnItemSelectedListener(this);

        loadSpinnerData();

    }

    private void loadSpinnerData() {
        List<String> restaurants = DB.getAllRestaurants();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,restaurants);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        restaurantSelection.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}