package orchextra;

import android.app.Application;

import orchextra.sdk.OrchextraSdk;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OrchextraSdk.initSdk(this);
    }
}
