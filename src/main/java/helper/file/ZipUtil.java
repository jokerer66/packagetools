package helper.file;


import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;

public class ZipUtil {

    private static ZipUtil zipUtil;
    public static ZipUtil getInstance() {
        if (zipUtil == null) {
            synchronized (ZipUtil.class) {
                if (zipUtil == null) {
                    zipUtil = new ZipUtil();
                }
            }
        }
        return zipUtil;
    }

    public ZipUtil() {
    }



    //针对输入的文件夹名称进行处理，得到压缩文件名
    public static void zip(String zipFileName, String relativePath, String directory) throws IOException {

        String fileName = zipFileName;
        /*如果传入了压缩包名称zipFileName则使用，如果没有传入，则使用directory作为压缩包名称。
        如果directory是文件夹则直接使用，如果directory是文件，如果已有后缀则替换，如果没有则添加*/
        if (fileName == null || fileName.trim().equals("")) {
            File temp = new File(directory);
            if (temp.isDirectory()) {
                fileName = directory + ".zip";
            } else {
                if (directory.indexOf(".") > 0) {
                    fileName = directory.substring(0, directory.lastIndexOf(".")) + "zip";
                } else {
                    fileName = directory + ".zip";
                }
            }
        }
        //得到压缩文件的名称后，放入zipoutputstream中
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName));
        try {
            zip(zos, relativePath, directory);

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (null != zos) {
                zos.close();
            }
        }
    }

    //
    private static void zip(ZipOutputStream zos, String relativePath, String absolutPath) throws IOException {

            File file = new File(absolutPath);

                if (file.isDirectory()) {
                        File[] files = file.listFiles();
                        for (int i = 0; i < files.length; i++) {
                                File tempFile = files[i];
                                if (tempFile.isDirectory()) {
                                        String newRelativePath = relativePath + tempFile.getName()
                                                + File.separator;
                                        createZipNode(zos, newRelativePath);
                                        zip(zos, newRelativePath, tempFile.getPath());
                                    } else {
                                        zipFile(zos, tempFile, relativePath);
                                    }
                            }
                    } else {
                        zipFile(zos, file, relativePath);
                    }
            }

    //压缩文件
    private static void zipFile(ZipOutputStream zos, File file, String relativePath) throws IOException {
                ZipEntry entry = new ZipEntry(relativePath + file.getName());
                zos.putNextEntry(entry);
                InputStream is = null;
                try {
                       is = new FileInputStream(file);
                        int BUFFERSIZE = 2 << 10;
                        int length = 0;
                        byte[] buffer = new byte[BUFFERSIZE];
                       while ((length = is.read(buffer, 0, BUFFERSIZE)) >= 0) {
                                zos.write(buffer, 0, length);
                            }
                       zos.flush();
                        zos.closeEntry();
                    } catch (IOException ex) {
                        throw ex;
                   } finally {
                        if (null != is) {
                                is.close();
                            }
                    }
            }

    private static void createZipNode(ZipOutputStream zos, String relativePath) throws IOException {
                ZipEntry zipEntry = new ZipEntry(relativePath);
                zos.putNextEntry(zipEntry);
                zos.closeEntry();
            }






    }