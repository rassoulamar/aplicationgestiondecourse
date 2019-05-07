package fr.fixiphone.gestioncourses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GestionDeLaBaseDeDonnee extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="shopping.db";
    private static final int DATABASE_VERSION = 6;


    public GestionDeLaBaseDeDonnee(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase db) {
        String strsqlproduits = "create table Produits ("
                +"id_Produit integer primary key autoincrement,"
                +"designation_produit text not null,"
                +"prix real not null,"
                +"categorie text not null,"
                +"photo blob "
                +")";
        String strsqlusers = "create table Users ("
                +"id_User integer primary key autoincrement,"
                +"nom_user text not null,"
                +"tele_user text not null,"
                +"adresse_user text not null"
                +")";
        String strsqlpanier = "create table Panier ("
                +"id_Panier integer primary key autoincrement,"
                +"id_user integer ,"
                +"Foreign key (id_user) references Users(id_User) "
                +")";
        String strsqlcommandes = "create table Commandes("
                +"id_panier integer,"
                +"id_produit integer,"
                +"quantite_produit integer,"
                +"Foreign key (id_panier) references Panier(id_Panier),"
                +"Foreign key (id_produit) references Produits(id_Produit)"
                +")";

        db.execSQL(strsqlproduits);
        db.execSQL(strsqlusers);
        db.execSQL(strsqlpanier);
        db.execSQL(strsqlcommandes);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db,int i, int DATABASE_VERSION) {
        String strsql1 ="drop table Produits";
        String strsql2 ="drop table Users";
        String strsql3 ="drop table Panier";
        String strsql4 ="drop table Commandes";
        db.execSQL(strsql1);
        db.execSQL(strsql2);
        db.execSQL(strsql3);
        db.execSQL(strsql4);
        this.onCreate(db);
    }
    public void insererProduit (String designation_Produit , String prix , String categorie, String photo ){
        String strsqlProduit = "insert into Produits (designation_Produit,prix,categorie,photo) values ('"+designation_Produit+"',"+prix+",'"+categorie+"','"+photo+"')";
        this.getWritableDatabase().execSQL(strsqlProduit);
    }
    public void insererUser (String nom_user,String tele_user ,String adresse_user){
        String strsqlUser = "insert into Users (nom_user,tele_user,adresse_user) values ('"+nom_user+"','"+tele_user+"','"+adresse_user+"')";
        this.getWritableDatabase().execSQL(strsqlUser);

    }


    public List<String> getAllNomUser() {
        List<String> nomUser = new ArrayList<String>();



        String selectNomUser = "SELECT nom_user,tele_user,adresse_user FROM Users";

        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery( selectNomUser, null );

        nomUser.add( 0,"Séléctionner un client" );
        if (cursor.moveToPosition( 0 )) {
            do {
                nomUser.add( cursor.getString( 0));
            } while (cursor.moveToNext());
        }


        cursor.close();

        return nomUser;
    }
    public List<String> getLaListeDesProduit() {
        List<String> nomProduit = new ArrayList<String>();



        String selectNomProduit = "SELECT designation_Produit FROM Produits";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( selectNomProduit, null );

        nomProduit.add( 0,"Séléctionner un produit" );
        if (cursor.moveToPosition( 0 )) {
            do {
                nomProduit.add( cursor.getString( 0));
            } while (cursor.moveToNext());
        }


        cursor.close();

        return nomProduit;
    }

        public Map <String,Map<String,String> > getAllNumAdresseUser(){
            Map <String,Map<String,String> > mapTable = new HashMap<>();
            Map<String,String> numAdresse = new HashMap<>();
            String selectNomAdresse = "SELECT nom_user,tele_user,adresse_user FROM Users";
            SQLiteDatabase db2 = this.getReadableDatabase();
            Cursor cursor1 =db2.rawQuery( selectNomAdresse,null );

            if (cursor1.moveToPosition( 0 )){
                do {
                    mapTable.put( cursor1.getString( 0 ),numAdresse );
                    numAdresse.put( cursor1.getString( 1 ),cursor1.getString( 2 ) );
                }while (cursor1.moveToNext());
            }
            return mapTable;
        }

    public void deleteData(String nom_user) {
            String strdelete = "DELETE FROM Users WHERE nom_user= '"+nom_user+"'";
            this.getReadableDatabase().execSQL( strdelete );

    }
    public void deleteProduit(String designation_produit) {
        String strdelete = "DELETE FROM Produits WHERE designation_produit= '"+designation_produit+"'";
        this.getReadableDatabase().execSQL( strdelete );

    }

    public void updatData (int id_User, String nom_user, String tele_user , String adresse_user){
        String updatequery ="UPDATE Users SET nom_user='"+nom_user+"',tele_user='"+tele_user+"', adresse_user='"+adresse_user+"'  where id_User='"+id_User+"' ";
        this.getReadableDatabase().execSQL( updatequery );
    }


}
