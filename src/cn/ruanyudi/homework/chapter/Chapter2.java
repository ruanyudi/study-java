package cn.ruanyudi.homework.chapter;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Chapter2 {
    public static void showMenu() {
        System.out.println("\n欢迎查看第二章节的作业，请选择要查看的作业号:");
        while (true) {
            System.out.println("\n1——猜拳游戏");
            System.out.println("2——录入手机信息");
            System.out.println("3——包装类");
            System.out.println("4——判断三角形");
            System.out.println("5——九九乘法表");
            System.out.println("输入你的选择（0-退出）");
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
        System.out.println("\n现在开始猜拳游戏.....");
        System.out.println("输入你的选择 : (0:剪刀 1:石头 2:布)");
        int choice = sc.nextInt();
        Random rd = new Random(new Random().nextInt());
        int computer_choice = Math.abs(rd.nextInt() % 3);
        boolean flag = true;
        String result;
        if ((choice + 1) % 3 == computer_choice) {
            flag = false;
        }
        if (flag == false) {
            result = "你输了";
        } else {
            result = "你赢了";
        }
        if (computer_choice == choice) {
            result = "平局";
        }
        System.out.println(result);
        String str = "";
        switch (computer_choice) {
            case 0:
                str = "剪刀";
                break;
            case 1:
                str = "石头";
                break;
            case 2:
                str = "布";
                break;
        }
        System.out.println("电脑的选择是 : " + str);
    }
}

class PhoneDemo {
    static String name = "";
    String systemInfo = "";
    int numCpu = 0;
    static Scanner sc = new Scanner(System.in);
    double price = 0;

    public void run() {
        System.out.println("开始录入手机信息...");
        System.out.println("输入手机的品牌 : ");
        name = sc.next();
        System.out.println("输入手机的系统信息 : ");
        systemInfo = sc.next();
        System.out.println("输入手机处理器的数量 : ");
        numCpu = sc.nextInt();
        System.out.println("输入手机的价格 : ");
        price = sc.nextDouble();
        System.out.println("手机信息录入完成...");
        System.out.println("手机品牌 : " + name);
        System.out.println("操作系统 : " + systemInfo);
        System.out.println("CPU数 : " + numCpu);
        System.out.println("价格 : " + price + "元");
    }
}

class TriangleDemo {
    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("请输入三角形的三条边的大小以判断三角形的形状 : ");
        int[] data = new int[3];
        for (int i = 0; i < 3; i++) data[i] = sc.nextInt();
        Arrays.sort(data);
        if (data[2] <= data[1] + data[0]) {
            System.out.println("不构成三角形");
            return;
        }
        double tmp_a = Math.pow(data[2], 2);
        double tmp_b = Math.pow(data[0], 2) + Math.pow(data[1], 2);
        String result = "";
        if (tmp_a > tmp_b) {
            result = "钝角三角形";
        } else if (tmp_a == tmp_b) {
            result = "直角三角形";
        } else {
            result = "锐角三角形";
            if (data[0] == data[1] || data[1] == data[2] || data[0] == data[2]) {
                result = "等腰三角形";
            }
            if (data[0] == data[1] && data[1] == data[2]) {
                result = "等边三角形";
            }
        }
        System.out.println("该三角形是" + result);
    }
}

class ApiDemo {
    public void run() {
        System.out.println("Byte的数据总数 : " + (Byte.MAX_VALUE - Byte.MIN_VALUE + 1));
        System.out.println("Byte的取值范围是" + Byte.MIN_VALUE + "-" + Byte.MAX_VALUE);
        System.out.println("将字符串127转换为Byte类型的值为 : " + Byte.parseByte("127"));
        System.out.println("Byte的数据总数 : " + (Integer.MAX_VALUE - Integer.MIN_VALUE + 1.0));
        System.out.println("Byte的取值范围是" + Integer.MIN_VALUE + "-" + Integer.MAX_VALUE);
        System.out.println("将字符串5000转换为Byte类型的值为 : " + Integer.parseInt("5000"));
        System.out.println("16转为二进制 : " + Integer.toBinaryString(16));
        System.out.println("16转为十六进制 : " + Integer.toHexString(16));

    }
}

class MultiplicationDemo {
    Scanner sc = new Scanner(System.in);
    public void run() {
        System.out.println("选择需要打印的格式 (0 : 左上三角形，1 : 左下三角，2 : 右上三角，3 : 右下三角): ");
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