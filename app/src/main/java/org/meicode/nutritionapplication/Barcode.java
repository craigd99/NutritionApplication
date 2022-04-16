package org.meicode.nutritionapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.meicode.nutritionapplication.pojo.Item;

public class Barcode extends AppCompatActivity {

    //Initialize variable

    Button btScan, contentCopy,databaseCheck;
    TextView barResult,barContent, textItemName, textCarbohydrates;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        //Assign variable

        btScan = findViewById(R.id.bt_scan);
        barResult = findViewById(R.id.barcodeResult);
        barContent = findViewById(R.id.barcodeContent);
        textItemName = findViewById(R.id.txtItemName);
        textCarbohydrates = findViewById(R.id.txtCarbohydrates);
        DB = new DBHelper(this);
        contentCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)
                getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", barContent.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Text copied to clipboard",
                Toast.LENGTH_LONG).show();
            }
        });

        databaseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://eandata.com/search.php"));
                    startActivity(browserIntent);
            }
        });

        btScan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        Barcode.this
                );

                intentIntegrator.setPrompt("For flash use volume up key");
                //Set beep
                intentIntegrator.setBeepEnabled(true);
                //Locked orientation
                intentIntegrator.setOrientationLocked(true);
                //Set Capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //Intiate scan
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialize intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data

        );
        barResult.setText(intentResult.toString());
        String barResultToSplit = intentResult.toString();

        String toSplit = barResultToSplit;
        String[] split = toSplit.split(":");

        barContent.setText(split[2].trim());

        String barcodeContentSplit = split[2].trim();
        String[] barcodeContent = barcodeContentSplit.split("R");

        barContent.setText(barcodeContent[0]);

        String barcodeID = barContent.getText().toString();

        Item item = DB.retrieveItem(barcodeID);
        System.out.println(item.getCarbohydrates() + " , " + item.getItemName());

        textCarbohydrates.setText(item.getCarbohydrates());

        int carbs = Integer.parseInt(textCarbohydrates.getText().toString());

        if(carbs <= 30){
            textCarbohydrates.setTextColor(Color.GREEN);
        }
        else{
            if(carbs >= 30 & carbs <= 60){
                textCarbohydrates.setTextColor(Color.YELLOW);
            }
            else{
                if(carbs >= 60 & carbs <= 90){
                    textCarbohydrates.setTextColor(Color.RED);
                }
            }
        }

        textItemName.setText(item.getItemName());

        //check condition
        if (intentResult.getContents()!= null){
            //when result content is not null
            //initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    Barcode.this
            );
            //Set title
            builder.setTitle("Result");
            //Set message
            builder.setMessage(intentResult.getContents());
            //Set positive button
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Dismiss dialog
                    dialogInterface.dismiss();
                }
            });
            //Show alert dialog
            builder.show();
        }else {
            //When result content is null
            //Display toast
            Toast.makeText(getApplicationContext(),"oops... You did not scan anything",Toast.LENGTH_SHORT).show();
        }


    }

}