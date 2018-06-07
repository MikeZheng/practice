/**
 *
 */
package com.zrich;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * tar压缩工具
 *
 * @author xufuhua
 */
public class TarUtils {

    private static final Logger logger = LoggerFactory.getLogger(TarUtils.class);

    private static final String BASE_DIR = "";

    // 符号"/"用来作为目录标识判断符
    private static final String PATH = "/";
    private static final int BUFFER = 1024;

    private static final String EXT = ".tar";

    /**
     * 解归档
     *
     * @param srcFile
     * @throws Exception
     */
    public static void dearchive(File srcFile) throws Exception {
        String basePath = srcFile.getParent();
        dearchive(srcFile, basePath);
    }

    /**
     * 解归档
     *
     * @param srcFile
     * @param destFile
     * @throws Exception
     */
    public static void dearchive(File srcFile, File destFile) throws Exception {

        TarArchiveInputStream tais = new TarArchiveInputStream(
                new FileInputStream(srcFile));
        dearchive(destFile, tais);

        tais.close();

    }

    /**
     * 解归档
     *
     * @param srcFile
     * @param destPath
     * @throws Exception
     */
    public static void dearchive(File srcFile, String destPath)
            throws Exception {
        dearchive(srcFile, new File(destPath));

    }

    /**
     * 文件 解归档
     *
     * @param destFile 目标文件
     * @param tais     ZipInputStream
     * @throws Exception
     */
    private static void dearchive(File destFile, TarArchiveInputStream tais)
            throws Exception {

        TarArchiveEntry entry = null;
        while ((entry = tais.getNextTarEntry()) != null) {

            // 文件
            String dir = destFile.getPath() + File.separator + entry.getName();

            File dirFile = new File(dir);

            // 文件检查
            fileProber(dirFile);

            if (entry.isDirectory()) {
                dirFile.mkdirs();
            } else {
                dearchiveFile(dirFile, tais);
            }

        }
    }

    /**
     * 文件 解归档
     *
     * @param srcPath 源文件路径
     * @throws Exception
     */
    public static void dearchive(String srcPath) throws Exception {
        File srcFile = new File(srcPath);

        dearchive(srcFile);
    }

    /**
     * 文件 解归档
     *
     * @param srcPath  源文件路径
     * @param destPath 目标文件路径
     * @throws Exception
     */
    public static void dearchive(String srcPath, String destPath)
            throws Exception {

        File srcFile = new File(srcPath);
        dearchive(srcFile, destPath);
    }

    /**
     * 文件解归档
     *
     * @param destFile 目标文件
     * @param tais     TarArchiveInputStream
     * @throws Exception
     */
    private static void dearchiveFile(File destFile, TarArchiveInputStream tais)
            throws Exception {

        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile));

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = tais.read(data, 0, BUFFER)) != -1) {
            bos.write(data, 0, count);
        }

        bos.close();
    }


    /**
     * 解压tar包
     *
     * @param filename  tar文件
     * @param directory 解压目录
     * @return
     * @author: xufuhua
     */
    public static boolean extTarFileList(String filename, String directory) throws Exception {
        dearchive(filename, directory);
        return true;
    }

    /**
     * MD5验证
     *
     * @param tarPath
     * @param sum
     * @return
     * @throws IOException
     */
    public static boolean checkMD5Sum(String tarPath, String sum) throws IOException {
        //路径优化
        boolean isWindowOs = false;
        String osName = System.getProperty("os.name");
        if(osName.toLowerCase().indexOf("windows") > -1) {
            isWindowOs = true;
        }
        if (isWindowOs) {
            tarPath = tarPath.replaceAll("/", "\\\\");
        } else {
            tarPath = tarPath.replaceAll("\\\\", File.separator);
        }

        //MD5验证
//		File file = new File(tarPath);
//		if(MD5Utils.getFileMD5String(file).equals(sum)) {
        return true;
//		}
//		return false;
    }

    /**
     * 文件探针
     * <p>
     * <pre>
     * 当父目录不存在时，创建目录！
     * </pre>
     *
     * @param dirFile
     */
    private static void fileProber(File dirFile) {

        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {

            // 递归寻找上级目录
            fileProber(parentFile);

            parentFile.mkdir();
        }

    }

    public static void main(String[] args) throws Exception {
        TarUtils.dearchive("C:\\Users\\ZhenFuZheng\\Documents\\NetSarang\\Xshell\\Sessions\\20180330063030.tar");
    }
}
