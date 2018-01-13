package com.zrich;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    /**
     * Socket服务端
     */
    public static void main(String[] args) {
        final String folder = args[0];

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            ServerSocket serverSocket = new ServerSocket(33456);
            System.out.println("服务端已启动，等待客户端连接..");

            while (true) {
                Socket socket = serverSocket.accept();// 侦听并接受到此套接字的连接,返回一个Socket对象
                SocketThread socketThread = new SocketThread(socket, folder);
                executorService.execute(socketThread);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*

    public static void main(String[] args) {
        final String folder = args[0];
        try {
            final ServerSocket server = new ServerSocket(33456);
            Thread th = new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("start listening ....");
                        Socket socket = server.accept();
                        System.out.println("accept data....");
                        receiveFile(socket, folder);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            th.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void receiveFile(Socket socket, String folder) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream din = null;
        FileOutputStream fout = null;
        try {
            din = new DataInputStream(socket.getInputStream());
            File file = new File(folder + File.separator + din.readUTF());
            file.createNewFile();
            fout = new FileOutputStream(file);
            System.out.println(" ======================= Accept new file " + file.getName());
            System.out.println(" ======================= file path = "+ file.getAbsolutePath());
            inputByte = new byte[1024];
            System.out.println("start receive data.....");
            while (true) {
                if (din != null) {
                    length = din.read(inputByte, 0, inputByte.length);
                }
                if (length == -1) {
                    break;
                }
                System.out.println(length);
                fout.write(inputByte, 0, length);
                fout.flush();
            }
            System.out.println("============================= complete receiving file .... ");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null)
                fout.close();
            if (din != null)
                din.close();
            if (socket != null)
                socket.close();
        }
    }*/


}
