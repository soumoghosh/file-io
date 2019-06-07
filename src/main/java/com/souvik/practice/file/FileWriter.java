package com.souvik.practice.file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileWriter {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "root");
		StringBuilder sb = new StringBuilder();
		String fileName = "E:\\code\\file-io\\src\\main\\resources\\csv\\teachers1.csv";

		String sql = "select * from teacher1";

		// OutputStream fos = new FileOutputStream(fileName);
		PrintWriter pw = new PrintWriter(fileName);

		PreparedStatement psmt = con.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		for (int i = 0; i < 1; i++) {
			sb.append("id" + "," + "name" + "," + "dept" + "," + "course" + "\n");
		}

		while (rs.next()) {

			sb.append(rs.getInt("id"));
			sb.append(",");
			sb.append(rs.getString("name"));
			sb.append(",");
			sb.append(rs.getString("dept"));
			sb.append(",");
			sb.append(rs.getString("course"));
			sb.append("\n");

		}
		pw.write(sb.toString());
		pw.close();
	}

}
