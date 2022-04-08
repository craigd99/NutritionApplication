package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItems extends AppCompatActivity {

    EditText itemName;
    EditText carbohydrateAmount;
    EditText barcodeID;
    Button submit;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);


        itemName = (EditText) findViewById(R.id.AddItemName);
        carbohydrateAmount = (EditText) findViewById(R.id.AddCarbohydrateAmount);
        barcodeID = (EditText) findViewById(R.id.AddBarcodeID);
        submit = (Button) findViewById(R.id.SubmitItem);
        DB = new DBHelper(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strItemName = itemName.getText().toString();
                int strCarbohydrateAmount = Integer.parseInt(carbohydrateAmount.getText().toString());
                String strBarcodeID = barcodeID.getText().toString();

                if (itemName.equals("") || carbohydrateAmount.equals("") || barcodeID.equals("")) {
                    Toast.makeText(AddItems.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    DB.insertFoodData(strItemName, strCarbohydrateAmount, strBarcodeID);
                    Toast.makeText(AddItems.this, "Item successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}