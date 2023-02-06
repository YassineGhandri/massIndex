package com.example.massindex.controlleur;

import android.content.Context;

import com.example.massindex.modele.AccessLocal;
import com.example.massindex.modele.Profil;
import com.example.massindex.outils.Serializer;

import java.util.Date;

public final class Controle {
    private static Controle instance =null;
    private static Profil profil;
    private static String nomFic="saveprofil";
    private static AccessLocal accessLocal;

    /**
     * create private constructor
     */
    private Controle(){
        super();
    }

    /**
     * create instance
     * @return instance
     */
    public static final Controle getInstance(Context contexte){
        if(Controle.instance==null){
            Controle.instance=new Controle();
            accessLocal=new AccessLocal(contexte);
            profil=accessLocal.recupDernier();
           // recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     *
     * @param poids en kg
     * @param taille en cm
     * @param age
     * @param sexe
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte){
        profil=new Profil(new Date(),poids, taille, age, sexe);
        accessLocal.ajout(profil);
        //Serializer.Serializer(nomFic, profil, contexte);
    }

    /**
     * recuperate img of object profile
     * @return Img
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * recuperate message of object profile
     * @return message
     */
    public String getMessage(){
        return profil.getMessage();
    }

    /**
     * recuperation du profil
     * @param contexte
     */
    public static void recupSerialize(Context contexte){
        profil= (Profil) Serializer.deSerializer(nomFic, contexte);
    }
    public Integer getPoids(){
        if(profil==null){
            return null;
        }else{
            return profil.getPoids();
        }
    }
    public Integer getTaille(){
        if(profil==null){
            return null;
        }else{
            return profil.getTaille();
        }
    }
    public Integer getAge(){
        if(profil==null){
            return null;
        }else{
            return profil.getAge();
        }
    }
    public Integer getSex(){
        if(profil==null){
            return null;
        }else{
            return profil.getSexe();
        }
    }


}

