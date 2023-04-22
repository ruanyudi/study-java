package cn.ruanyudi.homework.chapter;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Chapter4 {
    public static void showMenu() {
        System.out.println("\nWelcome to Section 4 assignments, select the activity number you want to view:");
        while (true) {
            System.out.println("1 - File reading and writing");
            System.out.println("2 – Image download");
            System.out.println("Enter your choice (0-exit)");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    new TextReadWrite().show();
                    break;
                case 2:
                    new ImageViewer().show();
                    break;
            }
//            System.out.println("输入0以继续");
//            sc.nextInt();
        }
    }
}

class TextReadWrite{
    String filePath1 = "test1.txt";
    String filePath2 = "test2.txt";

    public void show(){
        File file1 = new File(filePath1);
        if(file1.exists()==false){
            try {
                System.out.println("File not found Automatically create files....");
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File file2 = new File(filePath2);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i<file1.length();i++){
            try {
                fileOutputStream.write(fileInputStream.read());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ImageViewer{
    URL url;

    {
        try {
            url = new URL("https://img2.doubanio.com/view/status/l/public/142605515-a26d520a5d06573.jpg");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    String savePath = "test.jpg";
    public void show(){
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        connection.setConnectTimeout(1000);
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] data;
        try {
            data = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File output = new File(savePath);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(output);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Desktop.getDesktop().open(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}