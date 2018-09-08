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
    private double fee;
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
}
