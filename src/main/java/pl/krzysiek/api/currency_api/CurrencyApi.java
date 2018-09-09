package pl.krzysiek.api.currency_api;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import pl.krzysiek.domain.Currency;

@Service
public class CurrencyApi {

    private final Gson gson = new Gson();

    public CurrencyApi() {

    }

    public Currency getActuallyRate(String currencyFrom, String currencyTo) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("https://free.currencyconverterapi.com/api/v6/convert?q=" + currencyFrom + "_" + currencyTo).openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");
        String jsonString = readStream(connection.getInputStream());

        JsonCurrencyResponse jsonCurrencyResponse = gson.fromJson(jsonString, JsonCurrencyResponse.class);
        return new Currency(jsonCurrencyResponse.getResults().getCurrencyRateJson().getCurrencyPair(), jsonCurrencyResponse.getResults().getCurrencyRateJson().getVal(), jsonCurrencyResponse.getResults().getCurrencyRateJson().getFr(), jsonCurrencyResponse.getResults().getCurrencyRateJson().getTo());
    }

    private String readStream(InputStream in) {
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
