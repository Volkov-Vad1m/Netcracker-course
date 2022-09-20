package ru.skillbench.tasks.basics.control;

public class Main {

    public static void main(String[] args) {
        ControlFlowStatements1Impl test = new ControlFlowStatements1Impl();

        int[][] arr = new int[][] {{1, 2 ,3}, {-17, 6, 17}};
        ControlFlowStatements1.BankDeposit bank = test.calculateBankDeposit(50);
        System.out.println(test.getMinValue(arr));
        System.out.println(bank);
    }
}
