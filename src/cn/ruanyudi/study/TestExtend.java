package cn.ruanyudi.study;

public class TestExtend {
    public static void main(String[] args) {
        Student st1 = new Student("阮宇迪", 20, "AI");
        System.out.println(st1 instanceof Person);
        System.out.println(st1 instanceof Student);
        Person ps1 = new Person();
        ps1.dao();
    }
}

class Person {
    String name;
    int height;
    private boolean dao = true;

    public void rest() {
        System.out.println("休息!");
    }

    public void dao() {
        System.out.println(dao);
    }
}

class Student extends Person {
    String major;

    public Student(String name, int height, String major) {
        this.name = name;
        this.major = major;
        this.height = height;
    }
}

class Boy extends Person {
    public void cry() {
        System.out.println("cry");
    }
}