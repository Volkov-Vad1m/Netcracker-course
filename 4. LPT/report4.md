# Отчёт к учебному практическому заданию №4 #
_Работу выполнил Волков Вадим, студент группы Б01-007_  
Отчёт лучше смотреть [`здесь`](https://github.com/Volkov-Vad1m/Netcracker-course/blob/master/4.%20LPT/report4.md).  
Программный код [`тут`](https://github.com/Volkov-Vad1m/Netcracker-course/tree/master/4.%20LPT).
## 1. Класс  Person.
Разработайте класс __Person__ для хранения даты рождения.  
Класс имеет следующее поле и конструкторы:

```java
   public class Person {

    private final Calendar birthDay;

    public Person(Calendar birthDay) {
        this.birthDay = birthDay;
    }
    
    //month - 1 т.к в Calendar отсчет с 0.
    public Person(int year, int month, int day) {
        this(new GregorianCalendar(year, month - 1, day));
    }
    
    // code...
```

Напишем метод __toString()__, возвращающий строковое представление даты рождения по вводимому в метод формату даты.
Формат даты, который понимает метод __toString(pattern)__ такой же, как и у [`SimpleDateFormat`](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html).
```java
    @Override
    public String toString() {
        return toString("dd-MM-yyyy");
    }

    public String toString(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        StringBuffer stringBuffer = dateFormat.format(birthDay.getTime(), new StringBuffer(), new FieldPosition(1));
        return new String(stringBuffer);
    }
```

## 2. Формирование Date и Calendar.
Необходимо написать код, который формирует объекты __Date__ и __Calendar__ по следующим данным, вводимым пользователем:
     _<Год><Месяц><Число>_
    _<Часы><минуты>_
Класс имеет только статические методы, позволяющие получить объект типа __Date__ или __Calendar__.
```java
public class DateBuilder {

    public static Date dateBuild(int year, int month, int day, int hours, int minutes) {
        return calendarBuild(year, month, day, hours, minutes).getTime();
    }

    public static Calendar calendarBuild(int year, int month, int day, int hours, int minutes) {
        return new GregorianCalendar(year, month, day, hours, minutes);
    }
}
```
Важно замечание: для корректной работы методов подразумевается, что пользователь будет вводить параметр _month_ используя константы из класса __Calendar__. Т.е __Calendar.MAY__, __Calendar.OCTOBER__ и т.п. так как в __Calendar__ отсчет месяцев идёт с нуля.

## 3. Анализ слов.
Необходимо провести частотный анализ символов из первой и из второй строки.

Для начала реализуем метод, определяющий символы, принадлежащие обеим строкам.  
```java
public static Set<Character> charsInBoth (String s1, String s2) {
    Set<Character> chars = new LinkedHashSet<>();

    for(char c : s1.toCharArray()) {
        if(s2.contains(String.valueOf(c))) {
            chars.add(c);
        }
    }
    return chars;
}
```

Реализуем метод, определяющий символы, входящие в первую строку, но не входящие во вторую:
```java
public static Set<Character> charsInFirst (String s1, String s2) {
    Set<Character> chars = new LinkedHashSet<>();

    for(char c : s1.toCharArray()) {
        if(s2.contains(String.valueOf(c)) == false) {
            chars.add(c);
        }
    }
    return chars;
}
```
Реализуем метод, определяющий символы, содержащиеся хотя бы в одной строке:
```java
public static Set<Character> charsInOne(String s1, String s2) {
    Set<Character> chars = new LinkedHashSet<>();

    for(char c : s1.toCharArray()) {
            chars.add(c);
    }
    for(char c : s2.toCharArray()) {
        chars.add(c);
    }
    return chars;
}
```
Необходимо реализовать три различных вывода:  
Для вывода в обычном порядке можно ничего не делать. Просто выводим наш __Set__.

Метод __printReverse(Set<T> set)__  выводит символы в обратном порядке:
```java
public static <T> void printReverse(Set<T> set) {
    List<T> list = new ArrayList<>(set);
    Collections.reverse(list);
    System.out.println(list);
}
```

Метод __printHashShift(Set<Character> set, int n)__ выводит символы в порядке возрастания циклического сдвига влево на n разрядов хеш-функции символа:
```java
public static void printHashShift(Set<Character> set, int n) {
    TreeSet<Character> sortedSet = new TreeSet<>(new Comparator<Character>() {
        @Override
        public int compare(Character c1, Character c2) {
            return Integer.compare(shift(c1.hashCode(), n), shift(c2.hashCode(), n));
        }
    });

    sortedSet.addAll(set);
    System.out.println(sortedSet);
}
```

Функция циклического сдвига:
```java
public static int shift(int value, int n) {
    return (value << (n % 32)) | (value >>> ((n % 32) - 32));
}
```

## 4. Бинарное дерево
Необходимо реализовать простое бинарное дерево.
### 4.1 Класс Node
Для начала создадим класс __Node<T extends Comparable<T>>__, который будет представлять узел дерева. 
```java
public class Node<T extends Comparable<T>> {

    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
```

### 4.2 Класс Tree
Этот класс будет представлять основную логику бинарного дерева. 
```java
public class Tree<T extends Comparable<T>> {

    private Node<T> root;

    public Tree() { }
    // code...
```

Реализуем метод __add()__:
```java
public void add(T value) {
    if (root == null) {
        root = new Node<>(value);
        return;
    }
    addRecursively(value, root);

}

private void addRecursively(T value, Node<T> curNode) {

    int compare = value.compareTo(curNode.getValue());

    if(compare > 0) { // value > curNode.value

        if(curNode.getRightChild() == null) {
            curNode.setRightChild(new Node<>(value));
        } else {
            addRecursively(value, curNode.getRightChild());
        }

    }
    else if(compare < 0) { // value < curNode.value

        if(curNode.getLeftChild() == null) {
            curNode.setLeftChild(new Node<>(value));
        } else {
            addRecursively(value, curNode.getLeftChild());
        }

    } else { } // already exists

}
```

Удаления элемента __delete()__:
```java
public void delete(T value) {
    deleteRecursively(value, root);
}

private Node<T> deleteRecursively(T value, Node<T> curNode) {
    if (curNode == null) {
        return null;
    }

    int compare = value.compareTo(curNode.getValue());

    if (compare < 0) {
        curNode.setLeftChild(deleteRecursively(value, curNode.getLeftChild()));
    }
    else if (compare > 0) {
        curNode.setRightChild(deleteRecursively(value, curNode.getRightChild()));
    }
    else { //equals

        if (isLast(curNode)) {
            return null;
        }
        else if (curNode.getLeftChild() == null) {
            return curNode.getRightChild();
        }
        else if (curNode.getRightChild() == null) {
            return curNode.getLeftChild();
        }
        else {
            // ищем самый маленький элемент в правом поддереве и ставим его вместо удалённого узла.
            Node<T> smallestNode = findSmallestNode(curNode.getRightChild());
            curNode.setValue(smallestNode.getValue());
            curNode.setRightChild(deleteRecursively(smallestNode.getValue(), curNode.getRightChild()));
            return curNode;
        }
    }
    return curNode;
}

private Node<T> findSmallestNode(Node<T> node) {
    return node.getLeftChild() == null ? node : findSmallestNode(node);
}
```

Теперь реализуем различные методы обхода дерева:  
Прямой метод обхода:
```java
public List<T> preorder() {
    if(root == null) {
        return new LinkedList<>();
    }

    return preorderRecursively(root, new LinkedList<T>());
}

private List<T> preorderRecursively(Node<T> curNode, List<T> list) {
    list.add(curNode.getValue());

    if(curNode.getLeftChild() != null) {
        preorderRecursively(curNode.getLeftChild(), list);
    }
    if(curNode.getRightChild() != null) {
        preorderRecursively(curNode.getRightChild(), list);
    }

    return list;
}
```
Обратный метод обхода:
```java
public List<T> postorder() {
    if(root == null) {
        return new LinkedList<T>();
    }

    return postorderRecursively(root, new LinkedList<T>());
}

private List<T> postorderRecursively(Node<T> curNode, List<T> list) {
    if(curNode.getLeftChild() != null) {
        postorderRecursively(curNode.getLeftChild(), list);
    }
    if(curNode.getRightChild() != null) {
        postorderRecursively(curNode.getRightChild(), list);
    }
    list.add(curNode.getValue());
    return list;
}
```

Центрированный обход дерева:
```java
public List<T> inorder() {
    if(root == null) {
        return new LinkedList<T>();
    }

    return inorderRecursively(root, new LinkedList<T>());
}

private List<T> inorderRecursively(Node<T> curNode, List<T> list) {
    if(curNode.getLeftChild() != null) {
        inorderRecursively(curNode.getLeftChild(), list);
    }

    list.add(curNode.getValue());

    if(curNode.getRightChild() != null) {
        inorderRecursively(curNode.getRightChild(), list);
    }
    return list;
}
```
Метод, для получения высоты дерева:
```java
public int size() {
    return sizeRecursively(root);
}

private int sizeRecursively(Node<T> node) {
    if (node == null || isLast(node)) {
        return 0;
    }
    return 1 + Math.max(sizeRecursively(node.getLeftChild()), sizeRecursively(node.getRightChild()));
}
```
Метод __isLast()__ проверяет, есть ли у узла дети или нет.
```java
private boolean isLast(Node<T> node) {
    return node.getLeftChild() == null && node.getRightChild() == null;
}
```


