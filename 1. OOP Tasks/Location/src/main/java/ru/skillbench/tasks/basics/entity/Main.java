package ru.skillbench.tasks.basics.entity;

public class Main {

    public static void main(String[] args) {

        LocationImpl ap = new LocationImpl(Location.Type.APARTMENT, "158");
        System.out.println(ap.getType().getNameForAddress());
        LocationImpl bu = new LocationImpl(Location.Type.BUILDING, "10 к. 1");
        Location st = new LocationImpl(Location.Type.STREET, "Институтский пер.");
        LocationImpl ct = new LocationImpl(Location.Type.CITY, "Livny");
        Location bebra = new LocationImpl1();
        System.out.println(bu);
        ap.setParent(bu);
        bu.setParent(st);
        st.setParent(ct);
        ct.setParent(bebra);
        System.out.println(ap.getAddress());

    }
}
