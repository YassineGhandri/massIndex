package com.example.massindex.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.massindex.R;
import com.example.massindex.controlleur.Controle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    /**
     * propriétés
     */
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmily;
    private Controle controle;

    /**
     * initialisation des liens avec les objects graphiques
     */
    private void init(){
        txtPoids= (EditText) findViewById(R.id.txtPoids);
        txtTaille=(EditText)findViewById(R.id.txtTaille);
        txtAge=(EditText)findViewById(R.id.txtAge);
        rdHomme=(RadioButton) findViewById(R.id.rdHomme);
        rdFemme=(RadioButton) findViewById(R.id.rdFemme);
        lblIMG=(TextView) findViewById(R.id.lbIMG);
        imgSmily=(ImageView) findViewById(R.id.imgSmily);
        ecouteCalcul();
        this.controle=Controle.getInstance(this);
        recupProfil();
    }
    private void ecouteCalcul(){
        findViewById(R.id.btnCalc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Log.d("message","the btn work correctly");
                Integer poids=0;
                Integer taille=0;
                Integer age=0;
                Integer sexe=0;
                /**
                 * recupération des données
                 */
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch (Exception e){}
                if (rdHomme.isChecked()){
                    sexe=1;
                }
                /**
                 * controle des données
                 */
                if(poids==0 || taille==0 || age==0){
                    Toast.makeText(MainActivity.this,"Saisie Incorrecte0",
                            Toast.LENGTH_SHORT).show();
                }else{
                    afficherResult(poids,taille,age,sexe);
                }
            }
        });
    }
    private void afficherResult(Integer poids, Integer taille, Integer age, Integer sexe){
        /**
         * creation du profil et recupération des resultats
         */
        this.controle.creerProfil(poids,taille,age,sexe,this);
        float img=this.controle.getImg();
        String message=this.controle.getMessage();
        /**
         * affichage
         */
        if(message=="normal"){
            imgSmily.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.BLACK);
        }else{
            if(message=="Trés Faible"){
                imgSmily.setImageResource(R.drawable.happy);
                lblIMG.setTextColor(Color.GREEN);}
            else{
                imgSmily.setImageResource(R.drawable.sad);
                lblIMG.setTextColor(Color.RED);}

        }

        this.lblIMG.setText("IMG: "+String.format("%.01f",img)+" "+message);
    }
    private void recupProfil(){
        if(controle.getPoids()!=null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if(controle.getSex()==1){
                rdHomme.setChecked(true);
            }
            ((Button)findViewById(R.id.btnCalc)).performClick();
        }


    }

}