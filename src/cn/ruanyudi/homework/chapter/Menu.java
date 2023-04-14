package cn.ruanyudi.homework.chapter;

import org.junit.Test;

import java.util.Scanner;

public class Menu {
    @Test
    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("选择想要查看的章节：\n");
            System.out.println("1. 第1章节\n");
            System.out.println("2. 第2章节\n");
            System.out.println("3. 第3章节\n");
            System.out.println("4. 第4章节\n");
            System.out.print("输入你的选择（0-退出）： ");
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
                    System.out.println("暂无作业");
                    break;
            }
        }
    }
}
