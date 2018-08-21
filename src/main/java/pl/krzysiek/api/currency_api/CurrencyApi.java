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

//        Type listType = new TypeToken<ArrayList<Currency>>(){}.getType();
//        List<Currency> yourClassList = new Gson().fromJson(jsonString, listType);


//        Type mapType = new TypeToken<Map<String, Results>>() {}.getType(); // define generic type
//        Map<String, Results> results = gson.fromJson(testJson, mapType);

//        String json = testJson;
//
//        Type aType = new TypeToken<Map<String, ArrayList<Object>>>() {
//        }.getType();
//        Map<String, ArrayList<Object>> map = new Gson().fromJson(json, aType);
//
//        Type fooType = new TypeToken<Results<Results.CurrencyDetails>>() {
//        }.getType();
//        gson.fromJson(testJson, fooType);

//        Type collectionType = new TypeToken<Collection<Results>>(){}.getType();
//        Collection<Results> ints2 = gson.fromJson(testJson, collectionType);


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


//    private Type getType(final Class<?> rawClass, final Class<?> parameterClass) {
//        return new ParameterizedType() {
//            @Override
//            public Type[] getActualTypeArguments() {
//                return new Type[]{parameterClass};
//            }
//
//            @Override
//            public Type getRawType() {
//                return rawClass;
//            }
//
//            @Override
//            public Type getOwnerType() {
//                return null;
//            }
//
//        };
//
//    }

//    public class CustomJsonDeserializer<T> implements JsonDeserializer<JsonCurrencyResponse<T>> {
//
//        private final Class<T> clazz;
//
//        public CustomJsonDeserializer(Class<T> clazz) {
//            this.clazz = clazz;
//        }
//
//        @Override
//        public JsonCurrencyResponse<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            JsonObject body = json.getAsJsonObject().getAsJsonObject("results");
//            JsonArray arr = body.entrySet().iterator().next().getValue().getAsJsonArray();
//            List<T> list = new ArrayList<>();
//            for(JsonElement element : arr) {
//                JsonElement innerElement = element.getAsJsonObject().entrySet().iterator().next().getValue();
//                list.add(context.deserialize(innerElement, clazz));
//            }
//            return new JsonCurrencyResponse<T>((T) list);
//        }
//    }


}
