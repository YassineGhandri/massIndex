package com.example.massindex.modele;

import java.io.Serializable;
import java.util.Date;

public class Profil implements Serializable {

    //constantes
    private static final Integer minFemme=15;
    private static final Integer maxFemme=30;
    private static final Integer minHomme=10;
    private static final Integer maxHomme=25;

    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;

    public Date getDateMesure() {
        return dateMesure;
    }

    private float img;
    private String message;

    public Profil(Date dateMesure,Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure=dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculImg();
        this.resultImg();
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }
    private void calculImg(){
        float f=taille;
        float tailleM= (f/100);
        img= (float) ((1.2*poids/(tailleM*tailleM)) + 0.23*age-10.83*sexe-5.4);
    }
    private void resultImg(){
        Integer min;
        Integer max;
        if(sexe==0){
            min=minFemme;
            max=maxFemme;
        }else{
            min=minHomme;
            max=maxHomme;
        }
        message="normal";
        if(img<min){
            message="Trés Faible";
        }else{
            if(img>max){
                message="Trop de Graisse";
            }
        }
    }
}


