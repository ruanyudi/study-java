package cn.ruanyudi.homework.chapter;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Chapter4 {
    public static void showMenu() {
        System.out.println("\nWelcome to Section 4 assignments, select the activity number you want to view:");
        while (true) {
            System.out.println("1.File reading and writing");
            System.out.println("2.Image download");
            System.out.println("3.Weather API");
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
                case 4:

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

class HttpPost {
    public static String doPost(String httpUrl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("GET");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            os = connection.getOutputStream();
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
}
