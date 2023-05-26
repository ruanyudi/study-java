package cn.ruanyudi.homework.chapter;

import java.util.Scanner;

public class Menu {

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Select the chapter you want to view:\n");
            System.out.println("1. Section 1\n");
            System.out.println("2. Section 2\n");
            System.out.println("3. Section 3\n");
            System.out.println("4. Section 4\n");
            System.out.println("5. Section 5\n");
            System.out.print("Enter your choice (0-exit): ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;

                case 1:
                    cn.ruanyudi.homework.chapter.Chapter1.showMenu();
                    break;
                case 2:
                    cn.ruanyudi.homework.chapter.Chapter2.showMenu();
                    break;
                case 3:
                    cn.ruanyudi.homework.chapter.Chapter3.showMenu();
                    break;
                case 4:
                    cn.ruanyudi.homework.chapter.Chapter4.showMenu();
                    break;
                case 5:
                    cn.ruanyudi.homework.chapter.Chapter5.showMenu();
            }
        }
    }
}
