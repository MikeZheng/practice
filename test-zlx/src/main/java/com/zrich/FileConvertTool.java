package com.zrich;

import java.io.*;

public class FileConvertTool {

    public static void main(String[] args) throws IOException {
        String path="";
        File file = new File(path);
        String folder = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("//"));
        System.out.println(folder);
        File newFile = new File(folder+file.getName()+".txt");
        newFile.createNewFile();

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile));

        byte[] bytes = new byte[1024];
        int length;
        while ((length = inputStream.read(bytes))> 0) {

            outputStream.write(bytes, 0, length);
            outputStream.flush();
        }
        inputStream.close();
        outputStream.close();
    }

}
