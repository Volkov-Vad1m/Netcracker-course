package ru.skillbench.tasks.basics.entity;

public class Main {
    public static void main(String[] args) {
        Employee mng1 = new EmployeeImpl();
        Employee mng2 = new EmployeeImpl("2", "2", 1, null);
        Employee mng3 = new EmployeeImpl();
        Employee mng4 = new EmployeeImpl();
        mng1.setManager(mng2);
        System.out.println(mng1.getTopManager().getFullName());



    }


}
