package cn.ruanyudi.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RunTest{
    public static void main(String[] args) throws Exception{
        File file1 = new File("./test1.txt");
        File file2 = new File("./test2.txt");
        FileInputStream fileInputStream = new FileInputStream(file1);
        FileOutputStream fileOutputStream =new FileOutputStream(file2);
        byte[] buffer = new byte[1024];
        int len =0;
        while((len=fileInputStream.read(buffer))!=-1){
            System.out.println(len);
            fileOutputStream.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}