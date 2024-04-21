package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {
    @Id
    @Column(name= "countryShortcut",insertable=false)
    private String countryShortcut;

    @Column(name= "countryName",insertable=false)
    private String countryName;

    public String getCountryShortcut() {
        return countryShortcut;
    }

    public void setCountryShortcut(String countryShortcut) {
        this.countryShortcut = countryShortcut;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (countryShortcut != null ? !countryShortcut.equals(country.countryShortcut) : country.countryShortcut != null)
            return false;
        return countryName != null ? countryName.equals(country.countryName) : country.countryName == null;
    }

    @Override
    public int hashCode() {
        int result = countryShortcut != null ? countryShortcut.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }
}
