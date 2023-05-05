package cn.ruanyudi.run;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
//            connection.setDoOutput(true);
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
//            os = connection.getOutputStream();
            System.out.println(connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                System.out.println("连接成功 ");
                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine() )!= null) {
                    sbf.append(temp);
//                    sbf.append("\r\n");
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

public class RunTest{
    //
    public static void main(String[] args) {
        HttpPost httpPost = new HttpPost();
        String result = httpPost.doPost("https://devapi.qweather.com/v7/weather/now?location=101010100&key=f31902ff88b14313a3d283ab25edd978");
        System.out.println(result);
        URL url;
        try {
            url = new URL("https://devapi.qweather.com/v7/weather/now?location=101010100&key=f31902ff88b14313a3d283ab25edd978");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(httpURLConnection.getResponseCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            byte [] bytes = new byte[1024];
            inputStreamReader.read(bytes);
            System.out.println(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}