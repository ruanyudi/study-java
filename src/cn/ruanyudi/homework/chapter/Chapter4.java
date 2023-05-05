package cn.ruanyudi.homework.chapter;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import org.json.JSONObject;

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
                case 3:
                    new WeatherApi().show();
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

class WeatherApi {
    Scanner sc = new Scanner(System.in);
    String key = "f31902ff88b14313a3d283ab25edd978";
    public void show(){
        System.out.println("Input the City name which you want to queue : ");
        String city = sc.next();
        try {
            city = URLEncoder.encode(city,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String location ;
        try {
            location = netGet("https://geoapi.qweather.com/v2/city/lookup?location="+city+"&key="+key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JSONObject jsLocation = new JSONObject(location);
//       System.out.println(jsLocation.getJSONArray("location"));
        JSONObject jsonitem = (JSONObject) jsLocation.getJSONArray("location").get(0);
        System.out.println("城市 : "+jsonitem.get("name"));
        String result = "";
        try {
            result = netGet("https://devapi.qweather.com/v7/weather/now?location="+jsonitem.get("id")+"&key="+key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = new JSONObject(result);
        jsonObject = jsonObject.getJSONObject("now");
        System.out.println("气温 : "+jsonObject.get("temp"));
        System.out.println("天气 : "+jsonObject.get("text"));
        System.out.println("风向 : "+jsonObject.get("windDir"));
    }
    String  netGet(String urlPath) throws Exception {
        URL url = new URL(urlPath);
        URLConnection conn = url.openConnection();
        GZIPInputStream is = new GZIPInputStream(conn.getInputStream());
        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        isr.close();
        is.close();
        return sb.toString();
    }
}
