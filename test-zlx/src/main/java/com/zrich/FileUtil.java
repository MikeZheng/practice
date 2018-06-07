package com.zrich;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    public static void main(String[] args) {
        String path = "C:\\workspace\\scenic-hotel-pom";
        deleteFile(path, ".iml");
        deleteFile(path, ".project");
        deleteFile(path, ".classpath");
        deleteFolder(path,  ".settings");
    }

    private static void deleteFolder(String path, String folder) {
        File file = new File(path);
        if (file.isDirectory()) {
            if(file.getAbsolutePath().contains(folder)) {
                deleteFolder(file.getAbsolutePath());
                System.out.println(file.getAbsolutePath());
            } else {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    deleteFolder(file1.getAbsolutePath(), folder);
                }
            }
        }
    }

    public static void deleteFolder(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            deleteFile(path, "");
            file.delete();
        } else {
            file.delete();
        }
    }

    public static void deleteFile(String path, String fileRegex) {
        File file = new File(path);
        List<File> fileList = new ArrayList<>();
        printFile(file, fileList);
        List<File> needFileList =  fileList.stream().filter(a -> {return a.getName().contains(fileRegex);}).collect(Collectors.toList());
        printFile(needFileList);
        for (File file1 : needFileList) {
            file1.delete();
        }
    }

    public static void printFile(File file, List<File> fileList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File innerFile = files[i];
                printFile(innerFile, fileList);
            }
        } else {
            fileList.add(file);
        }
    }

    public static void printFile(List<File> files)  {
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

}
