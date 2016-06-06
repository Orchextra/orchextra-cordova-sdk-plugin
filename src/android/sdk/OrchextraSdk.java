package orchextra.sdk;

import android.app.Application;

import com.gigigo.ggglogger.GGGLogImpl;
import com.gigigo.orchextra.ORCUser;
import com.gigigo.orchextra.Orchextra;
import com.gigigo.orchextra.OrchextraCompletionCallback;

import org.apache.cordova.CallbackContext;

import orchextra.entities.OrchextraAuthTokens;

public class OrchextraSdk {

    private OrchextraAuthTokens orchextraAuthTokens;

    public void initSdk(final Application application, OrchextraAuthTokens orchextraAuthTokens, final CallbackContext callbackContext) {
        this.orchextraAuthTokens = orchextraAuthTokens;

        Orchextra.init(application, new OrchextraCompletionCallback() {
            @Override
            public void onSuccess() {
                callbackContext.success();
                GGGLogImpl.log("Orchextra started successful");
            }

            @Override
            public void onError(String s) {
                callbackContext.error(0);
                GGGLogImpl.log("Orchextra not started due to:" + s);
            }

            @Override
            public void onInit(String s) {
                GGGLogImpl.log("Orchextra initialization finished, details:" + s);
            }
        });
    }

    public boolean startSdk() {
        if (orchextraAuthTokens != null) {
            Orchextra.start(orchextraAuthTokens.getKey(), orchextraAuthTokens.getSecret());
            return true;
        } else {
            return false;
        }
    }

    public void setUser(ORCUser orcUser) {
        Orchextra.setUser(orcUser);
    }
}
