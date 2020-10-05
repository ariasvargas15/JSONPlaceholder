package co.com.ceiba.mobile.pruebadeingreso.utilities;

import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.R;
import dmax.dialog.SpotsDialog;

public class Tools {

    public static SpotsDialog.Builder showDialog(Context context){
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(context).setCancelable(false).setMessage(context.getResources().getString(R.string.generic_loading));
        return sp;
    }
}
