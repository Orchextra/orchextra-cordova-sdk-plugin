package orchextra.sdk;

import android.app.Application;

import com.gigigo.orchextra.ORCUser;
import com.gigigo.orchextra.Orchextra;
import com.gigigo.orchextra.OrchextraLogLevel;

import orchextra.entities.OrchextraAuthTokens;

public class OrchextraSdk {

    private OrchextraAuthTokens orchextraAuthTokens;

    public void initSdk(final Application application) {
        Orchextra.setLogLevel(OrchextraLogLevel.ALL);
        Orchextra.init(application, null);
    }

    public void setTokenCredentials(OrchextraAuthTokens orchextraAuthTokens) {
        this.orchextraAuthTokens = orchextraAuthTokens;
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

    public boolean stopSdk() {
        Orchextra.stop();
        return true;
    }

    public void startScanner() {
        Orchextra.startScannerActivity();
    }
}
