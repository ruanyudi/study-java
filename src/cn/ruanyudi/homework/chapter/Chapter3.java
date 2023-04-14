package cn.ruanyudi.homework.chapter;

import org.junit.Test;

import java.util.*;

import static java.lang.System.exit;

public class Chapter3 {
    public static void showMenu() {
        System.out.println("\n欢迎查看第三章节的作业，请选择要查看的作业号:");
        while (true) {
            System.out.println("1——客户选购信息管理系统");
            System.out.println("2——多线程判断素数");
            System.out.print("3——多线程货物管理");
            System.out.println("输入你的选择（0-退出）");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    new CustomerGoodsAdmin().show();
                    break;
                case 2:
                    new InterfaceThread().show();
                    break;
                case 3:
                    new ManagedGoodsInterface().show();
                    break;
            }
//            System.out.println("输入0以继续");
//            sc.nextInt();
        }
    }
}

class InterfaceThread {
    @Test
    public void show() {
        System.out.println("请输入十个数字");
        int[] data = new int[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            data[i] = sc.nextInt();
//            data[i] =i;
        }
        for (int i = 0; i < 10; i++) {
            MultiThread thread = new MultiThread(data[i]);
            thread.start();
        }
    }
}

class MultiThread extends Thread {
    private final int number;

    MultiThread(int data) {
        this.number = data;
    }

    public void run() {
        if (number == 1 || number == 0) {
            System.out.println(number + "不是素数");
            return;
        }
        for (int i = 2; i <= java.lang.Math.sqrt(number); i++) {
            if (number % i == 0) {
                System.out.println(number + "不是素数");
                return;
            }
        }
        System.out.println(number + "是素数");
    }
}

class CustomerGoodsAdmin {
    Scanner sc = new Scanner(System.in);
    Customer[] customers;

    public void show() {
        while (true) {
            int choice = 0;
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
            switch (choice) {
                case 0:
                    return;
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

    protected void inputCustomerInfo() {
        System.out.println("输入要录入的Customer个数:");
        int number = sc.nextInt();
        int preLength = customers != null ? customers.length : 0;
        if (preLength == 0) {
            customers = new Customer[number];
        } else {
            customers = Arrays.copyOf(customers, preLength + number);
        }
        for (int i = 0; i < number; i++) {
            System.out.println("-----------------------");
            customers[preLength + i] = new Customer();
        }
    }

    private void print() {
        if (customers == null) {
            System.out.println("当前没有用户信息");
            return;
        }
        System.out.println("select the CustomerName (0 for AllCustomer):");
        String choice = sc.next();
        if (choice.equals("0")) {
            System.out.println("系统当前有" + customers.length + "条用户信息");
            for (int i = 0; i < customers.length; i++) {
                System.out.println(customers[i]);
            }
        } else {
            for (Customer customer : customers) {
                if (customer.getName().equals(choice)) {
                    System.out.println(customer);
                    return;
                }
            }
            System.out.println("找不到该用户");
        }
    }

    private void rank() {
        System.out.println("系统当前有" + customers.length + "条用户信息");
        for (int i = 0; i < customers.length; i++) {
            for (int j = customers.length - 1; j > i; j--) {
                if (customers[j].getTotal() > customers[j - 1].getTotal()) {
                    Customer tmp = customers[j];
                    customers[j] = customers[j - 1];
                    customers[j - 1] = tmp;
                }
            }
        }
        for (Customer customer : customers) {
            System.out.println("Name : " + customer.getName() + "\t总计" + customer.getTotal());
        }
    }
}

class Customer {
    Scanner sc = new Scanner(System.in);
    private final String name;
    private final int age;
    private final Goods[] goods;
    private double total;

    Customer() {
        System.out.println("输入客户的姓名:");
        name = sc.next();
        System.out.println("输入用户的年龄:");
        age = sc.nextInt();
        System.out.println("输入购买的商品数量:");
        int number = sc.nextInt();
        goods = new Goods[number];
        for (int i = 0; i < goods.length; i++) {
            System.out.println("-----------------------");
            goods[i] = new Goods();
            total += goods[i].getTotalPrice();
        }
    }

    public double getTotal() {
        return total;
    }

    public void print() {

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        System.out.println("-----------------------");
        System.out.println("Name : " + getName() + "\t\tAge : " + getAge());
        System.out.println("-----------------------");
        for (Goods good : goods) {
            System.out.println(good);
        }
        System.out.println("总计 : " + total);
        return "";
    }
}

class Goods {
    Scanner sc = new Scanner(System.in);
    private final String name;
    private final int num;
    private final double price;
    private final double totalPrice;

    Goods() {
        System.out.println("输入商品的名称:");
        name = sc.next();
        System.out.println("输入商品的数量:");
        num = sc.nextInt();
        System.out.println("输入商品的价格:");
        price = sc.nextDouble();
        totalPrice = price * num;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String toString() {
        System.out.println("商品名称 : " + name + "\t商品数量 : " + num + "\t商品单价 : " + price + "\t商品总价 : " + totalPrice);
        return "";
    }
}

class ManagedGoods {
    private String name = "";
    private int quantity = 0;
    private boolean supplier = false;

    public ManagedGoods(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public synchronized void reduceQuantity(int amount) {
        quantity -= amount;
        System.out.println("Goods : " + this.getName() + " is REDUCING | Cur Q :" + this.getQuantity());
    }

    public synchronized void addQuantity(int amount) {
        quantity += amount;
        System.out.println("Goods : " + this.getName() + " is ADDING | Cur Q :" + this.getQuantity());
    }

    public synchronized void startSupplier() {
        if (isSupplier()) return;
        System.out.println("Goods : " + this.getName() + " Start Supplier");
        this.supplier = true;
    }

    public synchronized boolean isSupplier() {
        return supplier;
    }
}


class ManagedGoodsInterface {
    Scanner sc = new Scanner(System.in);
    int numberGoods;
    int numberSeller;
    List<ManagedGoods> items = new ArrayList<ManagedGoods>();
    Random rand = new Random();

    @Test
    public void show() {
        System.out.println("Input the number of Goods : ");
        numberGoods = sc.nextInt();
        System.out.println("Input the seller of the System : ");
        numberSeller = sc.nextInt();
        System.out.println("Managment Start ...");
        for (int i = 0; i < numberGoods; i++) {
            ManagedGoods tmp = new ManagedGoods(Integer.toString(i), rand.nextInt(50, 100));
            items.add(tmp);
        }
//        for(int i=0;i< items.size();i++){
//            System.out.println("Name : "+items.get(i).getName()+" Quantity : "+Integer.toString(items.get(i).getQuantity()));
//        }
        work();
        exit(0);
    }

    private void work() {
        for (int i = 0; i < numberGoods; i++) {
            ManagedGoods tmp = new ManagedGoods(Integer.toString(i), rand.nextInt(50, 100));
            items.add(tmp);
        }
        for (int i = 0; i < numberSeller; i++) {
            GoodsOutputer tmp = new GoodsOutputer(items);
            tmp.start();
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class GoodsInputer extends Thread {
    private final List<ManagedGoods> items;
    private final int id;
    private final Random random = new Random();

    public GoodsInputer(List<ManagedGoods> items, int id) {
        this.id = id;
        this.items = items;
    }

    public void run() {
        while (true) {
            int amount = random.nextInt(items.size()) + items.size();
            items.get(id).addQuantity(amount);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class GoodsOutputer extends Thread {
    private final List<ManagedGoods> items;
    private final Random random = new Random();

    public GoodsOutputer(List items) {
        this.items = items;
    }

    public void run() {
        while (true) {
            int id = random.nextInt(items.size());
            int amount = random.nextInt(items.size());
            ManagedGoods cur = items.get(id);
            cur.reduceQuantity(amount);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (cur.getQuantity() < 20 && !cur.isSupplier()) {
                cur.startSupplier();
                new GoodsInputer(items, id).start();
            }
        }
    }
}