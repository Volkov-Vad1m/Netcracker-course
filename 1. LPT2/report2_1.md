# Отчёт к учебному практическому заданию №2_1 #
_Работу выполнил Волков Вадим, студент группы Б01-007_  
Отчёт лучше смотреть [`здесь`](https://github.com/Volkov-Vad1m/Netcracker-course/blob/master/1.%20LPT2/report2_1.md).  
Программный код [`тут`](https://github.com/Volkov-Vad1m/Netcracker-course/tree/master/1.%20LPT2).  

## 1. Класс Vectors
Необходимо создать класс __Vectors__, содержащий статические методы работы с векторами.  
Реализуем метод умножения вектора на скаляр __multiply()__:  
```java
public static void multiply(List<Double> v, double d) {
    for(int i = 0; i < v.size(); i++) {
        v.set(i, v.get(i) * d);
    }
}
```

Метод сложения двух векторов. Если размерности векторов разные, меньший вектор дополняется нулями до размера большего. Результат записывается в __Vector<Double> v1__.
```java
public static void sum(Vector<Double> v1, Vector<Double> v2) {
    int length = Integer.min(v1.size(), v2.size());

    for(int i = 0; i < length; i++) {
        v1.set(i, v1.get(i) + v2.get(i));
    }
}
```

Также реализуем метод, вычисляющий скалярное произведение векторов. Аналогично, вектор дополняется нулями.
```java
public static double scalarMultiply(Vector<Double> v1, Vector<Double> v2) {
    int length = Integer.min(v1.size(), v2.size());
    int result = 0;

    for(int i = 0; i < length; i++) {
        result += v1.get(i) * v2.get(i);
    }

    return result;
}
```
## 2. Дополнение класса __Vectors__
### 2.1 Реализация требуемых методов
В класс __Vectors__ необходимо добавить методы для работы с байтовыми и символьными потоками.  
Метод __outputVector()__ записывает вектор в байтовый поток:  
```java
public static void outputVector(Vector<Double> v, OutputStream out) throws IOException {
    ObjectOutputStream objectOut = new ObjectOutputStream(out);

    objectOut.writeInt(v.size());

    for(Double value : v) {
        objectOut.writeDouble(value);
    }

    objectOut.close();
}
```

Метод __inputVector()__ выполняет чтение вектора из байтового потока:
```java
public static Vector<Double> inputVector(InputStream in) throws IOException {
    ObjectInputStream objectIn = new ObjectInputStream(in);

    if(objectIn.available() == 0) {
        objectIn.close();
        throw new RuntimeException("bad input");
    } else {

        Vector<Double> v = new Vector<>(objectIn.readInt());

        while (objectIn.available() > 0) {
            v.add(objectIn.readDouble());
        }

    	objectIn.close();
        return v;
    }
}
```

Метод __writeVector()__ записывает вектор в символьный поток:
```java
public static void writeVector(Vector<Double> v, Writer out) throws IOException {
    out.write(String.valueOf(v.size()));

    for (Double value : v) {
        out.write(" " + value);
    }

}
```

Метод __readVector()__ выполняет чтение вектора из символьного потока:
```java
public static Vector<Double> readVector(Reader in) throws IOException {

    
        Vector<Double> v = new Vector<>();

        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.parseNumbers();

        tokenizer.nextToken();

        while(tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            v.add(tokenizer.nval);

        }

        return v;


}
```
### 2.1 Тестирование класса
Тест символьных потоков. Считываем данные из файла __vector1_read__. Вид файла:
```
10 1.01 2.02 3.03 4.04 5 6 7 8 9 10
```
После считывания, записываем в байтовый поток, затем считываем этот байтовый поток и сверяем данные:
```java
try {
    FileReader reader = new FileReader("./src/main/resources/vector1_read");
    Vector<Double> v = Vectors.readVector(reader);
    reader.close();

    FileOutputStream out = new FileOutputStream("./src/main/resources/vector1_out");
    Vectors.outputVector(v, out);
    out.close();

    FileInputStream in = new FileInputStream("./src/main/resources/vector1_out");
    System.out.println(Vectors.inputVector(in));
    in.close();

} catch (IOException e) {
    e.printStackTrace();
}
```
Тест символьных потоков:
```java
try {
    FileReader reader = new FileReader("./src/main/resources/vector1_read");
    Vector<Double> v = Vectors.readVector(reader);
    reader.close();

    FileWriter writer = new FileWriter("./src/main/resources/vector1_write");
    Vectors.writeVector(v, writer);
    writer.close();

    FileReader reader2 = new FileReader("./src/main/resources/vector1_write");
    System.out.println(Vectors.readVector(reader2));
    reader2.close();

} catch (IOException e) {
    e.printStackTrace();
}
```


## 3. Сериализация вектора
### 3.1 LinkedListVector
Класс обладает единственным полем:
```java
private LinkedList<T> list;
```

Конструкторы класса:
```java
public LinkedListVector() {
    this.list = new LinkedList<>();
}

public LinkedListVector(Collection<? extends T> c) {
    this.list = new LinkedList<>(c);
}
```

Метод __writeList()__ сериализует список и записывает его в поток __ObjectOutput__:
```java
public void writeList(ObjectOutput out) throws IOException {
    out.writeObject(list);
}
```

Метод __readList()__ читает данные из потока __ObjectInput__ и десериализует их в список:
```java
public void readList(ObjectInput in) throws IOException, ClassNotFoundException {
    this.list = (LinkedList<T>) in.readObject();
}
```
Тестирование:
```java
LinkedListVector<Double> list = new LinkedListVector<>();
    for(int i = 0; i < 10; i++) {
        list.add(i + 0d);
    }

    // try to write
    try {
        OutputStream file = new FileOutputStream("./src/main/resources/data_of_list.txt");
        ObjectOutput objOut = new ObjectOutputStream(file);

        list.writeList(objOut);

        file.close();
        objOut.close();
    } catch (IOException e) {
        e.printStackTrace();

    }

    //try to read
    LinkedListVector<Double> newList = new LinkedListVector<>();
    try {
        InputStream file = new FileInputStream("./src/main/resources/data_of_list.txt");
        ObjectInput objIn = new ObjectInputStream(file);

        newList.readList(objIn);

        file.close();
        objIn.close();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();

    }
    // compare
    System.out.println(newList.equals(list));
    // console: true
}
```
### 3.2 ArrayVector
Поле класса:
```java
    private T [] array;
```
Конструкторы класса:
```java
    public ArrayVector() {
        this.array = null;
    }
    
    public ArrayVector(T...elements) {
        array = elements;
    }
```
Метод __writeArray()__ сериализует массив и записывает его в поток __ObjectOutput__:
```java
public void writeArray(ObjectOutput out) throws IOException {
    out.writeObject(array);
}
```

Метод __readArray()__ читает данные из потока __ObjectInput__ и десериализует их в массив:
```java
public void readArray(ObjectInput in) throws IOException, ClassNotFoundException {
    this.array =  (T[])in.readObject();
}
```
Тестирование:
```java
ArrayVector<String> array = new ArrayVector<>("Hi", "my", "name" ,"is", "Vadim") ;

    // try to write
    try {
        OutputStream file = new FileOutputStream("./src/main/resources/data_of_arr.txt");
        ObjectOutput objOut = new ObjectOutputStream(file);

        array.writeArray(objOut);

        file.close();
        objOut.close();
    } catch (IOException e) {
        e.printStackTrace();

    }

    //try to read
    ArrayVector<String> newArray = new ArrayVector<>();
    try {
        InputStream file = new FileInputStream("./src/main/resources/data_of_arr.txt");
        ObjectInput objIn = new ObjectInputStream(file);


        newArray.readArray(objIn);

        file.close();
        objIn.close();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();


    }
    // compare
    System.out.println(array);
    System.out.println(newArray);
}
```

## 4. MyClassToBePersisted
Необходимо реализовать класс __MyClassToBePersisted.java__, обладающий полями:
`<Поле профиля>`
`<Поле группы>`

Поля класса __MyClassToBePersisted__:
```java
private String profile;
private String group;
```

Конструкторы класса:
```java
public MyClassToBePersisted() {
    this("", "");
}
public MyClassToBePersisted(String profile, String group) {
    this.profile = profile;
    this.group = group;
}
```
Метод __toString()__:
```java
@Override
public String toString() {
    return "MyClassToBePersisted{" +
            "profile='" + profile + '\'' +
            ", group='" + group + '\'' +
            '}';
}
```

### 4.1 SerializeMyClassToBePersisted
В основном методе этого класса необходимо создать экземпляр класса __MyClassToBePersisted__ и сеарилизовать его в файл:
```java
public static void main(String[] args) {

    MyClassToBePersisted obj = new MyClassToBePersisted("profile", "group");

    try {
        OutputStream out = new FileOutputStream("./src/main/resources/objOut");
        ObjectOutputStream objOut = new ObjectOutputStream(out);

        objOut.writeObject(obj);

        objOut.close();
        out.close();


    } catch (IOException e) {
        e.printStackTrace();
    }

}
```

### 4.2 DeserializeMyClassToBePersisted.java
В основном методе этого класса необходимо считать сериализованный класс и десериализовать его в экземпляр класса __MyClassToBePersisted__:
```java
    public static void main(String[] args) {
        MyClassToBePersisted obj = null;

        try {
            InputStream in = new FileInputStream("./src/main/resources/objOut");

            ObjectInputStream objIn = new ObjectInputStream(in);

            obj = (MyClassToBePersisted) objIn.readObject();
            
            objIn.close();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(obj);
    }
```


