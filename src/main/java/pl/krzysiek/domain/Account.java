package pl.krzysiek.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "account", schema = "cantor", catalog = "")
public class Account {
    private int userId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer active;
    private double plnBalance;
    private double usdBalance;
    private double eurBalance;
    private double cnyBalance;
    private double gbpBalance;
    private Collection<AddFunds> addFundsByUserId;
    private Collection<CasinoTransactions> casinoTransactionsByUserId;
    private Collection<CurrencyTransaction> currencyTransactionsByUserId;
    private Collection<TransferFunds> transferFundsByUserIdFrom;
    private Collection<TransferFunds> transferFundsByUserIdTo;
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "pln_balance")
    public double getPlnBalance() {
        return plnBalance;
    }

    public void setPlnBalance(double plnBalance) {
        this.plnBalance = plnBalance;
    }

    @Basic
    @Column(name = "usd_balance")
    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }

    @Basic
    @Column(name = "eur_balance")
    public double getEurBalance() {
        return eurBalance;
    }

    public void setEurBalance(double eurBalance) {
        this.eurBalance = eurBalance;
    }

    @Basic
    @Column(name = "cny_balance")
    public double getCnyBalance() {
        return cnyBalance;
    }

    public void setCnyBalance(double cnyBalance) {
        this.cnyBalance = cnyBalance;
    }

    @Basic
    @Column(name = "gbp_balance")
    public double getGbpBalance() {
        return gbpBalance;
    }

    public void setGbpBalance(double gbpBalance) {
        this.gbpBalance = gbpBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userId == account.userId &&
                Double.compare(account.plnBalance, plnBalance) == 0 &&
                Double.compare(account.usdBalance, usdBalance) == 0 &&
                Double.compare(account.eurBalance, eurBalance) == 0 &&
                Double.compare(account.cnyBalance, cnyBalance) == 0 &&
                Double.compare(account.gbpBalance, gbpBalance) == 0 &&
                Objects.equals(name, account.name) &&
                Objects.equals(surname, account.surname) &&
                Objects.equals(email, account.email) &&
                Objects.equals(password, account.password) &&
                Objects.equals(active, account.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surname, email, password, active, plnBalance, usdBalance, eurBalance, cnyBalance, gbpBalance);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonBackReference
    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    @OneToMany(mappedBy = "accountByUserId")
    public Collection<AddFunds> getAddFundsByUserId() {
        return addFundsByUserId;
    }

    public void setAddFundsByUserId(Collection<AddFunds> addFundsByUserId) {
        this.addFundsByUserId = addFundsByUserId;
    }

    @OneToMany(mappedBy = "accountByUserId")
    public Collection<CasinoTransactions> getCasinoTransactionsByUserId() {
        return casinoTransactionsByUserId;
    }

    public void setCasinoTransactionsByUserId(Collection<CasinoTransactions> casinoTransactionsByUserId) {
        this.casinoTransactionsByUserId = casinoTransactionsByUserId;
    }

    @OneToMany(mappedBy = "accountByUserId")
    public Collection<CurrencyTransaction> getCurrencyTransactionsByUserId() {
        return currencyTransactionsByUserId;
    }

    public void setCurrencyTransactionsByUserId(Collection<CurrencyTransaction> currencyTransactionsByUserId) {
        this.currencyTransactionsByUserId = currencyTransactionsByUserId;
    }

    @OneToMany(mappedBy = "accountByUserIdFrom")
    public Collection<TransferFunds> getTransferFundsByUserIdFrom() {
        return transferFundsByUserIdFrom;
    }

    public void setTransferFundsByUserIdFrom(Collection<TransferFunds> transferFundsByUserId) {
        this.transferFundsByUserIdFrom = transferFundsByUserId;
    }

    @OneToMany(mappedBy = "accountByUserIdTo")
    public Collection<TransferFunds> getTransferFundsByUserIdTo() {
        return transferFundsByUserIdTo;
    }

    public void setTransferFundsByUserIdTo(Collection<TransferFunds> transferFundsByUserId_0) {
        this.transferFundsByUserIdTo = transferFundsByUserId_0;
    }
}
