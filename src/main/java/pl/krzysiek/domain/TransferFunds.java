package pl.krzysiek.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "transfer_funds", schema = "cantor", catalog = "")
public class TransferFunds {
    private int id;
    private double amount;
    private String currency;
    private double fee;
    private int status;
    private Timestamp date;
    private Timestamp lastEditDate;
    private Timestamp finishDate;
    private String privateIndivdalCode;
    private Account accountByUserIdFrom;
    private Account accountByUserIdTo;

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
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        TransferFunds that = (TransferFunds) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                Double.compare(that.fee, fee) == 0 &&
                status == that.status &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(date, that.date) &&
                Objects.equals(lastEditDate, that.lastEditDate) &&
                Objects.equals(finishDate, that.finishDate) &&
                Objects.equals(privateIndivdalCode, that.privateIndivdalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, fee, status, date, lastEditDate, finishDate, privateIndivdalCode);
    }

    @ManyToOne
    @JoinColumn(name = "user_id_from", referencedColumnName = "user_id", nullable = false)
    public Account getAccountByUserIdFrom() {
        return accountByUserIdFrom;
    }

    public void setAccountByUserIdFrom(Account accountByUserIdFrom) {
        this.accountByUserIdFrom = accountByUserIdFrom;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_to", referencedColumnName = "user_id", nullable = false)
    public Account getAccountByUserIdTo() {
        return accountByUserIdTo;
    }

    public void setAccountByUserIdTo(Account accountByUserIdTo) {
        this.accountByUserIdTo = accountByUserIdTo;
    }
}
