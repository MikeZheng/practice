package com.zrich;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketClient {

    /**
     * Hello world!
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        File file = new File(args[0]);
        upload(args[0]);
    }

    public static void upload(String path) throws IOException, InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                upload(files[i].getAbsolutePath());
            }
        } else {
            uploadFile(path);
            return;
        }
    }

    public static void uploadFile(String filepath) throws IOException {
        System.out.println(" ============================== Start upload " + filepath);
        long time = System.currentTimeMillis();
        Socket socket = new Socket();
        int times = 0;
        while (times < 10) {
            try {
//                socket.connect(new InetSocketAddress("104.156.238.11", 33456), 10 * 1000);
                socket.connect(new InetSocketAddress("localhost", 33456), 10 * 1000);
                break;
            } catch (IOException e) {
                times++;
            }
        }
        if (times == 10) {
            System.out.println("==============================  Fail to upload " + filepath);
            return;
        }
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        long size = file.length();
        dataOutputStream.writeUTF(file.getName());
        int length;
        int count = 0;
        int percent = 10;

        while ((length = fileInputStream.read(bytes)) > 0) {
            count += length;
            if (count * 100 / size > percent) {
                System.out.println("============================== uploading " + percent + "% already.");
                percent += 10;
            }
            dataOutputStream.write(bytes, 0, length);
            dataOutputStream.flush();
        }
        System.out.println(" ============================== Complete upload " + filepath + ", cost " + (System.currentTimeMillis() - time) + " ms");

    }


}
