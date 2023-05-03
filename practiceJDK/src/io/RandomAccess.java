package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
	public static void main(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile(new File("src/io/RandomAFile.txt"), "rw");
//			raf.write("01234条件\r\n56789\r\nabcd\r\nefg\r\nhi\r\nj\r\nk".getBytes("UTF-8"));//文件中内容
			raf.seek(0);
//			byte[] bytes = new byte[1];
			while (raf.getFilePointer() != raf.length()) {
				long i = raf.getFilePointer();
				System.out.println("i=" + i);
//				String s = new String(raf.readLine().getBytes("UTF-8"));
				String str = new String(raf.readLine().getBytes("ISO-8859-1"),"UTF-8");//readLine默认字符集为ISO-8859-1，转换字符串是需要重构字符串
				if (str.contains("cd")) {
					System.out.println("i=" + i);
					raf.seek(i+2);
//					String replace = s.replace("cd", "指针");
					raf.write("指针格式".getBytes("UTF-8"));//
				}
				if (str.contains("指针")) {
					System.out.println("i=" + i);
					raf.seek(i);
					raf.write(str.replace("指针格式", "吞吐量大小").getBytes());//无法识别
				}
			}
			raf.seek(0);
			while (raf.getFilePointer() != raf.length()) {
				String s = raf.readLine();
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
