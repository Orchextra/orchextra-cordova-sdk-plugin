package orchextra;

import android.app.Application;

import com.gigigo.orchextra.Orchextra;
import com.gigigo.orchextra.OrchextraCompletionCallback;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import orchextra.utils.LogD;

/**
 * This class echoes a string called from JavaScript.
 */
public class OrchextraWrapper extends CordovaPlugin {

    private Application application;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        application = cordova.getActivity().getApplication();
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            initSdk(callbackContext);
            return true;
        } else if (action.equals("start")) {
            startSdk(callbackContext);
            return true;
        } else if (action.equals("openScanner")) {
            openScanner(callbackContext);
            return true;
        }
        return false;
    }

    private void initSdk(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Orchextra.init(application, new OrchextraCompletionCallback() {
                    @Override
                    public void onSuccess() {
                        callbackContext.success();
                        LogD.i("Orchextra se ha inicializado correctamente");
                    }

                    @Override
                    public void onError(String s) {
                        callbackContext.error(0);
                        LogD.i("Se ha producido un error: " + s);
                    }

                    @Override
                    public void onInit(String s) {
                        callbackContext.error(0);
                        LogD.i("Se ha producido un error en la inicializacion: " + s);
                    }
                });

            }
        });
    }

    private void startSdk(final CallbackContext callbackContext) {

        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchextra.start("42cb4ba46f86bdc276940cd9cf2cdf8b7f7f579d", "16163c4041f2f317ac300e582c3fb663982a6e9e");
                callbackContext.success();
            }
        };
    }

    private void openScanner(CallbackContext callbackContext) {
        Orchextra.startScannerActivity();
        callbackContext.success();
    }
}
