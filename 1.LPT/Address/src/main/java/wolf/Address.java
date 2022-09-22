package wolf;

import org.jetbrains.annotations.NotNull;

public class Address {

    private String country;
    private String city;
    private String street;
    private String building;
    //////////////////////////////////////////////////
    public Address(@NotNull String country, @NotNull String city, @NotNull String street, @NotNull String building) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }
    //////////////////////////////////////////////////
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
    //////////////////////////////////////////////////

    public boolean equals(Object otherAddress) {
        if(!(otherAddress instanceof Address)) {
            return false;
        }
        return this.building.equals(( (Address) otherAddress).building) &&
                this.street.equals(( (Address) otherAddress).street) &&
                this.city.equals(( (Address) otherAddress).city) &&
                this.country.equals(( (Address) otherAddress).country);
    }




}



