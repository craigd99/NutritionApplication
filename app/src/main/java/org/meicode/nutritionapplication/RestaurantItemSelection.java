package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class RestaurantItemSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner restaurantItemSelection;
    DBHelper DB;
    ListView restaurantFoodItems;
    TextView restaurantFoodItemTextView;
    Button addRestaurantItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_item_selection);

        restaurantItemSelection = (Spinner) findViewById(R.id.spinnerRestaurantItemSelectionNew);
        restaurantFoodItems = (ListView) findViewById(R.id.restaurantItemList);
        restaurantFoodItemTextView = (TextView) findViewById(R.id.restaurantTextView);
        addRestaurantItem = (Button) findViewById(R.id.addToRestaurantFoodList);

        DB = new DBHelper(this);

        restaurantItemSelection.setOnItemSelectedListener(this);

       loadRestaurantSpinnerData();

    }

    private void loadRestaurantSpinnerData() {
        ArrayList<String> restaurantItemNames = new ArrayList<>();
        Bundle extras = getIntent().getExtras();

        ArrayList<String> restaurantItems = (ArrayList<String>) extras.getSerializable("restaurantItems");

        for (String itemID : restaurantItems) {
            ArrayList<String> restaurantItemNamesList = DB.getAllRestaurantItemNames(Integer.parseInt(itemID));
            System.out.println(restaurantItemNamesList);
            restaurantItemNames.add(restaurantItemNamesList.get(0));
            //Call database passing it in the item ID and retrieve the item listing
            // Select itemname from itemtable where id = itemID;
            //  restaurantItemNames.add()
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, restaurantItemNames);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        restaurantItemSelection.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        List<String> selectedItems = new ArrayList<String>();
        selectedItems.add(restaurantItemSelection.getSelectedItem().toString());
        restaurantFoodItemTextView.setText(selectedItems.get(0));


        ArrayAdapter restaurantItemAdapter = new ArrayAdapter(this,
                R.layout.activity_restaurant_item_selection,R.id.restaurantTextView,selectedItems);
        restaurantFoodItems.setAdapter(restaurantItemAdapter);
    }




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}