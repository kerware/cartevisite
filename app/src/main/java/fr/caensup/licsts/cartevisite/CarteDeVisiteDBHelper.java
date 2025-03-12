package fr.caensup.licsts.cartevisite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarteDeVisiteDBHelper extends SQLiteOpenHelper {

    // Nom de la base de données et version
    private static final String DATABASE_NAME = "carte_visite.db";
    private static final int DATABASE_VERSION = 1;

    // Nom de la table
    private static final String TABLE_NAME = "CarteDeVisite";

    // Colonnes de la table
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NOM = "NOM";
    private static final String COLUMN_PRENOM = "PRENOM";
    private static final String COLUMN_NUMERO = "NUMERO";
    private static final String COLUMN_VOIE = "VOIE";
    private static final String COLUMN_VILLE = "VILLE";
    private static final String COLUMN_CODE_POSTAL = "CODE_POSTAL";

    // SQL de création de la table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOM + " TEXT NOT NULL, " +
                    COLUMN_PRENOM + " TEXT NOT NULL, " +
                    COLUMN_NUMERO + " TEXT NOT NULL, " +
                    COLUMN_VOIE + " TEXT NOT NULL, " +
                    COLUMN_VILLE + " TEXT NOT NULL, " +
                    COLUMN_CODE_POSTAL + " TEXT NOT NULL);";

    public CarteDeVisiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crée la table lors de la création de la base de données
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprime l'ancienne table si elle existe et recrée-la
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insérer une nouvelle carte de visite
    public long insertCarteDeVisite(String nom, String prenom, String numero, String voie, String ville, String codePostal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, nom);
        values.put(COLUMN_PRENOM, prenom);
        values.put(COLUMN_NUMERO, numero);
        values.put(COLUMN_VOIE, voie);
        values.put(COLUMN_VILLE, ville);
        values.put(COLUMN_CODE_POSTAL, codePostal);

        // Insertion d'une nouvelle ligne et retour de l'ID inséré
        return db.insert(TABLE_NAME, null, values);
    }

    // Lire une carte de visite par ID
    public Cursor getCarteDeVisiteById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
    }

    // Lire toutes les cartes de visite
    public Cursor getAllCartesDeVisite() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    // Mettre à jour une carte de visite par ID
    public int updateCarteDeVisite(int id, String nom, String prenom, String numero, String voie, String ville, String codePostal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, nom);
        values.put(COLUMN_PRENOM, prenom);
        values.put(COLUMN_NUMERO, numero);
        values.put(COLUMN_VOIE, voie);
        values.put(COLUMN_VILLE, ville);
        values.put(COLUMN_CODE_POSTAL, codePostal);

        // Mise à jour d'une ligne
        return db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    // Supprimer une carte de visite par ID
    public int deleteCarteDeVisite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }




}

