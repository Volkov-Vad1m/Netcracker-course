package wolf.address;

public class Main {

    public static void main(String[] args) {
        String address1 = "Россия, Орловская область, Ливны, Мира, 123, 1, 123";

        String address2 = "Россия; Московская область, Долгопрудный, Мира; 123- 1, 123";

        String address3 = "Россия-         Брянская область, Брянск, Мира; 177- 7. 999";

        String address4 = "Беларусь-         Минская область, Минск, Мира; 92- 1. 93                    ";

        System.out.println(Address.toAddressSplit(address1));
        System.out.println(Address.toAddressTokenizer(address2));
        System.out.println(Address.toAddressTokenizer(address3));
        System.out.println(Address.toAddressTokenizer(address4));
    }
}
