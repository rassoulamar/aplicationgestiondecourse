package fr.fixiphone.gestioncourses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class Produits extends AppCompatActivity implements View.OnFocusChangeListener,View.OnClickListener, AdapterView.OnItemSelectedListener {

    private GestionDeLaBaseDeDonnee gestionDeLaBaseDeDonnee;
    private EditText editTextNomProduit;
    private EditText editTextPrixProduit;
    private EditText editTextSaisieCategorieProduit;
    private EditText editTextPhotoProduit;
    private TextView textViewMessageProduit;
    private Button buttonAjouterProduit;
    private Button buttonModifierProduit;
    private Button buttonSupprimerProduit;
    private Spinner spinnerProduit;
    private String produitAtPoistion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produits);

       gestionDeLaBaseDeDonnee = new GestionDeLaBaseDeDonnee(this);

       editTextNomProduit = findViewById( R.id.editTextNomProduit );
       editTextPrixProduit = findViewById( R.id.editTextPrixProduit );
       editTextSaisieCategorieProduit = findViewById( R.id.editTextSaisieCategorieProduit );
        editTextPhotoProduit = findViewById( R.id.editTextPhotoProduit );
        textViewMessageProduit = findViewById( R.id.textViewMessageProduit );
        buttonAjouterProduit = findViewById( R.id.buttonAjouterProduit );
        buttonModifierProduit= findViewById( R.id.buttonModifierProduit );
        buttonSupprimerProduit= findViewById( R.id.buttonSupprimerProduit );
        spinnerProduit = findViewById( R.id.spinnerProduit );


        editTextNomProduit.setOnFocusChangeListener( this );
        editTextPrixProduit.setOnFocusChangeListener( this );
        editTextSaisieCategorieProduit.setOnFocusChangeListener( this );
        editTextPhotoProduit.setOnFocusChangeListener( this );

        buttonAjouterProduit.setOnClickListener( this );
        buttonModifierProduit.setOnClickListener( this );
        buttonSupprimerProduit.setOnClickListener( this );
        spinnerProduit.setOnItemSelectedListener( this );

        GestionDeLaBaseDeDonnee db = new GestionDeLaBaseDeDonnee(getApplicationContext());


        List<String> nomProduit = db.getLaListeDesProduit();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,nomProduit);

        // attaching data adapter to spinner
        spinnerProduit.setAdapter(dataAdapter);




    }



    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        if(view == editTextNomProduit && hasFocus)
            editTextNomProduit.setText( "" );

        if (view == editTextPrixProduit && hasFocus)
            editTextPrixProduit.setText( "" );

        if (view== editTextSaisieCategorieProduit && hasFocus)
            editTextSaisieCategorieProduit.setText( "" );

        if (view == editTextPhotoProduit && hasFocus)
            editTextPhotoProduit.setText( "" );

    }

    @Override
    public void onClick(View view) {

        if(view == buttonAjouterProduit ){
            gestionDeLaBaseDeDonnee.insererProduit( editTextNomProduit.getText().toString(),editTextPrixProduit.getText().toString(),editTextSaisieCategorieProduit.getText().toString(),editTextPhotoProduit.getText().toString() );
        }
        if(view== buttonSupprimerProduit){
            gestionDeLaBaseDeDonnee.deleteProduit(produitAtPoistion);
            textViewMessageProduit.setText( produitAtPoistion );

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             produitAtPoistion = adapterView.getItemAtPosition( i ).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
