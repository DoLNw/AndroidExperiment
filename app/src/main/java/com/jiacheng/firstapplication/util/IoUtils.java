package com.jiacheng.firstapplication.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * 文件、流操作
 */
public class IoUtils {

    /**
     * 把InputStreamReader读到字符串
     * 
     * @param reader
     *            读取器
     * @return 字符串
     * @throws IOException
     */
    public static String read(InputStreamReader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        String line;
        String lineSeparator = System.getProperty("line.separator");
        try {
            while ((line = br.readLine()) != null)
                builder.append(line).append(lineSeparator);
        } finally {
            br.close();
        }
        return builder.toString();
    }

    /**
     * 复制文件
     * 
     * @param srcFile
     *            源文件
     * @param destFile
     *            目的文件
     * @throws IOException
     */
    public static void copy(File srcFile, File destFile) throws IOException {
        InputStream in = new FileInputStream(srcFile);
        try {
            write(in, destFile);
        } finally {
            in.close();
        }
    }

    /**
     * 读输入流，写入文件
     * 
     * @param inputStream
     *            输入流
     * @param file
     *            写入的文件
     * @throws IOException
     */
    public static void write(InputStream inputStream, File file)
            throws IOException {
        if (file.exists())
            file.delete();
        else
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) >= 0)
                out.write(buffer, 0, bytesRead);
        } finally {
            out.flush();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.getFD().sync();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取图片，构造Bitmap对象。构造的Bitmap对象为原始像素尺寸，与dpi无关。
     * 
     * @param context
     *            Context
     * @param file
     *            图片文件
     * @return Bitmap对象
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Bitmap readBitmap(Context context, File file)
            throws FileNotFoundException, IOException {
        return MediaStore.Images.Media.getBitmap(context.getContentResolver(),
                Uri.fromFile(file));
    }
}
