# Отчёт к учебному практическому заданию №2 #
_Работу выполнил Волков Вадим, студент группы Б01-007_
Отчет лучше посмотреть на [`GitHub`](https://github.com/ksartamonov/Netcracker-Special-Course/tree/master/PracticalTask2). Там красивей...    
Код [`тут`](https://github.com/ksartamonov/Netcracker-Special-Course/tree/master/PracticalTask2)
## 1. Говорящие животные ##
Необходимо реализовать следующую диаграмму классов:
![Speaking animals](https://github.com/ksartamonov/Netcracker-Special-Course/blob/master/PracticalTask2/pictures/speaking-animals-diagram.png)

Начнем с описания интерфейса __Voice__:
### 1.1 interface Voice ###
Интерфейс содержит единственный метод __voice()__, который свой для каждого животного.
```java
public interface Voice {
    public void voice();
}
```
Далее опишем классы, реализующие этот интерфейс:
### 1.2 Cat.java, Dog.java, Cow.java
Все методы отличаются только реализацией метода __voice()__
```java
public class Dog implements Voice {
    @Override
    public void voice() {
        System.out.println("woof");
    }
}
```
```java
public class Cat implements Voice {
    @Override
    public void voice() {
        System.out.println("meow");
    }
}
```
```java
public class Cow implements Voice {
    @Override
    public void voice() {
        System.out.println("moo");
    }
}
```

## 2. Игра в кости
Была решена задача реализации игры в кости с использованием интерфейсов и немного изменена логика.
### 2.1 Interface Player
Был создан интерфейс __Player__. Я постарался сделать его универсальным, чтобы можно было использовать его для других игр.  
К примеру: "21" или бильярд :)  
```java
public interface Player {

    int getId();

    /**
     * The result of points in the round. 
     * For example: Player 1 has 21 on the dice.
     */
    int getTurnResult();

    void setTurnResult(int turnResult);

    /** 
     * final score of the game 
     * For example: Player 1 has 3 wins in the 7 rounds
     */
    int getScore();

    void setScore(int score);
}

```

Этот интерфейс реализуют 2 класса: __DicePlayer__ и __ComputerPlayer__(которой наследуется от __DicePlayer__).
__DicePlayer__:
```java
    private int id;

    /**
     * result of tossing cubes in a particular round
     */
    private int turnResult;

    private int score;
```
Поле __id__ хранит Id игрока, __turnResult__ --- количество очков, выпавшее при данном броске, __score__--- количество выигранных им партий.  
Конструктор и методы: 
```java
    public DicePlayer(int id) {
        this.id = id;
    }
```
Реализуем методы интерфеса (обычные getters and setters):
```java
public int getId() {
    return id;
}

public int getTurnResult() {
    return turnResult;
}

public void setTurnResult(int turnResult) {
    this.turnResult = turnResult;
}

public int getScore() {
    return score;
}

public void setScore(int score) {
    this.score = score;
}
```
Был переопределен метод __toString()__ для более удобного вывода результатов.  
```java
 @Override
public String toString() {
    return "Player " + id;
}
```
__ComputerPlayer__ насследуется от __DicePlayer__ только чтобы переопределить метод __toString()__
Для класса __ComputerPlayer__:
```java
    @Override
    public String toString() {
        return "Computer";
    }
```

### 2.2 Dice.java
Помимо использования интерфейса для игрока был создан отдельный класс __Dice.java__, реализующий игральные кости, он содержит в себе только метод подкидывания кубиков:
```java
    public static int roll(int nDice) {
        Random rand = new Random();
        int val = 0;
        for (int i = 0; i < nDice; i++) { // value is in bound [nDice ; 6*nDice]
            val += rand.nextInt(6) + 1;
        }
        return val;
    }
```

### 2.3 DiceGame.java
Класс __DiceGame__ реализует интерфейс __Game__.
```java
public interface Game {

    void play();

    void printScores();

    Player getWinner();
}
```
Класс __DiceGame__ обладает следующими полями:
```java
    private final int SCORE_TO_WIN = 7;
    private final Player[] players;
    private final int cubes; // amount of cubes
    private Player winner;
```
Рассмотрим методы данного класса:

Конструктор после добавления всех игроков автоматически добавляет в список игроков игрока __Computer__:
```java
public DiceGame(int players, int cubes) {
    this.players = new Player[players + 1]; //+PC

    for (int i = 0; i < this.players.length - 1; i++) {
        this.players[i] = new DicePlayer(i + 1);
    }

    this.players[players] = new ComputerPlayer(players + 1); // PC is not real
    this.cubes = cubes;
}
```

Метод __play()__ реализует основную логику игры в кости:
```java
public void play() {
    Dice dices = this.new Dice();
    
    while (winner == null) { // main body
        System.out.println("-----------------toss!-----------------");

        for (Player player : players) {
            player.setTurnResult(dices.roll());
            System.out.println(player + ": the number " + player.getTurnResult()   
            + " has been dropped out on the dice");
        }

        endRound();
        winner = findWinner();
    }

    System.out.println(winner + " won the game");  // someone won
}
```
Метод __endRound()__ корректно завершает раунд. Печатает в консоль победителя раунда, устанавливает очки побед в раундах и печатает их и сдвигает массив игроков, чтобы в __players[0]__ позиции был победитель раунда. Методы __indexMax()__ и __moveArray()__ вспомогательные, с ними можно ознакомиться [`здесь`]()
```java
private void endRound() {
    int firstWinner = indexMax();
    System.out.println(players[firstWinner] + " won this round!");
    
    players[firstWinner].setScore(players[firstWinner].getScore() + 1);
    
    printScores();
    moveArray(players.length - firstWinner);
}
```
Метод __findWinner()__ определяет победителя всей игры и возвращает его. В случае, если победителя еще нет --- возвращает `null`:
```java
private Player findWinner() {
    for (Player player : players) {
        if (player.getScore() == SCORE_TO_WIN) {
            return player;
        }
    }
    return null;
}
```

Метод __printScores()__ печатает в консоль количество побед в раундах каждым игроком:
```java
@Override
public void printScores() {
    System.out.println("-----------------scores-----------------");
    for (Player player : players) {
        System.out.println(player + ": " + player.getScore());
    }
    System.out.println("----------------------------------------");
}
```

### 2.4 Main.java
Класс для тестирования игры в кости:
```java
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("type number of players");
        int players = in.nextInt();

        System.out.println("How many cubes?");
        int cubes = in.nextInt();
        DiceGame dealer = new DiceGame(players, cubes);
        dealer.play();
    }
}
```
## 3. Extended Class
Необходимо реализовать класс, представленный на изображении:
![Extended class](https://github.com/ksartamonov/Netcracker-Special-Course/blob/master/PracticalTask2/pictures/extended-class-diagram.png)

Реализация класса:
```java
public class ExtendedClass {

    private byte b;
    private int i;
    private double d;
    private String s;

    // ************************************************************************
    // getters and setters
    // ************************************************************************
    
    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    
    // ************************************************************************
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExtendedClass that = (ExtendedClass) o;
        return b == that.b && i == that.i && Double.compare(that.d, d) == 0 && Objects.equals(s, that.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(b, i, d, s);
    }

    @Override
    public String toString() {
        return "ExtendedClass{" +
                "b=" + b +
                ", i=" + i +
                ", d=" + d +
                ", s='" + s + '\'' +
                '}';
    }
}
```

## 4. Персональное задание
Необходимо было создать интерфейс __Sleepy__ с методами __sleep()__, __wakeUp()__ и __ask()__:
```java
public interface Sleepy {
    void sleep();
    void wakeUp();
    void ask();
}

```
Интерфейс был реализован в классе __SleepyImpl__. Метод __sleep()__ устанавливает флаг __awake__ равным `false`. Метод __wakeUp()__ устанавливает флаг __awake__ равным `true`. Метод __ask()__ печатает в консоль “BOO!”, если флаг установлен в `true`, и “zzz…” в противном случае.


__SleepyImpl.java__:
```java
public class SleepyImpl implements Sleepy{
    private boolean awake = false;

    @Override
    public void sleep() {
        awake = false;
    }

    @Override
    public void wakeUp() {
        awake = true;
    }

    @Override
    public void ask() {
        if(awake) {
            System.out.println("BOO");
        } else {
            System.out.println("zzz...");
        }
    }
}
```
