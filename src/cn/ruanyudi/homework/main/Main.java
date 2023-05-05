package cn.ruanyudi.homework.main;

import org.junit.jupiter.api.Test;

public class Main {
    @Test
    public static void main(String[] args) {
        try {
            cn.ruanyudi.homework.chapter.Menu.showMenu();
        } catch (Exception e) {
            System.out.println("Error input");
            main(args);
        }
//        cn.ruanyudi.homework.chapter.Menu.showMenu();
    }
}
