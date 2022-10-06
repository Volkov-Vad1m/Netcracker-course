package wolf.person;

public class Person {
    
    private String firstName;
    private String lastName;
    private String middleName;

    public Person(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public Person(String lastName, String firstName) {
        this(lastName, firstName, null);
    }

    public Person(String lastName) {
        this(lastName, null, null);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(lastName);

        if (firstName != null) {

           result.append(" ")
                   .append(firstName.charAt(0))
                   .append(".");

           if (middleName != null) {
               result.append(" ")
                       .append(middleName.charAt(0)).append(".");
           }

        }

        return new String(result);

    }

}
