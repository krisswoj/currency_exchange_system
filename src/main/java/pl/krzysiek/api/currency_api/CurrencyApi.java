package pl.krzysiek.api.currency_api;

import com.google.gson.*;
import org.json.JSONException;
import pl.krzysiek.api.simpay.utils.SkipArrayAdapterFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import pl.krzysiek.domain.CurrencyRate;

public class CurrencyApi {

    private static final Gson GSON = new GsonBuilder().registerTypeAdapterFactory(new SkipArrayAdapterFactory()).create();

    public CurrencyApi() {

    }


    public CurrencyRate getActuallyRate(String currencyFrom, String currencyTo) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("https://free.currencyconverterapi.com/api/v6/convert?q="+currencyFrom+"_"+currencyTo).openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");
        String jsonString = readStream(connection.getInputStream());

        Gson gson = new GsonBuilder().create();
        JsonCurrencyResponse jsonCurrencyResponse = gson.fromJson(jsonString, JsonCurrencyResponse.class);

        CurrencyRate currencyRate = new CurrencyRate(jsonCurrencyResponse.getResults().getCurrencyRate().getCurrencyPair(), jsonCurrencyResponse.getResults().getCurrencyRate().getVal(), jsonCurrencyResponse.getResults().getCurrencyRate().getFr(), jsonCurrencyResponse.getResults().getCurrencyRate().getTo());

        return currencyRate;
    }


    public String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }


}
