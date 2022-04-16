package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RestaurantItemSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner restaurantItemSelection;
    DBHelper DB;
    TextView restaurantFoodItemOne, restaurantFoodItemTwo,restaurantFoodItemThree, restaurantFoodItemOneCarbs
            ,restaurantFoodItemTwoCarbs,restaurantFoodItemThreeCarbs, insulinRequirement;
    Button saveSelection, saveSelectionTwo, saveSelectionThree, restaurantCalculation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_item_selection);

        restaurantItemSelection = (Spinner) findViewById(R.id.spinnerRestaurantItemSelectionNew);
        restaurantFoodItemOne = (TextView) findViewById(R.id.restaurantItemOne);
        restaurantFoodItemOneCarbs = (TextView) findViewById(R.id.restaurantItemOneCarbs);
        restaurantFoodItemTwo = (TextView) findViewById(R.id.restaurantItemTwo);
        restaurantFoodItemTwoCarbs = (TextView) findViewById(R.id.restaurantItemTwoCarbs);
        restaurantFoodItemThree = (TextView) findViewById(R.id.restaurantItemThree);
        restaurantFoodItemThreeCarbs = (TextView) findViewById(R.id.restaurantItemThreeCarbs);
        insulinRequirement = (TextView) findViewById(R.id.txtInsulinRequirement);
        saveSelection = (Button) findViewById(R.id.btnSaveSelection);
        saveSelectionTwo = (Button) findViewById(R.id.btnSaveSelection2);
        saveSelectionThree = (Button) findViewById(R.id.btnSaveSelection3);
        restaurantCalculation = (Button) findViewById(R.id.restaurantCalculations);

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

        saveSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selectedItems = new ArrayList<String>();
                selectedItems.add(restaurantItemSelection.getSelectedItem().toString());
                restaurantFoodItemOne.setText(selectedItems.get(0));
                String itemname = restaurantFoodItemOne.getText().toString();
                int itemresult = DB.getRestaurantCarbohydrates(itemname);
                restaurantFoodItemOneCarbs.setText(Integer.toString(itemresult));
            }
        });


        saveSelectionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selectedItems = new ArrayList<String>();
                selectedItems.add(restaurantItemSelection.getSelectedItem().toString());
                restaurantFoodItemTwo.setText(selectedItems.get(0));
                String itemname = restaurantFoodItemTwo.getText().toString();
                int itemresult = DB.getRestaurantCarbohydrates(itemname);
                restaurantFoodItemTwoCarbs.setText(Integer.toString(itemresult));

            }
        });

        saveSelectionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selectedItems = new ArrayList<String>();
                selectedItems.add(restaurantItemSelection.getSelectedItem().toString());
                restaurantFoodItemThree.setText(selectedItems.get(0));
                String itemname = restaurantFoodItemThree.getText().toString();
                int itemresult = DB.getRestaurantCarbohydrates(itemname);
                restaurantFoodItemThreeCarbs.setText(Integer.toString(itemresult));

            }
        });

        restaurantCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

                String insulin = sharedPreferences.getString("insulin", "no");
                String[] insulinRatio = insulin.split(":");
                int insulinRatioDose = Integer.parseInt(insulinRatio[0]);
                int insulinRatioCarbs = Integer.parseInt(insulinRatio[1]);

                int carbsNum = Integer.parseInt(restaurantFoodItemOneCarbs.getText().toString());
                int carbsNumTwo = Integer.parseInt(restaurantFoodItemTwoCarbs.getText().toString());
                int carbsNumThree = Integer.parseInt(restaurantFoodItemThreeCarbs.getText().toString());
                int totalcarbs = carbsNum+carbsNumTwo+carbsNumThree;
                int intInsulinRequirement = (insulinRatioDose%insulinRatioCarbs)*totalcarbs;
                System.out.println(intInsulinRequirement);
                insulinRequirement.setText(Integer.toString(intInsulinRequirement));


            }
        });
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}