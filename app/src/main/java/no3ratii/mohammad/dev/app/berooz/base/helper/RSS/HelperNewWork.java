package no3ratii.mohammad.dev.app.berooz.base.helper.RSS;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import no3ratii.mohammad.dev.app.berooz.base.G;


public class HelperNewWork {

    public static int readStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) G.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWorkInfo = connectivityManager.getActiveNetworkInfo();
        if (netWorkInfo == null || !netWorkInfo.isConnectedOrConnecting()) {
            return 3;
        } else {
            int netType = netWorkInfo.getType();
            //int netSubType = netWorkInfo.getSubtype();
            if (netType == connectivityManager.TYPE_WIFI) {
                return 1;
            } else if (netType == connectivityManager.TYPE_MOBILE) {
                return 2;
            }
        }
        return 0;
    }
}
