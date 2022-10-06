package wolf.shirt;

public class Shirt {

    private String id;
    private String description;
    private String color;
    private String size;

    public Shirt(String id, String description, String color, String size) {
        this.id = id;
        this.description = description;
        this.color = color;
        this.size = size;
    }

    public static Shirt[] ConverterToShirt(String[] stringShirts) {

        Shirt[] convertedShirts = new Shirt[stringShirts.length];

        for(int i = 0; i < stringShirts.length; i++) {

            String[] splitString = stringShirts[i].split(",");

            convertedShirts[i] = new Shirt(splitString[0], splitString[1],
                    splitString[2], splitString[3]);
        }

        return convertedShirts;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'';
    }


}
