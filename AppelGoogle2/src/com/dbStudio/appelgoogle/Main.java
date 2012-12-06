package com.dbStudio.appelgoogle;

import java.sql.ResultSet;
import java.sql.Statement;

import com.dataBase.Mabase;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {

	private static final int CODE_DE_MON_ACTIVITE = 1; // determine la fenêtre

	/**
	 * Appel la fenetre de recherche google "AppelGoogle.java"
	 */

	private void openGoogle() {

		// Mettez le nom de l'Activity dans la quelle vous êtes actuellement
		Intent intent = new Intent(Main.this, AppelGoogle.class);

		// On démarre l'autre Activity
		startActivityForResult(intent, CODE_DE_MON_ACTIVITE);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dbquote();
		Button b = (Button) findViewById(R.id.btRefresh);
		b.setOnClickListener(monEcouteur);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 * Nom : onOptionsItemSelected Permet d'attribuer une action aux items du
	 * menu
	 */

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Boolean result = false;
		switch (item.getItemId()) {
		case R.id.appelGoogle: // si on appuie sur "appelGoogle"
			openGoogle(); // on ouvre la fenêtre AppelGoogle.java
			result = true;
			break;
		case R.id.quitter: // si on appuie sur "Quitter"
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Quitter...");
			alertDialog.setMessage("êtes vous sûre ?");
			alertDialog.setButton2("Non",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
						}
					});
			alertDialog.setButton("Oui", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish(); // on ferme l'application
				}
			});
			alertDialog.show();
		default:
			break;
		}
		return result;
	}
	private void dbquote()
	{// appel une quote au hasard dans la base de données{
		int random = (int)(Math.random() * 14); //crée un nombre au hasard
		System.out.println(random);
		int version = 1;
		CursorFactory factory = null;
		Mabase maBase = new Mabase(getApplicationContext(), "mbase.db", //appel la base
				factory, version);
		SQLiteDatabase db = maBase.getWritableDatabase();
		maBase.onCreate(db);
			try {
				Cursor c = db.rawQuery( // curseur permet de se balader dans la base
						"SELECT txt FROM quotes where id="+random, null); // requete avec random pour le choix au hasard
				c.moveToNext();
				TextView txt = (TextView) findViewById(R.id.textView2);
				txt.setText(c.getString(c.getColumnIndex("txt"))); // aff
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
private android.view.View.OnClickListener monEcouteur=new View.OnClickListener() {
		
		/* 
		 * Action sur le onClick
		 */
		
		public void onClick(View v) {		
			//execute la recherche sur la webview
			dbquote();
		}
	};
}
