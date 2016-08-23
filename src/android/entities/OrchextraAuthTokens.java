package orchextra.entities;

public class OrchextraAuthTokens {

    private String key;
    private String secret;
    private String logLevel;

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return logLevel;
    }
}
