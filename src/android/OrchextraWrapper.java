package orchextra;

import android.app.Application;

import com.gigigo.ggglogger.GGGLogImpl;
import com.gigigo.orchextra.CrmUser;
import com.gigigo.orchextra.CustomSchemeReceiver;
import com.gigigo.orchextra.Orchextra;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import orchextra.entities.OrchextraAuthTokens;
import orchextra.sdk.OrchextraSdk;
import orchextra.utils.DataParser;

/**
 * This class echoes a string called from JavaScript.
 */
public class OrchextraWrapper extends CordovaPlugin {

    private static final String ACTION_INIT = "init";
    private static final String ACTION_START = "start";
    private static final String ACTION_STOP = "stop";
    private static final String ACTION_BIND_USER = "bindUser";
    private static final String ACTION_OPEN_SCANNER = "openScanner";

    private OrchextraSdk orchextraSdk;
    private DataParser dataParser;

    private CallbackContext schemeContext;
    private Application application;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        application = cordova.getActivity().getApplication();

        OrchextraSdk.initSdk(application);

        orchextraSdk = new OrchextraSdk();
        dataParser = new DataParser();
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_INIT)) {
            setTokenCredentials(args, callbackContext);
            this.schemeContext = callbackContext;
            return true;
        } else if (action.equals(ACTION_START)) {
            start(callbackContext);
            return true;
        } else if (action.equals(ACTION_STOP)) {
            stop(callbackContext);
            return true;
        } else if (action.equals(ACTION_BIND_USER)) {
            bindUser(args, callbackContext);
            return true;
        } else if (action.equals(ACTION_OPEN_SCANNER)) {
            openScanner(callbackContext);
            return true;
        }
        return false;
    }

    private void setTokenCredentials(JSONArray args, final CallbackContext callbackContext) {
        final OrchextraAuthTokens orchextraAuthTokens = dataParser.obtainApiKeyAndSecret(args);

        if (orchextraAuthTokens != null) {
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    orchextraSdk.setTokenCredentials(application, orchextraAuthTokens);
                }
            });
        } else {
            GGGLogImpl.log("Api key or secret can't be null");
            callbackContext.error(0);
        }
    }

    private void start(final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
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

        Orchextra.setCustomSchemeReceiver(customSchemeReceiver);
    }

    private void stop(final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if (orchextraSdk.stopSdk()) {
                    callbackContext.success();
                } else {
                    GGGLogImpl.log("You must call Orchextra Init method before start method");
                }
            }
        });
    }

    private void openScanner(CallbackContext callbackContext) {
        orchextraSdk.startScanner();
        callbackContext.success();
    }

    private void bindUser(JSONArray args, CallbackContext callbackContext) {
        CrmUser orcUser = dataParser.obtainUser(args);

        if (orcUser != null) {
            orchextraSdk.setUser(orcUser);
            callbackContext.success();
        }
    }

    private CustomSchemeReceiver customSchemeReceiver =
            new CustomSchemeReceiver() {
                @Override
                public void onReceive(String scheme) {
                    sendReceivedScheme(scheme);
                }
            };

    private void sendReceivedScheme(String scheme) {
        if (scheme != null && !scheme.isEmpty()) {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, scheme);
            pluginResult.setKeepCallback(true);
            if (schemeContext != null) {
                schemeContext.sendPluginResult(pluginResult);
            }
        }
    }
}
