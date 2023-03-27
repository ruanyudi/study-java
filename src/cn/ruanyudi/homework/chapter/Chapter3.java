package cn.ruanyudi.homework.chapter;

import java.util.Arrays;
import java.util.Scanner;

public class Chapter3 {
    public static void showMenu() {
        System.out.println("\n欢迎查看第三章节的作业，请选择要查看的作业号:");
        while (true) {
            System.out.println("1——客户选购信息管理系统");
            System.out.println("输入你的选择（0-退出）");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    new CustomerGoodsAdmin().show();
                    break;
            }
//            System.out.println("输入0以继续");
//            sc.nextInt();
        }
    }
}


class CustomerGoodsAdmin{
    Scanner sc = new Scanner(System.in);
    Customer[] customers;
    public void show(){
        while(true){
            int choice = 0 ;
            System.out.println("\n------------------------------------");
            System.out.println("Welcome\tTo\tCustomerGoodsManagement");
            System.out.println("------------------------------------");
            System.out.println();
            System.out.println("1.\t录入Customer信息");
            System.out.println("2.\t查询用户信息");
            System.out.println("3.\t查看用户Rank");
            System.out.println("0.\tExit System");
            System.out.println();
            choice = sc.nextInt();
            switch (choice){
                case 0:
                    return ;
                case 1:
                    inputCustomerInfo();
                    break;
                case 2:
                    print();
                    break;
                case 3:
                    rank();
                    break;
            }
        }

    }
    protected void inputCustomerInfo(){
        System.out.println("输入要录入的Customer个数:");
        int number = sc.nextInt();
        int preLength = customers!=null ? customers.length : 0;
        if(preLength==0){
            customers = new Customer[number];
        }
        else{
            customers = Arrays.copyOf(customers,preLength+number);
        }
        for(int i=0;i<number;i++){
            System.out.println("-----------------------");
            customers[preLength+i] = new Customer();
        }
    }

    private void print(){
        if(customers==null){
            System.out.println("当前没有用户信息");
            return ;
        }
        System.out.println("select the CustomerName (0 for AllCustomer):");
        String choice = sc.next();
        if(choice.equals("0")){
            System.out.println("系统当前有"+customers.length+"条用户信息");
            for(int i=0;i<customers.length;i++){
                System.out.println(customers[i]);
            }
        }
        else{
            for(Customer customer : customers){
                if(customer.getName().equals(choice)){
                    System.out.println(customer);
                    return ;
                }
            }
            System.out.println("找不到该用户");
        }
    }

    private void rank(){
        System.out.println("系统当前有"+customers.length+"条用户信息");
        for(int i=0;i<customers.length;i++){
            for(int j= customers.length-1;j>i;j--){
                if(customers[j].getTotal()>customers[j-1].getTotal()){
                    Customer tmp = customers[j];
                    customers[j] = customers[j-1];
                    customers[j-1] = tmp;
                }
            }
        }
        for(Customer customer : customers){
            System.out.println("Name : "+ customer.getName()+"\t总计"+customer.getTotal());
        }
    }
}

class Customer{
    private String name;
    private int age;
    private Goods[] goods;
    private double total;

    public double getTotal() {
        return total;
    }

    Scanner sc = new Scanner(System.in);
    Customer(){
        System.out.println("输入客户的姓名:");
        name=sc.next();
        System.out.println("输入用户的年龄:");
        age=sc.nextInt();
        System.out.println("输入购买的商品数量:");
        int number = sc.nextInt();
        goods = new Goods[number];
        for(int i=0;i< goods.length;i++){
            System.out.println("-----------------------");
            goods[i]=new Goods();
            total += goods[i].getTotalPrice();
        }
    }
    public void print(){

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString(){
        System.out.println("-----------------------");
        System.out.println("Name : "+ getName()+"\t\tAge : "+getAge());
        System.out.println("-----------------------");
        for(Goods good : goods){
            System.out.println(good);
        }
        System.out.println("总计 : "+total);
        return "";
    }
}

class Goods{
    private String name;
    private int num;

    public double getTotalPrice(){
        return totalPrice;
    }

    private double price;
    private double totalPrice;
    Scanner sc = new Scanner(System.in);
    Goods(){
        System.out.println("输入商品的名称:");
        name = sc.next();
        System.out.println("输入商品的数量:");
        num = sc.nextInt();
        System.out.println("输入商品的价格:");
        price = sc.nextDouble();
        totalPrice = price*num  ;
    }
    public String toString(){
        System.out.println("商品名称 : "+name+"\t商品数量 : "+num+"\t商品单价 : "+price+"\t商品总价 : "+totalPrice);
        return "";
    }
}