package com.souvik.practice.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FileReaderDuplicate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		StringBuilder sb = new StringBuilder();

		String filePath = "E:\\code\\file-io\\src\\main\\resources\\csv\\teachers.csv";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "root");

		InputStream fis = new FileInputStream(filePath);

		while (true) {
			int a = fis.read();
			if (a == -1) {
				break;
			}
			char b = (char) a;
			sb.append(b);
		}
		System.out.println(sb);

		String[] arry = sb.toString().split("\n");
		for (int i = 1; i < arry.length; i++) {
			System.out.println(arry[i]);
			System.out.println("***********");
			String[] char1 = arry[i].split(",");
			for (int j = 0; j < char1.length; j++) {
				System.out.println(char1[j]);
			}
			String sql = "insert into teacher1 (name, dept, course) values(?,?,?)";
			fis.close();

		}

	}

}
