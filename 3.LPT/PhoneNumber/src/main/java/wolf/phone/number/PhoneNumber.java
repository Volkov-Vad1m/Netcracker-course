package wolf.phone.number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {

    private final String phone;

    public PhoneNumber(String phone) {

        String regex = "(\\+\\d+|8)(\\d{3})(\\d{3})(\\d{4})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        if(matcher.matches()) {

            String code = matcher.group(1);

            if(code.equals("8")) {
                code = "+7";
            }

            this.phone = code + matcher.group(2) + "-"
                    + matcher.group(3) + "-" + matcher.group(4);

        } else {
            throw new IllegalArgumentException("not a phone number");
        }

    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return phone;
    }
}
