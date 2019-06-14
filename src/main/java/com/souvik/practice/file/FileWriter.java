package com.souvik.practice.file;

import java.io.File;
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
		File f = new File("E:\\code\\file-io\\src\\main\\resources\\csv\\teacher1.csv");
		String name = f.getName();
		String fileName = name.substring(0, name.indexOf("."));
		int i;

		String sql = "select * from " + fileName;

		// OutputStream fos = new FileOutputStream(fileName);
		PrintWriter pw = new PrintWriter(f);

		PreparedStatement psmt = con.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		int columnCount = rs.getMetaData().getColumnCount();
		String columnName = "";
		for (i = 1; i <= columnCount; i++) {
			String columnName1 = rs.getMetaData().getColumnName(i);
			columnName = columnName + columnName1;
			if (i <= columnCount - 1) {
				columnName = columnName + ",";
			}
		}
		sb.append(columnName + "\n");
		while (rs.next()) {
			for (int j = 1; j <= columnCount; j++) {
				sb.append(rs.getObject(j));
				if (j <= columnCount - 1)
					sb.append(",");
				// sb.append(rs.getInt(1));
				// sb.append(",");
				// sb.append(rs.getString(2));
				// sb.append(",");
				// sb.append(rs.getString(3));
				// sb.append(",");
				// sb.append(rs.getString(4));
			}
			sb.append("\n");

		}

		pw.write(sb.toString());
		pw.close();
	}
}