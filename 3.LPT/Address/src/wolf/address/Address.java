package wolf.address;


import java.util.Iterator;
import java.util.StringTokenizer;

public class Address {
    
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String building;
    private String apartment;

    public Address(String country, String region, String city, String street, String house, String building, String apartment) {
        this.country = country.trim();
        this.region = region.trim();
        this.city = city.trim();
        this.street = street.trim();
        this.house = house.trim();
        this.building = building.trim();
        this.apartment = apartment.trim();
    }
    private Address(String[] attributes) {
        this(attributes[0],
                attributes[1],
                attributes[2],
                attributes[3],
                attributes[4],
                attributes[5],
                attributes[6]);
    }

    public static Address toAddressSplit(String address) {

        String[] attributes = address.split(",");

        if (attributes.length != 7) {
            throw new IllegalArgumentException("missing attributes");
        }

        return new Address(attributes);

    }

    public static Address toAddressTokenizer(String address) {
        StringTokenizer attributes = new StringTokenizer(address, ",.;-");

        if(attributes.countTokens() != 7) {
            throw new IllegalArgumentException("missing attributes");
        }

        String[] params = new String[7];

        for (int i = 0; i < 7; i++) {
            params[i] = attributes.nextToken().trim();
        }

        return new Address(params);
    }


    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
