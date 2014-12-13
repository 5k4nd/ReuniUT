package fr.baptabl.reuniut;

import android.util.Log;

/**
 * Created by bat on 11/12/14.
 */
/*
a priori, n'est plus singleton. nécessité de singleton transférée dans la classe login
*/

    //CETTE CLASSE N'EST PLUS UTILISÉE À L'HEURE ACTUELLE (TEMPORAIRE)

public class CAS {
    public boolean isConnected;//équivalent de "has worked at least once"... (sic)
    private String login;
    private String password;

    private CAS(String login, String password) {
        this.login = login;
        this.password = password;
        this.isConnected = true;//le temps des tests
    }


//À suppprimer, MAIS je garde pour les tests
    public String getEdt(String login, String password) {
        String ticket = "null";

        //String barbareno = getData();
        String barbareno = "123456";
        barbareno = barbareno.substring(1, 5);
        Log.v("HTTP", barbareno);

        ticket = "login : " + login + "\net password : " + password;

        if (ticket != "null")
            this.isConnected = true;//tant que le CAS n'est pas opérationnel // À IMPLÉMENTER
        return ticket;

    }

    private String getEdt(String edt) {
        if (this.isConnected == false)
            return "null";
        else {
            //return edt = getData().parserDeStringAimplementer;
            return "null";

        }
    }

}
