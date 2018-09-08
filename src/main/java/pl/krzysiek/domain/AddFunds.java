package pl.krzysiek.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "add_funds", schema = "cantor", catalog = "")
public class AddFunds {
    private int id;
    private double amount;
    private String currency;
    private int paymentMethod;
    private int status;
    private Timestamp date;
    private Timestamp lastEditDate;
    private Timestamp finishDate;
    private String privateIndivdalCode;
    private String transactionIdFromPaymentSystem;
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
    @Column(name = "payment_method")
    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    @Basic
    @Column(name = "transaction_id_from_payment_system")
    public String getTransactionIdFromPaymentSystem() {
        return transactionIdFromPaymentSystem;
    }

    public void setTransactionIdFromPaymentSystem(String transactionIdFromPaymentSystem) {
        this.transactionIdFromPaymentSystem = transactionIdFromPaymentSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddFunds addFunds = (AddFunds) o;
        return id == addFunds.id &&
                Double.compare(addFunds.amount, amount) == 0 &&
                paymentMethod == addFunds.paymentMethod &&
                status == addFunds.status &&
                Objects.equals(currency, addFunds.currency) &&
                Objects.equals(date, addFunds.date) &&
                Objects.equals(lastEditDate, addFunds.lastEditDate) &&
                Objects.equals(finishDate, addFunds.finishDate) &&
                Objects.equals(privateIndivdalCode, addFunds.privateIndivdalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, paymentMethod, status, date, lastEditDate, finishDate, privateIndivdalCode);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public Account getAccountByUserId() {
        return accountByUserId;
    }

    public void setAccountByUserId(Account accountByUserId) {
        this.accountByUserId = accountByUserId;
    }

    public AddFunds() {
    }

    public AddFunds(double amount, String currency, int paymentMethod, int status, String privateIndivdalCode, String transactionIdFromPaymentSystem, Account accountByUserId, Timestamp date) {
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.privateIndivdalCode = privateIndivdalCode;
        this.transactionIdFromPaymentSystem = transactionIdFromPaymentSystem;
        this.accountByUserId = accountByUserId;
        this.date = date;
    }

    public static class AddFundsBySms {

        private String smsCode;
        private int premiumNumber;
        private int serviceNumber;

        public String getSmsCode() {
            return smsCode;
        }

        public void setSmsCode(String smsCode) {
            this.smsCode = smsCode;
        }

        public int getPremiumNumber() {
            return premiumNumber;
        }

        public void setPremiumNumber(int premiumNumber) {
            this.premiumNumber = premiumNumber;
        }

        public int getServiceNumber() {
            return serviceNumber;
        }

        public void setServiceNumber(int serviceNumber) {
            this.serviceNumber = serviceNumber;
        }
    }
}


