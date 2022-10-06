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
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.building = building;
        this.apartment = apartment;
    }
    private Address(String[] attributes) {
        this(attributes[0].trim(),
                attributes[1].trim(),
                attributes[2].trim(),
                attributes[3].trim(),
                attributes[4].trim(),
                attributes[5].trim(),
                attributes[6].trim());
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
