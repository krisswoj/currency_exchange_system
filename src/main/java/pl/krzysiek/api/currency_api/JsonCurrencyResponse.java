package pl.krzysiek.api.currency_api;

import com.google.gson.annotations.SerializedName;

public class JsonCurrencyResponse {

    private Query query;
    private Results results;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }


    public class Query {

        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    public class Results {

        @SerializedName(value = "USD_PLN", alternate = {"EUR_PLN", "PLN_USD", "PLN_EUR"})
        private CurrencyRateJson currencyRateJson;

        public CurrencyRateJson getCurrencyRate() {
            return currencyRateJson;
        }

        public void setCurrencyRate(CurrencyRateJson currencyRateJson) {
            this.currencyRateJson = currencyRateJson;
        }
    }

    public class CurrencyRateJson {

        @SerializedName("id")
        private String currencyPair;
        private Double val;
        private String to;
        private String fr;

        public String getCurrencyPair() {
            return currencyPair;
        }

        public void setCurrencyPair(String currencyPair) {
            this.currencyPair = currencyPair;
        }

        public Double getVal() {
            return val;
        }

        public void setVal(Double val) {
            this.val = val;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getFr() {
            return fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }
    }


}
