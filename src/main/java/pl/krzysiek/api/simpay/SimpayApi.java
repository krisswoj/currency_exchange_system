package pl.krzysiek.api.simpay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.krzysiek.api.simpay.connection.SimpayStatusRequest;
import pl.krzysiek.api.simpay.connection.SimpayStatusResponse;
import pl.krzysiek.api.simpay.models.SimpayAuth;
import pl.krzysiek.api.simpay.models.SimpayStatusParameters;
import pl.krzysiek.api.simpay.utils.SkipArrayAdapterFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SimpayApi {
    private static final Gson GSON = new GsonBuilder().registerTypeAdapterFactory(new SkipArrayAdapterFactory()).create();

    private static final int SIMPAY_API_VERSION = 1;
    private final SimpayAuth auth;
    private final int version;

    public SimpayApi(String key, String secret) {
        this(key, secret, SIMPAY_API_VERSION);
    }

    public SimpayApi(String key, String secret, int version) {
        this.auth = new SimpayAuth(key, secret);
        this.version = version;
    }

    public SimpayAuth getAuth() {
        return auth;
    }

    public int getVersion() {
        return version;
    }

    public SimpayStatusResponse getStatus(int serviceId, int number, String code) throws IOException {
        SimpayStatusParameters parameters = new SimpayStatusParameters(this.auth, serviceId, number, code);
        SimpayStatusRequest statusRequest = new SimpayStatusRequest(parameters);

        HttpURLConnection connection = (HttpURLConnection) new URL("https://simpay.pl/api/" + this.version + "/status").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.getOutputStream().write(GSON.toJson(statusRequest).getBytes(StandardCharsets.UTF_8));

        return GSON.fromJson(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8), SimpayStatusResponse.class);
    }
}
