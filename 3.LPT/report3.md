# Отчёт к учебному практическому заданию №3 #
_Работу выполнил Волков Вадим, студент группы Б01-007_  
Отчёт лучше смотреть [`здесь`](https://github.com/Volkov-Vad1m/Netcracker-course/blob/master/3.LPT/report3.md).  
Программный код [`тут`](https://github.com/Volkov-Vad1m/Netcracker-course/tree/master/3.LPT).  

## 1. Генератор SQL-инструкций. ##
Необходимо разработать программу, формирующую инструкцию SQL, которая вставляет строки в таблицу __T_GroupSelected <id_Student, firstName, lastName, id_Group>__ из таблицы __T_Student<id_Student, firstName, lastName, id_Group, dolgCount >__ тех студентов, которые относятся к некоторой группе (строковый параметр `group`) и количество долгов (целочисленный параметр) которых превышает заданное значение `nDebts`.
Реализация метода:
```java
public static String build(String group, int debts) {
    StringBuilder statment = new StringBuilder();

    statment.append("INSERT INTO T_GroupSelected (id_Student, firstName, lastName, id_Group)\n")
            .append("SELECT id_Student, firstName, lastName, id_Group FROM T_Student\n")
            .append("WHERE T_Student.id_Group = '").append(group)
            .append("' AND T_Student.dolgCount > ").append(debts)
            .append(";");
            
    return new String(statment);
}
```

## 2. Класс  Person.
Вновь необходимо разработать класс __Person__, в котором имеется функция, возвращающая __Фамилию И.О__. необходимо оптимизировать программу с точки зрения быстродействия. 
Поля класса:  
```java
private String firstName;
private String lastName;
private String middleName;
```

Для начала реализуем конструкторы класса, принимающие различное число аргументов.  
```java
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
```
Переопределим метод __toString()__, который и будет возвращать __Фамилию И.О__ в корректном формате:
```java
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
```
Программа оптимизирована с точки зрения быстродействия, так как мы используем StringBuilder.
## 3. Класс Address.

Необходимо доработать класс __Address__, реализовав метод, который из строки извлекает аттрибуты адреса.  
В классе есть следующие конструкторы:
```java
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
```
Рассмотрим первый метод. В качестве разделителя используется ','
```java
public static Address toAddressSplit(String address) {
    String[] attributes = address.split(",");

    if (attributes.length != 7) {
        throw new IllegalArgumentException("missing attributes");
    }
    return new Address(attributes);
}
```
Реализация метода, с использованием __String Tikenizer__:
```java
public static Address toAddressTokenizer(String address) {
    StringTokenizer attributes = new StringTokenizer(address, ",.;-");

    if(attributes.countTokens() != 7) {
        throw new IllegalArgumentException("missing attributes");
    }

    String[] params = new String[7];

    for (int i = 0; i < 7; i++) {
        params[i] = attributes.nextToken();
    }

    return new Address(params);
}
```
Проверочный класс:
```java
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
```
## 4. Класс Shirt.
Поля класса:
```java
private String id;
private String description;
private String color;
private String size;
```
Конструктор:
```java
public Shirt(String id, String description, String color, String size) {
        this.id = id;
        this.description = description;
        this.color = color;
        this.size = size;
    }
```
В классе есть методы, конвертирующий строковый массив в массив типа __Shirt__
```java
public static Shirt[] converterToShirt(String[] stringShirts) {

    Shirt[] convertedShirts = new Shirt[stringShirts.length];

    for(int i = 0; i < stringShirts.length; i++) {

        String[] splitString = stringShirts[i].split(",");

        convertedShirts[i] = new Shirt(splitString[0], splitString[1],
                splitString[2], splitString[3]);
    }

    return convertedShirts;
}
```
Переопределим метод __toString()__:
```java
@Override
public String toString() {
    return "id='" + id + '\'' +
            ", description='" + description + '\'' +
            ", color='" + color + '\'' +
            ", size='" + size + '\'';
}
```

Необзодимо преобразовать строковый массив в массив класса __Shirt__:
```java
public class Main {
    public static void main(String[] args) {
        String[] shirts = new String[11];

        shirts[0] = "S001,Black Polo Shirt,Black,XL";
        shirts[1] = "S002,Black Polo Shirt,Black,L";
        shirts[2] = "S003,Blue Polo Shirt,Blue,XL";
        shirts[3] = "S004,Blue Polo Shirt,Blue,M";
        shirts[4] = "S005,Tan Polo Shirt,Tan,XL";
        shirts[5] = "S006,Black T-Shirt,Black,XL";
        shirts[6] = "S007,White T-Shirt,White,XL";
        shirts[7] = "S008,White T-Shirt,White,L";
        shirts[8] = "S009,Green T-Shirt,Green,S";
        shirts[9] = "S010,Orange T-Shirt,Orange,S";
        shirts[10] = "S011,Maroon Polo Shirt,Maroon,S";

        Shirt[] convertedShirts = Shirt.converterToShirt(shirts);

        for(Shirt shirt : convertedShirts) {
            System.out.println(shirt);
            System.out.println("-------------------------------------------------------------------");
        }
    }
}
```

## 5. Телефонный номер
Необходимо разработать класс (__PhoneNumber__), который получает строковое представление телефонного номера в одном из двух возможных строковых форматов:
    __+<Код страны><Номер 10 цифр>__
    __8<Номер 10 цифр>__ (для России)
и преобразует полученную строку в формат:
__+<Код страны><Три цифры>–<Три цифры>–<Четыре цифры>__

Класс содержит поле __phone__
```java
private final String phone;
```
Конструктор класса принимает на вход строку, преобразует в правильный формат и заполняет поле __phone__
```java
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
```

Для проверки работы добавим метод __toString()__, который просто возвращает поле __phone__:
```java
@Override
public String toString() {
    return phone;
}
```

