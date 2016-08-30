package orchextra.sdk;

import android.app.Application;

import com.gigigo.orchextra.CrmUser;
import com.gigigo.orchextra.Orchextra;
import com.gigigo.orchextra.OrchextraBuilder;
import com.gigigo.orchextra.OrchextraLogLevel;

import orchextra.entities.OrchextraAuthTokens;

public class OrchextraSdk {

    private OrchextraAuthTokens orchextraAuthTokens;

    public static void initSdk(Application application) {
        OrchextraBuilder builder = new OrchextraBuilder(application)
                .setApiKeyAndSecret("null","null")
                .setLogLevel(OrchextraLogLevel.ALL);

        Orchextra.initialize(builder);
    }

    public void setTokenCredentials(Application application, OrchextraAuthTokens orchextraAuthTokens) {
        this.orchextraAuthTokens = orchextraAuthTokens;

        Orchextra.setGcmSendId(application, orchextraAuthTokens.getSenderId());
        Orchextra.start();
    }

    public boolean startSdk() {
        if (orchextraAuthTokens != null) {
            Orchextra.changeCredentials(orchextraAuthTokens.getApiKey(), orchextraAuthTokens.getApiSecret());
            return true;
        } else {
            return false;
        }
    }

    public void setUser(CrmUser orcUser) {
        Orchextra.setUser(orcUser);
    }

    public boolean stopSdk() {
        Orchextra.stop();
        return true;
    }

    public void startScanner() {
        Orchextra.startScannerActivity();
    }
}
