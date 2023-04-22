package cn.ruanyudi.homework.chapter;

import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.util.*;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

interface employment {
    void CallBack();
}

public class Chapter3 {
    public static void showMenu() {
        System.out.println("\nWelcome to view the assignments in Section 3, please select the activity number to view:");
        while (true) {
            System.out.println("1.Customer purchase information management system");
            System.out.println("2.Multi-threaded judgment prime");
            System.out.println("3.Multi-threaded cargo management");
            System.out.println("4.CallbackDemo");
            System.out.println("5.Student information management system");
            System.out.println("Enter your choice (0-exit)");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> new CustomerGoodsAdmin().show();
                case 2 -> new InterfaceThread().show();
                case 3 -> new ManagedGoodsInterface().show();
                case 4 -> new EmploymentSystem().show();
                case 5 -> new StudentManageSystem().show();
            }
//            System.out.println("输入0以继续");
//            sc.nextInt();
        }
    }
}

class InterfaceThread {

    public void show() {
        System.out.println("Please enter ten digits");
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
            System.out.println(number + "Not prime");
            return;
        }
        for (int i = 2; i <= java.lang.Math.sqrt(number); i++) {
            if (number % i == 0) {
                System.out.println(number + "Not prime");
                return;
            }
        }
        System.out.println(number + "is a prime number");
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
            System.out.println("1.\tEnter the customer information");
            System.out.println("2.\tQuery user information");
            System.out.println("3.\tView users Rank");
            System.out.println("0.\tExit System");
            System.out.println();
            choice = sc.nextInt();
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> inputCustomerInfo();
                case 2 -> print();
                case 3 -> rank();
            }
        }

    }

    protected void inputCustomerInfo() {
        System.out.println("Enter the number of customers to enter:");
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
            System.out.println("There is currently no user information");
            return;
        }
        System.out.println("select the CustomerName (0 for AllCustomer):");
        String choice = sc.next();
        if (choice.equals("0")) {
            System.out.println("The system currently has" + customers.length + "user information");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } else {
            for (Customer customer : customers) {
                if (customer.getName().equals(choice)) {
                    System.out.println(customer);
                    return;
                }
            }
            System.out.println("The user could not be found");
        }
    }

    private void rank() {
        System.out.println("The system currently has" + customers.length + "user information");
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
            System.out.println("Name : " + customer.getName() + "\ttotal" + customer.getTotal());
        }
    }
}

class Customer {
    private final String name;
    private final int age;
    private final Goods[] goods;
    Scanner sc = new Scanner(System.in);
    private double total;

    Customer() {
        System.out.println("Enter the customer's name:");
        name = sc.next();
        System.out.println("Enter the user's age:");
        age = sc.nextInt();
        System.out.println("Enter the quantity purchased:");
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
    private final String name;
    private final int num;
    private final double price;
    private final double totalPrice;
    Scanner sc = new Scanner(System.in);

    Goods() {
        System.out.println("Enter a name for the product:");
        name = sc.next();
        System.out.println("Enter the quantity of the item:");
        num = sc.nextInt();
        System.out.println("Enter the price of the item:");
        price = sc.nextDouble();
        totalPrice = price * num;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String toString() {
        System.out.println("Product Name : " + name + "\tNumber of Products : " + num + "\tUnit Price : " + price + "\tTotal price of goods : " + totalPrice);
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
            sleep(20000);
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
        do {
            int amount = random.nextInt(items.size()) + items.size();
            items.get(id).addQuantity(amount);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

}

class GoodsOutputer extends Thread {
    private final List<ManagedGoods> items;
    private final Random random = new Random();

    public GoodsOutputer(List<ManagedGoods> items) {
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

abstract class Leader implements employment {
    private String name;

    Leader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void assignWork(String name) {
        new Worker(name).assignment("Play game", this);
    }
}

class Worker {
    private String name;

    Worker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void assignment(String job, Leader leader) {
        System.out.printf(this.getName() + " is " + job);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        leader.CallBack();
    }
}

class EmploymentSystem {
    public void show() {
        System.out.println("Welcome To EmploymentSystem Starting...");
        Leader leader1 = new Leader("Leader1") {
            @Override
            public void CallBack() {
                System.out.println("Assigned By " + getName() + " Finished");
            }
        };
        Leader leader2 = new Leader("Leader2") {
            @Override
            public void CallBack() {
                System.out.println("Assigned By " + getName() + " Finished");
            }
        };
        leader1.assignWork("worker1");
        leader2.assignWork("worker2");

    }
}


class StudentInfo implements Serializable{
    String major;
    String name;

    StudentInfo(String name, String major) {
        this.name = name;
        this.major = major;
    }

    @Override
    public String toString() {
        return name+"\t"+major;
    }
}


class StudentManageSystem implements Serializable{
    List<StudentInfo> studentsData = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void saveData() throws IOException {
        File file = new File("./SystemData.dat");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(studentsData);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    void loadData(){
        File file = new File("./SystemData.dat");
        if(!file.exists()){
            return ;
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            studentsData = (ArrayList<StudentInfo>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            objectInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void addStudent() {
        System.out.println("Enter student's name : ");
        String name = sc.next();
        System.out.println("Enter the student's major: ");
        String major = sc.next();
        StudentInfo studentInfo = new StudentInfo(name, major);
        studentsData.add(studentInfo);
    }

    void updateStudent() {
        System.out.println("Enter the name of the student you want to modify: ");
        String name = sc.next();
        System.out.println("Enter the information you want to modify : ");
        String major = sc.next();
        Boolean flag = false;
        for(StudentInfo data : studentsData){
            if(Objects.equals(data.name, name)){
                flag = true;
                data.major = major;
                System.out.println("The modification was successful");
                listStudent();
                return ;
            }
        }
        if(flag == false){
            System.out.println("The student was not found");
        }
    }

    void deleteStudent() {
        System.out.println("Enter the name of the student you want to delete: ");
        String name = sc.next();
        Boolean flag = false;
        for(StudentInfo data : studentsData){
            if(Objects.equals(data.name, name)){
                studentsData.remove(data);
                flag = true;
                System.out.println("The deletion was successful");
                listStudent();
                return ;
            }
        }
        if(!flag){
            System.out.println("The student was not found");
        }
    }

    void infoStudent() {
        System.out.println("Enter the name of the student you want to view: ");
        String name = sc.next();
        boolean flag = false;
        for(StudentInfo data : studentsData){
            if(Objects.equals(data.name, name)){
                System.out.println(data);
                return ;
            }
        }
    }
    void listStudent(){
        for(StudentInfo tmp : studentsData){
            System.out.println(tmp);
        }
    }

    public void show() {
        loadData();
        while (true) {
            System.out.println("Student Information Management");
            System.out.println("----------------");
            System.out.println("1 - Student Information Addition");
            System.out.println("2 - Student Information Modification");
            System.out.println("3 - Student Information Deletion");
            System.out.println("4 - Student Information Inquiry");
            System.out.println("5 - View all information");
            System.out.println("0 - exit system");
            System.out.println("----------------");
            int choice = sc.nextInt();
//            System.out.println(choice);
            switch (choice) {
                case 1:{
                    addStudent();
                    break;
                }
                case 2:{
                    updateStudent();
                    break;
                }
                case 3:{
                    deleteStudent();
                    break;
                }
                case 4:{
                    infoStudent();
                    break;
                }
                case 5:{
                    listStudent();
                    break;
                }

                case 0: {
                    try {
                        saveData();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
        }

    }
}