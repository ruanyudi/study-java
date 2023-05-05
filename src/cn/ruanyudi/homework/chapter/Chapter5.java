package cn.ruanyudi.homework.chapter;

import java.util.Scanner;
public class Chapter5 {
    public static void showMenu() {
        System.out.println("nWelcome to the assignment in Chapter 5, please select the job number to view:");
        while (true) {
            System.out.println("1 - JDBC-test");
            System.out.println("Enter your choice (0-exit)");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    break;
            }
//            System.out.println("输入0以继续");
//            sc.nextInt();
        }
    }
}

