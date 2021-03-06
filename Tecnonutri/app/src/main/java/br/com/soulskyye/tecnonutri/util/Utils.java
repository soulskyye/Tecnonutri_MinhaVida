package br.com.soulskyye.tecnonutri.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

import br.com.soulskyye.tecnonutri.R;
import retrofit2.Call;

/**
 * Created by ulissescurti on 3/7/17.
 */

public class Utils {

    private static ProgressDialog progressDialog;

    public static final int NETWORK_STATUS_NOT_CONNECTED = 0;
    private static final int NETWORK_STATUS_WIFI = 1, NETWORK_STATUS_MOBILE = 2;

    public static String getFormattedDate(String date){// yyyy-MM-dd
        String[] splittedDate = date.split("-");
        return splittedDate[2]+"/"+splittedDate[1]+"/"+splittedDate[0];
    }

    public static void showProgressDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.loading));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgressDialog(){
        progressDialog.dismiss();
    }

    public static void showErrorPopup(Context context, Throwable t){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(R.string.general_error_message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                R.string.understood,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static String getFormattedNumber(float value){
        String formattedNumber = String.format("%.2f", value);;

        if(Locale.getDefault().equals(new Locale("pt", "BR"))){
            return formattedNumber.replace(".", ",");
        } else{
            return formattedNumber.replace(",", ".");
        }
    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return NETWORK_STATUS_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return NETWORK_STATUS_MOBILE;
        }
        return NETWORK_STATUS_NOT_CONNECTED;
    }

}
