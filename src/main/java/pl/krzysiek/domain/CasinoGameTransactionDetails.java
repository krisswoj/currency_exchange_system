package pl.krzysiek.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "casino_game_transaction_details", schema = "cantor", catalog = "")
public class CasinoGameTransactionDetails {
    private int id;
    private String results;
    private String usedHash;
    private String privateIndivdalCode;

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
    @Column(name = "results")
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    @Basic
    @Column(name = "used_hash")
    public String getUsedHash() {
        return usedHash;
    }

    public void setUsedHash(String usedHash) {
        this.usedHash = usedHash;
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
        CasinoGameTransactionDetails that = (CasinoGameTransactionDetails) o;
        return id == that.id &&
                Objects.equals(results, that.results) &&
                Objects.equals(usedHash, that.usedHash) &&
                Objects.equals(privateIndivdalCode, that.privateIndivdalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, results, usedHash, privateIndivdalCode);
    }
}
