# Отчёт к учебному практическому заданию №1 #
_Работу выполнил Волков Вадим, студент группы Б01-007_
Программный код можно найти на [`GitHub`](https://github.com/Volkov-Vad1m/Netcracker-course/tree/master/1.LPT)
## 1. Квадратное уравнение ##
Рассмотрим класс __QuadraticSolver.java__, осуществляющий решения квадратных уравнений:
### 1.1 Поля класса

__EPSILON__ - точность вычислений, будем использовать её для сравнения на ноль. Стандартный метотод __Double.compare()__ слишком точный для наших целей.
```java
public final static double EPSILON = 10E-6; // for comparing
```
Значения коэффициентов квадратного уравнения:
```java
    private double a;
    private double b;
    private double c;
```
Решения уравнения будем хранить в переменных __Apache.Complex__ для удобства обработки всех корней (действительных и комплексных)
```java
   private Complex z1, z2;
```

### 1.2 Методы класса

Конструктор класса, которому необходимы все коэффициенты квадратного уравнения:
```java
   public QuadraticSolver(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
```
В классе реализованы getters и setters к полям __a__,__b__,__c__. При любом их изменении класс обнуляет поля __z1__ и __z2__
Внутренний класс, вычесляющий дискриминант и хранящий его значение.
```java
class Discriminant {
    private double value;
        
    public Discriminant() {
        this.value = b * b - 4 * a * c;
    }
}
```
Метод __isZero(double number)__ сравнивает __number__ с нулём.
```java
private boolean isZero(double number) {
    return Math.abs(number) < EPSILON;
}
```
Далее реализована основная функция решения квадратного уравнения __solve()__, записывающая решения в поля __z1, z2__:
```java
public void solve() {
    /** linear equation */
    if(isZero(a)) {
        z1 = new Complex(-(c/b));
        return;
    }
    /** quadratic equation */
    Discriminant discriminant = this.new Discriminant();

    if(isZero(discriminant.value)) {
        z1 = new Complex(-b / (2 * a) );
        z2 = new Complex(-b / (2 * a) );
    } else if (discriminant.value > 0) {
        z1 = new Complex((-b + Math.sqrt(discriminant.value) ) / (2 * a));
        z2 = new Complex((-b - Math.sqrt(discriminant.value) ) / (2 * a));
    } else {
        z1 = new Complex(-b / (2 * a), Math.sqrt( Math.abs(discriminant.value) ) / (2 * a));
        z2 = z1.conjugate();
    }
}
```

> Дискриминант вычисляется внутренним классом. При компиляции основного класса __QuadraticSolver.java__ создаются 2 файла: файл основного класса __QuadraticSolver.class__ и файл вложенного класса __QuadraticSolver$1Discriminant.class__(то есть класс рассматривается как отдельный).

Метод __printSolutions()__ реализованный в данном классе красиво печатает решения уравнения в консоль. С его кодом лучше ознакомиться непосредственно в [`папке`](https://github.com/Volkov-Vad1m/Netcracker-course/blob/db064ab7e94149d17e453aa4a33284d72bf2f3be/1.LPT/QuadraticEquation/src/main/java/quadratic/equation/QuadraticSolver.java#L96) с исходниками, так как он немного нагроможден, в силу обработки всех возможных случаев.

### 1.3 Тестирование класса
Класс __Main.java__ демонстрирует работу класса:
```java
public class Main {

    public static void main(String[] args) {
        double a;
        double b;
        double c;

        Scanner in = new Scanner(System.in);
        System.out.println("enter the coefficients in the equation ax^2 + bx + c = 0");

        System.out.print("a: ");
        a = in.nextDouble();

        System.out.print("b: ");
        b = in.nextDouble();

        System.out.print("c: ");
        c = in.nextDouble();

        QuadraticSolver solver = new QuadraticSolver(a, b ,c);
        solver.solve();

        solver.printSolutions();
    }
}
```

Помимо метода __main()__ ,добавлены небольшие [`maven тесты`](https://github.com/Volkov-Vad1m/Netcracker-course/blob/db064ab7e94149d17e453aa4a33284d72bf2f3be/1.LPT/QuadraticEquation/src/test/java/quadratic/equation/TestQuadraticSolver.java#L1) вычисления корней уравнения.

## 2. Игра в кости ##
Класс __Dice.java__ с использованием __Player.java__ реализует игру в кости. Рассмотрим класс __Player.java__:

### 2.1 __Player.java__ ###

Класс обладает следующими полями (_см. комментарии_):
```java
/** static variable which counts amount of players */
private static int amountOfPlayers;

private int id;

/** result of tossing cubes in a particular round */
private int tossResult;

private int score;

/** if players is PC - false*/
private boolean isReal = true;
```

Конструктор сам заполняет поле id, в зависимости от колличества игроков:
```java
   public Player() {
        amountOfPlayers++;
        id = amountOfPlayers;
    }
```

Метод __tossesCubes()__ записывает в поле __tossResult__ результат подбрасывания кубиков. Результат рассчитывается иммено так (а не к примеру __random.nextInt(6*cubes)__ ) так как некоторые значения суммы очков выпадают чаще чем другие. Это попытка реализовать реальное распределение значений сумм:
```java 
   public void tossesCubes(int cubes) {
        int result = 0;
        Random random = new Random();

        for(int i = 0; i < cubes; i++) {
            result = result + random.nextInt(6) + 1;
        }

        this.tossResult = result;
    }
```

### 2.1 __Dice.java__ ###
Теперь рассмотрим основной класс, реализующий саму игру в кости:

#### Класс обладает следующими полями:
Количество выигранных игр, необходимое для победы
```java
    private final int roundsToWins = 7;

    private Player[] players;
    private int cubes; // amount of cubes
```

#### Рассмотрим методы класса:

Конструктор, принимающий на вход количество игороков и игральных костей:
```java
public Dice(int players, int cubes) {
    this.players = new Player[players + 1]; //+PC

    for(int i = 0; i < this.players.length; i++) {
        this.players[i] = new Player();
    }

    this.players[players].setReal(false); // PC is not real

    this.cubes = cubes;
}
```

Метод __play()__ реализует логику игры, в процессе работы печатает в консоль промежуточные результаты:
```java
public void play() {

    while(!someoneWon()) {
        System.out.println("-----------------toss!-----------------");
        for (Player player : players) {
            player.tossesCubes(cubes);

            // it is PC
            if (player.isReal()) {
                System.out.print("Player " + player.getId() + ": ");
            } else {
                System.out.print("PC: ");
            }
            System.out.println("the number " + player.getTossResult() + " has been dropped out on the dice");
        }

        endRound();
    }

    for(Player player : players) {
        if(player.getScore() == 7) {
            if(player.isReal()) {
                System.out.println("Player " + player.getId() + " won the game");
            } else {
                System.out.println("PC won the game");
            }
        }
    }

}
```

Метод __someoneWon()__ проверяет, появился ли уже победитель игры или нет:
```java
private boolean someoneWon() {
        for(Player a : players) {
            if(a.getScore() == roundsToWins) {
                return true;
            }
        }
        return false;
    }
```

Метод __endRound()__ корректно завершает раунд игры. Находит победителя, устанавливает счет, печатает результаты и сдвигает массив игроков:
```java
private void endRound() {
    int firstWinner = indexMax(); 

    if (players[firstWinner].isReal()) {
        System.out.println("Player " + (players[firstWinner].getId()) + " won!");
    } else {
        System.out.println("PC won!");
    }

    players[firstWinner].setScore(players[firstWinner].getScore() + 1);

    dumpScores();
    moveArray(players.length - firstWinner);
}
```
Методы __indexMax()__, __dumpScores()__, __moveArray()__ являются вспомогательными методами для __endRound()__. Они не очень интересные, с ними можно ознакомиться [`здесь`](https://github.com/Volkov-Vad1m/Netcracker-course/blob/db064ab7e94149d17e453aa4a33284d72bf2f3be/1.LPT/QuadraticEquation/src/test/java/quadratic/equation/TestQuadraticSolver.java#L1). Эти методы:
*  __indexMax()__ находит индекс массива, где лежит побеитель раунда.
*  __dumpScores()__ печатает итоги раунда и победителя раунда.
*  __moveArray()__ сдвигает массив игроков так, чтобы в __players[0]__ был победитель раунда.
### 2.3 __Main.java__ ###
Этот класс позволяет протестировать работу основного класса __Dice.java__:
```java
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("type number of players");
        int players = in.nextInt();

        System.out.println("How many cubes?");
        int cubes = in.nextInt();
        Dice dealer = new Dice(players, cubes);
        dealer.play();
    }
}
```

## 3. Адрес человека ##

Необходимо реализовать массив объектов типа __Person__ и реализовать в нем некоторые функции. Сначала рассмотрим класс __Person.java__:

### 3.1 __Person.java__
Класс обладает следующими полями:
```java
    private String name;
    private String surname;
    private Calendar birthDay;
    private Address address;
```

Конструкторы:
```java
public Person(@NotNull String name, @NotNull String surname, @NotNull Calendar birthDay, @NotNull Address address) {
    this.name = name;
    this.surname = surname;
    this.birthDay = birthDay;
    this.address = address;
}

public Person(@NotNull String name, @NotNull String surname, int year, int month, int day, @NotNull Address address) {
    this(name, surname, Calendar.getInstance(), address);
    this.birthDay.set(year, month - 1, day);

}
```

Кроме того, перегрузим метод __toString()__:
```java
@Override
public String toString() {
    return name + " " + surname;
}
```

Также добавим геттеры и сеттеры для всех полей.

### 3.2 __Adress.java__
Теперь подробно рассмотрим класс __Address.java__, созданный для упорядочивания аттрибутов адреса.

Поля метода:
```java
private String country;
private String city;
private String street;
private String building;
```

Конструктор:
```java
public Address(@NotNull String country, @NotNull String city, 
@NotNull String street, @NotNull String building) {
    this.country = country;
    this.city = city;
    this.street = street;
    this.building = building;
}
```
Определим метод __equals()__:
```java
public boolean equals(Object otherAddress) {
    if(!(otherAddress instanceof Address)) {
        return false;
    }
    return this.building.equals(( (Address) otherAddress).building) &&
            this.street.equals(( (Address) otherAddress).street) &&
            this.city.equals(( (Address) otherAddress).city) &&
            this.country.equals(( (Address) otherAddress).country);
    }
```
### 3.3 __Main.java__
Полный код [`здесь`](https://github.com/Volkov-Vad1m/Netcracker-course/blob/db064ab7e94149d17e453aa4a33284d72bf2f3be/1.LPT/Address/src/main/java/wolf/Main.java#L5)
В методе __main()__ создадим массив объектов типа __Person__, добавим 5 человек.

Теперь рассмотрим методы решающие поставленные задачи:

 Метод __searchSurname()__ выполняет поиск человека по фамилии и печатает в консоль людей с этой фамилией:
```java
public static void searchSurname(Person[] people, String surname) {
    System.out.println("---------searching surname---------");
    for(Person person : people) {
        if(person.getSurname().equals(surname)) {
            System.out.println(person);
        }
    }
}
```

Метод __searchAddress()__ выполняет поиск людей по адресу и печатает имена в консоль:
```java
public static void searchAddress(Person[] people, Address address) {
    System.out.println("---------searching address---------");
    for(Person person : people) {
        if(person.getAddress().equals(address)) {
            System.out.println(person);
        }
    }
}
```
Метод __dumpBetweenDates()__ выполняет поиск людей, родившихся в определенный период времени, начало и конец которого передаются в аргументах.
> Если аргументы переданы в некорректном порядке (начало периода позже, чем его окончание), то функция никак не обработает это.

```java
public static void dumpBetweenDates(Person[] people, Calendar calendar1, Calendar calendar2) {
    System.out.println("---------between dates---------");
    for(Person person : people) {
        if( person.getBirthDay().compareTo(calendar1) > 0 && 
        person.getBirthDay().compareTo(calendar2) < 0) {
            System.out.println(person);
        }
    }
}
```

Методы __searchOldest()__  печатает самого старого человека.
```java
public static void searchOldest(Person[] people) {
    System.out.println("---------the oldest---------");
    Person oldest = people[0];
    for(Person person : people) {
        if(oldest.getBirthDay().compareTo(person.getBirthDay()) < 0) {
            oldest = person;
        }
    }
    System.out.println(oldest);
}
```
Метод __searchSameStreets()__  печатает людей, проживающих на одной улице.
```java
public static void searchSameStreets(Person[] people) {
    System.out.println("---------same streets---------");
    for(int i = 0; i < people.length; i++) {
        for (int j = i + 1; j < people.length; j++) {
            if(people[i].getAddress().getStreet().
                    equals(people[j].getAddress().getStreet())) {
                System.out.print(people[i].getAddress().getStreet() + " :");
                System.out.println(people[i] + ", " + people[j]);
            }
        }
    }
}
```
