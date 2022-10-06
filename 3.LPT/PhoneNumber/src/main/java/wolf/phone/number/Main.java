package wolf.phone.number;

public class Main {
    public static void main(String[] args) {
        String[] numbers = new String[3];
        numbers[0] = "+79175655655";
        numbers[1] = "+104289652211";
        numbers[2] = "89175655655";

        for (String number : numbers) {
            System.out.println(new PhoneNumber(number));
        }

    }
}
