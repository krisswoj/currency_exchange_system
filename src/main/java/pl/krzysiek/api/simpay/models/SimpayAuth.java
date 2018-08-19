package pl.krzysiek.api.simpay.models;


public class SimpayAuth
{
    private String key;
    private String secret;

    public SimpayAuth(String key, String secret)
    {
        this.key = key;
        this.secret = secret;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    @Override
    public String toString()
    {
        return "SimpayAuth{" + "key='" + key + '\'' + ", secret='" + secret + '\'' + '}';
    }
}
