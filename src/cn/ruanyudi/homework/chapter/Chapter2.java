package cn.ruanyudi.homework.chapter;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Chapter2 {
    public static void showMenu() {
        System.out.println("\nWelcome to view the assignments in Section 2, please select the assignment number to view:");
        while (true) {
            System.out.println("\n1.Guessing game");
            System.out.println("2.Enter mobile phone information");
            System.out.println("3.Packaging class");
            System.out.println("4.Judging triangles");
            System.out.println("5.Nine-nine multiplication table");
            System.out.println("Enter your choice (0-exit)");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    new GuessDemo().run();
                    break;
                case 2:
                    new PhoneDemo().run();
                    break;
                case 3:
                    new ApiDemo().run();
                    break;
                case 4:
                    new TriangleDemo().run();
                    break;
                case 5:
                    new MultiplicationDemo().run();
                    break;
                case 0:
                    return;
            }
        }
    }
}

class GuessDemo {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nStart guessing game now.....");
        System.out.println("Enter your choice : (0: Scissors 1: Stone 2: Cloth)");
        int choice = sc.nextInt();
        Random rd = new Random(new Random().nextInt());
        int computer_choice = Math.abs(rd.nextInt() % 3);
        boolean flag = true;
        String result;
        if ((choice + 1) % 3 == computer_choice) {
            flag = false;
        }
        if (flag == false) {
            result = "You lose";
        } else {
            result = "You win";
        }
        if (computer_choice == choice) {
            result = "draw";
        }
        System.out.println(result);
        String str = "";
        switch (computer_choice) {
            case 0:
                str = "scissors";
                break;
            case 1:
                str = "stone";
                break;
            case 2:
                str = "cloth";
                break;
        }
        System.out.println("The choice of computer is: " + str);
    }
}

class PhoneDemo {
    static String name = "";
    String systemInfo = "";
    int numCpu = 0;
    static Scanner sc = new Scanner(System.in);
    double price = 0;

    public void run() {
        System.out.println("Start entering your phone information...");
        System.out.println("Enter the brand of your phone : ");
        name = sc.next();
        System.out.println("Enter your phone's system information : ");
        systemInfo = sc.next();
        System.out.println("Enter the number of processors for your phone : ");
        numCpu = sc.nextInt();
        System.out.println("Enter the price of your phone : ");
        price = sc.nextDouble();
        System.out.println("Mobile phone information entry completed...");
        System.out.println("Mobile Phone Brand : " + name);
        System.out.println("Operating system: " + systemInfo);
        System.out.println("Number of CPUs : " + numCpu);
        System.out.println("Price: " + price + "Yuan");
    }
}

class TriangleDemo {
    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("Please enter the size of the three sides of the triangle to judge the shape of the triangle: ");
        int[] data = new int[3];
        for (int i = 0; i < 3; i++) data[i] = sc.nextInt();
        Arrays.sort(data);
        if (data[2] <= data[1] + data[0]) {
            System.out.println("Does not form a triangle");
            return;
        }
        double tmp_a = Math.pow(data[2], 2);
        double tmp_b = Math.pow(data[0], 2) + Math.pow(data[1], 2);
        String result = "";
        if (tmp_a > tmp_b) {
            result = "Blunt triangle";
        } else if (tmp_a == tmp_b) {
            result = "right-angled triangle";
        } else {
            result = "acute triangle";
            if (data[0] == data[1] || data[1] == data[2] || data[0] == data[2]) {
                result = "isosceles triangle";
            }
            if (data[0] == data[1] && data[1] == data[2]) {
                result = "equilateral triangle";
            }
        }
        System.out.println("The triangle is" + result);
    }
}

class ApiDemo {
    public void run() {
        System.out.println("Total data for Byte : " + (Byte.MAX_VALUE - Byte.MIN_VALUE + 1));
        System.out.println("The value range of Byte is" + Byte.MIN_VALUE + "-" + Byte.MAX_VALUE);
        System.out.println("Convert the string 127 to a value of type Byte to : " + Byte.parseByte("127"));
        System.out.println("Total data for Integer : " + (Integer.MAX_VALUE - Integer.MIN_VALUE + 1.0));
        System.out.println("The value range of Integer is : " + Integer.MIN_VALUE + "-" + Integer.MAX_VALUE);
        System.out.println("Convert the string 5000 to a value of type Integer: " + Integer.parseInt("5000"));
        System.out.println("16 to binary : " + Integer.toBinaryString(16));
        System.out.println("16 to hexadecimal : " + Integer.toHexString(16));

    }
}

class MultiplicationDemo {
    Scanner sc = new Scanner(System.in);
    public void run() {
        System.out.println("Select the format you want to print (0 : upper left triangle, 1 : bottom left triangle, 2 : top right triangle, 3 : bottom right triangle): ");
        int choice = sc.nextInt();
        switch (choice) {
            case 0:
                caseOne();
                break;
            case 1:
                caseTwo();
                break;
            case 2:
                caseThree();
                break;
            case 3:
                caseFour();
                break;
        }
        return;
    }

    /**
     * 左上
     */
    void caseOne() {
        for (int i = 9; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                System.out.print(Integer.toString(i) + "*" + Integer.toString(j) + "=" + Integer.toString(i * j) + "\t");
            }
            System.out.println();
        }
        return;
    }

    /**
     * 左下
     */
    void caseTwo() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(Integer.toString(i) + "*" + Integer.toString(j) + "=" + Integer.toString(i * j) + "\t");
            }
            System.out.println();
        }
        return;
    }

    /**
     * 右上
     */
    void caseThree() {
        for (int i = 9; i > 0; i--) {
            for (int j = 9; j > i; j--) System.out.print("\t\t");
            for (int j = i; j > 0; j--) {
                System.out.print(Integer.toString(i) + "*" + Integer.toString(j) + "=" + Integer.toString(i * j) + "\t");
            }
            System.out.println();
        }
        return;
    }

    /**
     * 右下
     */
    void caseFour() {
        for (int i = 1; i < 10; i++) {
            for (int j = 8; j >= i; j--) {
                System.out.print("\t\t");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(Integer.toString(i) + "*" + Integer.toString(j) + "=" + Integer.toString(i * j) + "\t");
            }
            System.out.println();
        }
        return;
    }
}