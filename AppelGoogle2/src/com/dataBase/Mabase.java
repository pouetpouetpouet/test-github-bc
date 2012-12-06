package com.dataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Mabase extends SQLiteOpenHelper {

	public Mabase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// Constructeur de la classe Mabase
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String strReq = "CREATE TABLE quotes(id INTEGER PRIMARY KEY AUTOINCREMENT,txt varchar (250))";	
		//requete de création de la table
		try {
			db.execSQL(strReq);
			// execute la requete de création de la table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.execSQL("INSERT INTO quotes('txt') VALUES('Mon lit est possessif, le matin il veut pas me laisser partir…')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Tu tweet comme tu respire !')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Je suis née en kit, chez babou…')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('J’aimerai être un PC pour pouvoir redémarrer ma vie en mode « Sans Echec »')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Le travail c’est comme le jardinage, plus on s’applique, plus on récolte…')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Ne remet pas à demain ce que tu as déjà fait hier, ça sert à rien')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('C’est pas aux vieux geek qu’on apprend à faire Alt F4')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Tout ce qui est rar est share')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Prendre une salade au Mcdo c’est comme installer Mac OS sur un PC')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('J’adorerais changer le monde, mais ils ne veulent pas me fournir le code source.')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Quand la vie apporte des questions, Google donne les réponses.')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Windows a détecté que vous n’aviez pas de clavier. Appuyez sur ‘F9′ pour continuer.')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('404 : quotes not found')");
			db.execSQL("INSERT INTO quotes('txt') VALUES('Être sage comme une jpeg')");
			
			//insertions sql pour remplir la base
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
