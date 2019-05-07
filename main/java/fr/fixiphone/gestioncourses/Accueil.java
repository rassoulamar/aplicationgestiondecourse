package fr.fixiphone.gestioncourses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Accueil extends AppCompatActivity  {


    ImageButton imageButtonCatalogue;
    ImageButton imageButtonPanier;
    ImageButton imageButtonFacture;
    ImageButton imageButtonClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        imageButtonCatalogue = findViewById(R.id.imageButtonCatalogue);
        imageButtonPanier = findViewById(R.id.imageButtonPanier);
        imageButtonFacture = findViewById(R.id.imageButtonFacture);
        imageButtonClient = findViewById(R.id.imageButtonClient);








        imageButtonCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPageProduit = new Intent(Accueil.this, Produits.class);
                startActivity(intentPageProduit);
            }
        });

        imageButtonPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPagePannier = new Intent(Accueil.this, Pannier.class);
                startActivity(intentPagePannier);
            }
        });
        imageButtonFacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPageFacture = new Intent(Accueil.this, Facture.class);
                startActivity(intentPageFacture);
            }
        });
        imageButtonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPageClient = new Intent(Accueil.this, Client.class);
                startActivity(intentPageClient);
            }
        });



    }
}