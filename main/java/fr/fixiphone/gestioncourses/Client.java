package fr.fixiphone.gestioncourses;

import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Client extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    //declaration des attributs

    private Spinner spinnerClient;
    private GestionDeLaBaseDeDonnee gestionDeLaBaseDeDonnee;
    private EditText editTextNom ;
    private EditText editTextNumero;
    private EditText editTextAdresse;
    private Button buttonAjouteClient ;
    private TextView textViewMessage;
    private Button buttonSupprimerClient;
    private TextView textViewChoisirClient;
    private String itemAtPosi;
    private Button buttonModifierClient;
    private Map<String,Map<String,String>> mapTable = new HashMap<>( );
    private Map<String,String> mapTeleAdresse = new HashMap<>( );
    private String nom,numero,adresse;
    private int position=0;
    private int compt;
    private int j;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);
        spinnerClient = findViewById(R.id.spinnerClient);

        editTextNom = findViewById(R.id.editTextNom);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextAdresse= findViewById(R.id.editTextAdresse);
        buttonAjouteClient= findViewById(R.id.buttonAjouterClient);
        textViewMessage = findViewById(R.id.textViewMessage);
        buttonSupprimerClient = findViewById( R.id.buttonSupprimerClient );
        textViewChoisirClient = findViewById( R.id.textViewChoisirClient );
        buttonModifierClient = findViewById( R.id.buttonModifierClient );

        editTextNom.setOnFocusChangeListener(this);
        editTextNumero.setOnFocusChangeListener(this);
        editTextAdresse.setOnFocusChangeListener(this);
        buttonAjouteClient.setOnClickListener(this);
        buttonSupprimerClient.setOnClickListener( this );
        spinnerClient.setOnItemSelectedListener( this );
        buttonModifierClient.setOnClickListener( this );

        GestionDeLaBaseDeDonnee db = new GestionDeLaBaseDeDonnee(getApplicationContext());
        GestionDeLaBaseDeDonnee db2 = new GestionDeLaBaseDeDonnee(getApplicationContext());

        List<String> nomUser = db.getAllNomUser();
        // Creating adapter for spinner
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,nomUser);

        // attaching data adapter to spinner
        spinnerClient.setAdapter(dataAdapter);

        mapTable = db2.getAllNumAdresseUser();


    }

    // la methode qui efface les champs nom telephone et adresse en cliquant
    @Override
    public void onFocusChange(View view, boolean hasfocus) {

        if (view == editTextNom && hasfocus ){
            editTextNom.setText("");
        }
        if (view == editTextNumero && hasfocus){
            editTextNumero.setText("");
        }
        if (view == editTextAdresse && hasfocus){
            editTextAdresse.setText("");
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        if (view == buttonAjouteClient) {
            gestionDeLaBaseDeDonnee = new GestionDeLaBaseDeDonnee( this );
            gestionDeLaBaseDeDonnee.insererUser( editTextNom.getText().toString(), editTextNumero.getText().toString(), editTextAdresse.getText().toString() );

            textViewMessage.setText( "le client a été ajouté avec succes " );
        }

        if (view == buttonModifierClient) {
            gestionDeLaBaseDeDonnee = new GestionDeLaBaseDeDonnee( this );

            nom = itemAtPosi;
            j=0;
            if (mapTable.containsKey( nom )){
                j=position;

                textViewMessage.setText( "position"+j );
                mapTeleAdresse = mapTable.get( nom );
                compt=1;
                for (String numero : mapTeleAdresse.keySet()){

                    if (j==compt) {
                        adresse = mapTeleAdresse.get( numero );

                        editTextNom.setText( nom);
                        editTextNumero.setText( numero );
                        editTextAdresse.setText( adresse );

                    }
                    compt++;

                }

            }

        }
        if (view == buttonSupprimerClient){
            gestionDeLaBaseDeDonnee= new GestionDeLaBaseDeDonnee( this );

            textViewMessage.setText( "le client a été bien supprimer "+itemAtPosi);
            gestionDeLaBaseDeDonnee.deleteData(itemAtPosi);


        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        itemAtPosi = (String) adapterView.getItemAtPosition(i);
        position = i;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        adapterView.getItemAtPosition( 0 );
        String selectionner = "Choisir un client ";
        textViewMessage.setText( selectionner );


    } //onNothingSelected

}// class client
