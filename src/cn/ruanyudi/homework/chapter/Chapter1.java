package cn.ruanyudi.homework.chapter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Chapter1 {
    public static void showMenu() {
        System.out.println("\nWelcome to view the assignments in the first section, please select the activity number to view:");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Calendar");
            System.out.println("2.Student Achievement Management System");
            System.out.println("Enter your choice (0-exit)");
            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    CalendarDemo calendardemo = new CalendarDemo();
                    calendardemo.run();
                }
                case 2 -> {
                    ManagementDemo managementdemo = new ManagementDemo();
                    managementdemo.run();
                }
            }
        }
    }


}

class CalendarDemo {
    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("Enter the year:");
        int year = sc.nextInt();
        System.out.println("Enter the month:");
        int month = sc.nextInt();
        int day = 1;
        System.out.println("Mon\tTue\tWed\tThu\tFri\tSat\tSun\n");
        int week = getWeek(year, month, day);
        for (int i = 0; i < week - 1; i++) {
            System.out.print("\t");
        }
        int days = getDayOfMonth(year, month);
        for (int i = 1; i <= days; i++) {
            if (getWeek(year, month, i) == 7) {
                System.out.print(i + "\n");
            } else {
                System.out.print(i + "\t");
            }
        }
        System.out.println();
    }


    int getWeek(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            return 7;
        } else {
            return week;
        }
    }

    boolean check(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    int getDayOfMonth(int year, int month) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            if (check(year)) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 31;
        }
    }
}

class ManagementDemo {
    Students[] students;
    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("---------------------");
        System.out.println("\tStudent Achievement Management System");
        System.out.println("---------------------");
        while (true) {
            System.out.println("\t1.Enter student information");
            System.out.println("\t2.View student rankings");
            System.out.println("\t0.Exit the system");
            System.out.println("Enter the action to take");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    showRank();
                    break;
                case 0:
                    return;
            }
        }
    }

    void addStudent() {
        System.out.println("Please enter the number of students:");
        int n = sc.nextInt();
        students = new Students[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Students();
        }
        return;
    }

    void showRank() {
        int k = 0;
        System.out.println("Please need to check the positive or reverse order (0:positive 1:inverted):");
        k = sc.nextInt();
        if (k == 1) {
            k = -1;
        }
        try {
            int tmp = students.length;
        } catch (Exception e) {
            System.out.println("There is no student information in the current system");
            return;
        }

        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 1; j < students.length - i; j++) {
                if (students[j - 1].sumScore() * k < students[j].sumScore()) {
                    Students tmp = students[j - 1];
                    students[j - 1] = students[j];
                    students[j] = tmp;
                }
            }
        }
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }

}

class Students {
    String name;
    int scoreJava;
    int scoreSQLserver;
    int scoreAndroid;
    Scanner sc = new Scanner(System.in);

    public Students() {
        System.out.println("Please enter the student's name, Java grade, SQLserver score, and Android score (split by ',')");
        String sArgs = sc.next();
        String Args[] = sArgs.split(",");
        name = Args[0];
        scoreJava = Integer.parseInt(Args[1]);
        scoreSQLserver = Integer.parseInt(Args[2]);
        scoreAndroid = Integer.parseInt(Args[3]);
    }

    public int sumScore() {
        int res = scoreJava + scoreAndroid + scoreSQLserver;
        return res;
    }

    public String toString() {
        String res = name + '\t' + scoreJava + '\t' + scoreSQLserver + '\t' + scoreAndroid;
        return res;
    }

}