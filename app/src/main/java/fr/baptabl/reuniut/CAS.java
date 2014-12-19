package fr.baptabl.reuniut;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by bat on 11/12/14.
 */

public class CAS {
    //private String login;
    //private String password;

    public String getTicket(String login, String password) {
        String ticket = "null";



        return ticket;
    }

    public boolean isConnected(Context c, String login, String password) {
        ConnectivityManager connMgr = (ConnectivityManager) c.getSystemService(c.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


}
