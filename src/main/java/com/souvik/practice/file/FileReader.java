package com.souvik.practice.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileReader {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "root");
		String sql = null;
		int j;
		StringBuilder sb = new StringBuilder();
		// String fileName =
		// "E:\\code\\file-io\\src\\main\\resources\\csv\\teachers.csv";
		File f = new File("E:\\code\\file-io\\src\\main\\resources\\csv\\teacher1.csv");
		String name = f.getName();
		String fileName = name.substring(0, name.indexOf("."));
		try (InputStream fis = new FileInputStream(f);) {

			while (true) {

				int a = fis.read();
				if (a == -1) {
					break;
				}
				char b = (char) a;
				sb.append(b);
			}
			// System.out.println(sb);
			String[] perString = sb.toString().split("\n");
			int length = perString[0].split(",").length;
			String[] char2 = perString[0].split(",");
			String columns = "";
			String placeHolder = "";
			for (j = 0; j < length; j++) {
				columns = columns + char2[j];
				placeHolder = placeHolder + "?";
				if (j < length - 1) {
					columns = columns + ",";
					placeHolder = placeHolder + ",";
				}
			}
			sql = "insert into " + fileName + "(" + columns + ") values (" + placeHolder + ")";

			for (int i = 1; i < perString.length; i++) {
				String[] char1 = perString[i].split(",");

				/*
				 * String sql =
				 * "insert into teacher1 (name, dept, course) values('" +
				 * char1[0].trim() + "','" + char1[1].trim() + "','" +
				 * char1[2].trim() + "')";
				 */

				// System.out.println(sql);
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, char1[0]);
				ps.setString(2, char1[1]);
				ps.setString(3, char1[2]);
				ps.execute();
				ps.close();

			}

		}

	}

}
