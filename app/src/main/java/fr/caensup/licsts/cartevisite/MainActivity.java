package fr.caensup.licsts.cartevisite;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CarteDeVisiteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Gestion de la base de donnée avec un DBHelper
        // Décide automatiquement si il faut créer la bd
        // ne rien faire ou appliquer l'upgrade en cas de changement de version
        dbHelper = new CarteDeVisiteDBHelper( this );

        // Test temporaire des fonctions CRUD générées par ChatGpt (augmenter son niveau de confiance)
        testFonctionsCrud();
    }


    public void testFonctionsCrud() {
        // Creation
        int idCv = (int)dbHelper.insertCarteDeVisite("DUPOND","Marc", "13 bis", "rue du test","CAEN","14000" );
        Log.i("SQLCV", "id de creation : " + idCv );
        // Lecture
        Cursor cursor = dbHelper.getCarteDeVisiteById( idCv );
        if ( cursor.moveToFirst()) {
            int indexNom = cursor.getColumnIndex("NOM");
            if ( indexNom >= 0 ) {
                String nom = cursor.getString( indexNom );
                Log.i("SQLCV", "Nom : " + nom );
            }
        }

        // Modif
        dbHelper.updateCarteDeVisite( idCv , "DUPOND","Marc", "13", "rue du test","CAEN","14000" );
        // Lecture
        cursor = dbHelper.getCarteDeVisiteById( idCv );
        if ( cursor.moveToFirst()) {
            int indexNum = cursor.getColumnIndex("NUMERO");
            if ( indexNum >= 0 ) {
                String numero = cursor.getString( indexNum );
                Log.i("SQLCV", "Numero : " + numero );
            }
        }


        // Delete
        dbHelper.deleteCarteDeVisite( idCv );
        // Query pour verifier qu'il n'y plus de ligne
        cursor = dbHelper.getCarteDeVisiteById( idCv );
        if ( cursor != null ) {
            Log.i("SQLCV", "Nombre de ligne du curseur : " + cursor.getCount());
        } else {
            Log.i("SQLCV", "Curseur null (pas de données)");
        }
    }



}