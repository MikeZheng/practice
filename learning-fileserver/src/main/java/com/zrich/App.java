package com.zrich;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String filepath ="C:\\Users\\ZhenFuZheng\\Documents\\EGSC\\开发相关.zip";
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("104.156.238.11", 33456), 10*1000);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        dataOutputStream.writeUTF(file.getName());
        int length;
        while ((length=fileInputStream.read(bytes))>0){
            dataOutputStream.write(bytes, 0, length);
            dataOutputStream.flush();
        }
    }
}
