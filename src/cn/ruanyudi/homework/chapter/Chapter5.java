package cn.ruanyudi.homework.chapter;

import java.util.Scanner;

public class Chapter5 {
    public static void showMenu() {
        System.out.println("\n欢迎查看第五章节的作业，请选择要查看的作业号:");
        while (true) {
            System.out.println("1——日历");
            System.out.println("输入你的选择（0-退出）");
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