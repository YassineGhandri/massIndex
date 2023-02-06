package com.example.massindex.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.massindex.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccessLocal {

    private String monBase="bdCoach.sqlite";
    private Integer versionBase=1;
    private MySQLiteOpenHelper accessBD;
    private SQLiteDatabase bd;

    /**
     *
     * @param contexte
     */
    public AccessLocal(Context contexte){
        accessBD=new MySQLiteOpenHelper(contexte,monBase,null,versionBase);
    }

    /**
     * ajout d'un profil à la base des données
     * @param profil
     */
    public void ajout(Profil profil){
        bd=accessBD.getWritableDatabase();
        String req="insert into profil (dateMesure, poids, taille,age, sexe)values";
               req+= "(\""+profil.getDateMesure()+"\","+profil.getPoids()+","+profil.getTaille()+","+profil.getAge()+","+profil.getSexe()+")";
        bd.execSQL(req);
    }
    public Profil recupDernier(){
        bd=accessBD.getReadableDatabase();
        Profil profil=null;
        String req="select * from profil";
        Cursor curseur=bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date date=new Date();
            Integer poids=curseur.getInt(1);
            Integer taille=curseur.getInt(2);
            Integer age=curseur.getInt(3);
            Integer sexe=curseur.getInt(4);
            profil=new Profil(date,poids,taille,age,sexe);
        }
        curseur.close();
        return profil;
    }
}
