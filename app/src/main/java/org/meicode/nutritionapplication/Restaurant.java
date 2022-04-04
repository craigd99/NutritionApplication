package org.meicode.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.net.URI;

public class Restaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("name2");

    }
    public void openKFC(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://brand-uk.assets.kfc.co.uk/nutrition-allergens.pdf"));
        startActivity(browserIntent);
    }
    public void openMcdonalds(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mcdonalds.com/gb/en-gb/good-to-know/nutrition-calculator.html"));
        startActivity(browserIntent);
    }
    public void openBurgerKing(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://company.bk.com/pdfs/nutrition.pdf"));
        startActivity(browserIntent);
    }
    public void openSubway(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.subway.com/-/media/United_Kingdom/2022/W1/Jan22NutritionalInformation.pdf"));
        startActivity(browserIntent);
    }
}