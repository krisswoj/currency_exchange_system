package pl.krzysiek.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "currency", schema = "cantor", catalog = "")
public class Currency {


    private int id;
    private String currencyPair;
    private double rateValue;
    private String fromCurrency;
    private String toCurrency;
    private Timestamp addDate;

    public Currency(String currencyPair, Double rateValue, String fromCurrency, String toCurrency) {
        this.currencyPair = currencyPair;
        this.rateValue = rateValue;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Currency(String currencyPair, String fromCurrency, String toCurrency) {
        this.currencyPair = currencyPair;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Currency() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pair_name")
    public String getPairName() {
        return currencyPair;
    }

    public void setPairName(String pairName) {
        this.currencyPair = pairName;
    }

    @Basic
    @Column(name = "rate_value")
    public double getRateValue() {
        return rateValue;
    }

    public void setRateValue(double rateValue) {
        this.rateValue = rateValue;
    }

    @Basic
    @Column(name = "from_currency")
    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    @Basic
    @Column(name = "to_currency")
    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    @Basic
    @Column(name = "add_date")
    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency that = (Currency) o;
        return id == that.id &&
                Objects.equals(currencyPair, that.currencyPair) &&
                Objects.equals(rateValue, that.rateValue) &&
                Objects.equals(fromCurrency, that.fromCurrency) &&
                Objects.equals(toCurrency, that.toCurrency) &&
                Objects.equals(addDate, that.addDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyPair, rateValue, fromCurrency, toCurrency, addDate);
    }
}
