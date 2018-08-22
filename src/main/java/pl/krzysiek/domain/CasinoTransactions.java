package pl.krzysiek.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "casino_transactions", schema = "cantor", catalog = "")
public class CasinoTransactions {
    private int id;
    private double amount;
    private String currency;
    private int resultForUser;
    private double wonAmount;
    private int status;
    private Timestamp date;
    private Timestamp lastEditDate;
    private Timestamp finishDate;
    private String privateIndivdalCode;
    private Account accountByUserId;
    private CasinoGameTransactionDetails casinoGameTransactionDetailsByGameSpinId;

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
    @Column(name = "result_for_user")
    public int getResultForUser() {
        return resultForUser;
    }

    public void setResultForUser(int resultForUser) {
        this.resultForUser = resultForUser;
    }

    @Basic
    @Column(name = "won_amount")
    public double getWonAmount() {
        return wonAmount;
    }

    public void setWonAmount(double wonAmount) {
        this.wonAmount = wonAmount;
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
        CasinoTransactions that = (CasinoTransactions) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                resultForUser == that.resultForUser &&
                Double.compare(that.wonAmount, wonAmount) == 0 &&
                status == that.status &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(date, that.date) &&
                Objects.equals(lastEditDate, that.lastEditDate) &&
                Objects.equals(finishDate, that.finishDate) &&
                Objects.equals(privateIndivdalCode, that.privateIndivdalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, resultForUser, wonAmount, status, date, lastEditDate, finishDate, privateIndivdalCode);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public Account getAccountByUserId() {
        return accountByUserId;
    }

    public void setAccountByUserId(Account accountByUserId) {
        this.accountByUserId = accountByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "game_spin_id", referencedColumnName = "id", nullable = false)
    public CasinoGameTransactionDetails getCasinoGameTransactionDetailsByGameSpinId() {
        return casinoGameTransactionDetailsByGameSpinId;
    }

    public void setCasinoGameTransactionDetailsByGameSpinId(CasinoGameTransactionDetails casinoGameTransactionDetailsByGameSpinId) {
        this.casinoGameTransactionDetailsByGameSpinId = casinoGameTransactionDetailsByGameSpinId;
    }
}
