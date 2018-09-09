package pl.krzysiek.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "currency_transaction", schema = "cantor", catalog = "")
public class CurrencyTransaction {
    private int id;
    private String currencyFrom;
    private String currencyTo;
    private double amountOriginalCurrency;
    private double amountBoughtCurrency;
    private double baseCurrencyRate;
    private double currencyRateWithFee;
    private double feeRate;
    private double fee;
    private int kindOfOperation;
    private int status;
    private Timestamp date;
    private Timestamp lastEditDate;
    private Timestamp finishDate;
    private String privateIndivdalCode;
    private Account accountByUserId;

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
    @Column(name = "currency_from")
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    @Basic
    @Column(name = "currency_to")
    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    @Basic
    @Column(name = "amount_original_currency")
    public double getAmountOriginalCurrency() {
        return amountOriginalCurrency;
    }

    public void setAmountOriginalCurrency(double amountOriginalCurrency) {
        this.amountOriginalCurrency = amountOriginalCurrency;
    }

    @Basic
    @Column(name = "amount_bought_currency")
    public double getAmountBoughtCurrency() {
        return amountBoughtCurrency;
    }

    public void setAmountBoughtCurrency(double amountBoughtCurrency) {
        this.amountBoughtCurrency = amountBoughtCurrency;
    }

    @Basic
    @Column(name = "base_currency_rate")
    public double getBaseCurrencyRate() {
        return baseCurrencyRate;
    }

    public void setBaseCurrencyRate(double baseCurrencyRate) {
        this.baseCurrencyRate = baseCurrencyRate;
    }

    @Basic
    @Column(name = "currency_rate_with_fee")
    public double getCurrencyRateWithFee() {
        return currencyRateWithFee;
    }

    public void setCurrencyRateWithFee(double currencyRateWithFee) {
        this.currencyRateWithFee = currencyRateWithFee;
    }

    @Basic
    @Column(name = "fee_rate")
    public double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(double feeRate) {
        this.feeRate = feeRate;
    }

    @Basic
    @Column(name = "kind_of_operation")
    public int getKindOfOperation() {
        return kindOfOperation;
    }

    public void setKindOfOperation(int kindOfOperation) {
        this.kindOfOperation = kindOfOperation;
    }

    @Basic
    @Column(name = "fee")
    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "last_edit_date")
    public Timestamp getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Timestamp lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    @Basic
    @Column(name = "finish_date")
    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    @Basic
    @Column(name = "private_indivdal_code")
    public String getPrivateIndivdalCode() {
        return privateIndivdalCode;
    }

    public void setPrivateIndivdalCode(String privateIndivdalCode) {
        this.privateIndivdalCode = privateIndivdalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyTransaction that = (CurrencyTransaction) o;
        return id == that.id &&
                Double.compare(that.amountOriginalCurrency, amountOriginalCurrency) == 0 &&
                Double.compare(that.amountBoughtCurrency, amountBoughtCurrency) == 0 &&
                Double.compare(that.fee, fee) == 0 &&
                status == that.status &&
                Objects.equals(currencyFrom, that.currencyFrom) &&
                Objects.equals(currencyTo, that.currencyTo) &&
                Objects.equals(date, that.date) &&
                Objects.equals(lastEditDate, that.lastEditDate) &&
                Objects.equals(finishDate, that.finishDate) &&
                Objects.equals(privateIndivdalCode, that.privateIndivdalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyFrom, currencyTo, amountOriginalCurrency, amountBoughtCurrency, fee, status, date, lastEditDate, finishDate, privateIndivdalCode);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public Account getAccountByUserId() {
        return accountByUserId;
    }

    public void setAccountByUserId(Account accountByUserId) {
        this.accountByUserId = accountByUserId;
    }


    public static class CurrencyCalculations{

        private String fromCurrency;
        private String toCurrency;
        private Double fromCurrencyAmount;
        private Double toCurrencyAmount;
        private Double feeRate;
        private Double fee;
        private Double baseCurrencyRate;
        private Double currencyRateWithFee;
        private int kindOfOperation;

        public String getFromCurrency() {
            return fromCurrency;
        }

        public void setFromCurrency(String fromCurrency) {
            this.fromCurrency = fromCurrency;
        }

        public String getToCurrency() {
            return toCurrency;
        }

        public void setToCurrency(String toCurrency) {
            this.toCurrency = toCurrency;
        }

        public Double getFromCurrencyAmount() {
            return fromCurrencyAmount;
        }

        public void setFromCurrencyAmount(Double fromCurrencyAmount) {
            this.fromCurrencyAmount = fromCurrencyAmount;
        }

        public Double getToCurrencyAmount() {
            return toCurrencyAmount;
        }

        public void setToCurrencyAmount(Double toCurrencyAmount) {
            this.toCurrencyAmount = toCurrencyAmount;
        }

        public Double getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(Double feeRate) {
            this.feeRate = feeRate;
        }

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
        }

        public Double getBaseCurrencyRate() {
            return baseCurrencyRate;
        }

        public void setBaseCurrencyRate(Double baseCurrencyRate) {
            this.baseCurrencyRate = baseCurrencyRate;
        }

        public Double getCurrencyRateWithFee() {
            return currencyRateWithFee;
        }

        public void setCurrencyRateWithFee(Double currencyRateWithFee) {
            this.currencyRateWithFee = currencyRateWithFee;
        }

        public int getKindOfOperation() {
            return kindOfOperation;
        }

        public void setKindOfOperation(int kindOfOperation) {
            this.kindOfOperation = kindOfOperation;
        }

        public CurrencyCalculations(String fromCurrency, String toCurrency, Double feeRate, Double baseCurrencyRate, int kindOfOperation) {
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.feeRate = feeRate;
            this.baseCurrencyRate = baseCurrencyRate;
            this.kindOfOperation = kindOfOperation;
        }

        public CurrencyCalculations() {
        }
    }
}
