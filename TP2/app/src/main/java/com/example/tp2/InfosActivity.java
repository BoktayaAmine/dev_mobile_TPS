package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class InfosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_produit);

        Intent intent = getIntent();


        int photo = intent.getIntExtra("photo", 0);
        String nom = intent.getStringExtra("nom");
        String description = intent.getStringExtra("description");
        String detailsIngred = intent.getStringExtra("detailsIngred");
        String preparation = intent.getStringExtra("preparation");


        ImageView imageView = findViewById(R.id.pizzaImage);
        TextView textViewNom = findViewById(R.id.pizzaName);
        TextView textViewDescription = findViewById(R.id.pizzaDescription);
        TextView textViewDetailsIngred = findViewById(R.id.pizzaIngredients);
        TextView textViewPreparation = findViewById(R.id.pizzaPreparation);


        imageView.setImageResource(photo);
        textViewNom.setText(nom);
        textViewDescription.setText(description);
        textViewDetailsIngred.setText(detailsIngred);
        textViewPreparation.setText(preparation);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Pizza recipes");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
