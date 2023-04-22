package cn.ruanyudi.run;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RunTest{
    static List<Data> data = new ArrayList<>();
    static void saveData(){
        File file = new File("data.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream =new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void loadData(){
        File file = new File("data.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FileInputStream fileInputStream;
        try {
            fileInputStream= new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            data = (List<Data>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
       loadData();
        System.out.println(data);
    }
}
class Data implements Serializable{
    String data;
    Data(String tmp){
        this.data = tmp ;
    }

    @Override
    public String toString() {
        return this.data ;
    }
}