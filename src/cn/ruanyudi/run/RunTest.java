package cn.ruanyudi.run;

public class RunTest{
    public static void main (String [] args){
        System.out.println(new Person().name);
    }
}
class Person{
    String name= "default";
}