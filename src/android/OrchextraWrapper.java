package orchextra;

import android.app.Application;

import com.gigigo.ggglogger.GGGLogImpl;
import com.gigigo.orchextra.ORCUser;
import com.gigigo.orchextra.Orchextra;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import orchextra.entities.OrchextraAuthTokens;
import orchextra.sdk.OrchextraSdk;
import orchextra.utils.DataParser;

/**
 * This class echoes a string called from JavaScript.
 */
public class OrchextraWrapper extends CordovaPlugin {

    public static final String ACTION_INIT = "init";
    public static final String ACTION_START = "start";
    public static final String ACTION_STOP = "stop";
    public static final String ACTION_SET_USER = "setUser";
    public static final String ACTION_OPEN_SCANNER = "openScanner";

    private Application application;

    private OrchextraSdk orchextraSdk;
    private DataParser dataParser;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        application = cordova.getActivity().getApplication();

        orchextraSdk = new OrchextraSdk();
        dataParser = new DataParser();
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_INIT)) {
            init(args, callbackContext);
            return true;
        } else if (action.equals(ACTION_START)) {
            start(callbackContext);
            return true;
        } else if (action.equals(ACTION_STOP)) {
            stop(callbackContext);
            return true;
        } else if (action.equals(ACTION_SET_USER)) {
            setUser(args, callbackContext);
            return true;
        } else if (action.equals(ACTION_OPEN_SCANNER)) {
            openScanner(callbackContext);
            return true;
        }
        return false;
    }

    private void init(JSONArray args, final CallbackContext callbackContext) {
        final OrchextraAuthTokens orchextraAuthTokens = dataParser.obtainApiKeyAndSecret(args);

        if (orchextraAuthTokens != null) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    orchextraSdk.initSdk(application, orchextraAuthTokens, callbackContext);
                }
            });
        } else {
            GGGLogImpl.log("Api key or secret can't be null");
            callbackContext.error(0);
        }
    }

    private void start(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orchextraSdk.startSdk()) {
                    callbackContext.success();
                } else {
                    GGGLogImpl.log("You must call Orchextra Init method before start method");
                    callbackContext.error(0);
                }
            }
        });
    }

    private void stop(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orchextraSdk.stopSdk()) {
                    callbackContext.success();
                } else {
                    GGGLogImpl.log("You must call Orchextra Init method before start method");
                    callbackContext.error(0);
                }
            }
        });
    }

    private void openScanner(CallbackContext callbackContext) {
        orchextraSdk.startScanner();
        callbackContext.success();
    }

    private void setUser(JSONArray args, CallbackContext callbackContext) {
        ORCUser orcUser = dataParser.obtainUser(args);

        if (orcUser == null) {
            callbackContext.error(0);
        }

        orchextraSdk.setUser(orcUser);
    }
}
