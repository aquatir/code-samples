package learn_to_code.frameworks.hibernate.many_to_one_to_many_with_map;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table
public class Company {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "company_contract",
            joinColumns = {@JoinColumn(name = "company_id")})
    @MapKey(name = "id") // Use id property of CompanyWorker as a key to map
    private Map<CompanyWorker, CompanyContract> contracts;

    public Company() {};
    public Company (String name) {
        this.name = name;
        contracts = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<CompanyWorker, CompanyContract> getContracts() {
        return contracts;
    }

    public void setContracts(Map<CompanyWorker, CompanyContract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (!name.equals(company.name)) return false;
        return contracts != null ? contracts.equals(company.contracts) : company.contracts == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (contracts != null ? contracts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String companyContracts = contracts.keySet().stream().collect(
                StringBuilder::new, (prevValues, newValue) -> prevValues.append(newValue).append("\n"),
                (streamOneResult, streamTwoResult) -> streamOneResult.append(streamTwoResult)).toString(); /* will never be called
                is case of linear stream */
        return String.format("%3d %20s %s", getId(), getName(), companyContracts);
    }
}
