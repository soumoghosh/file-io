package com.souvik.practice.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Collectionsort1 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "root");
		File f = new File("E:\\code\\file-io\\src\\main\\resources\\csv\\teacher1.csv");
		String name = f.getName();
		String fileName = name.substring(0, name.indexOf("."));
		int i;

		String sql = "select * from " + fileName;

		PreparedStatement psmt = con.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		int columnCount = rs.getMetaData().getColumnCount();
		String columnName = "";
		List list = new ArrayList();
		while (rs.next()) {
			StringBuilder sb = new StringBuilder();
			for (int j = 1; j <= columnCount; j++) {
				sb.append(rs.getObject(j));
				if (j <= columnCount - 1)
					sb.append(",");
			}

			list.add(sb.toString());
		}
		Collections.sort(list, new MySort());
		System.out.println(list);
	}
}

class MySort implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.split(",")[1].compareTo(o2.split(",")[1]);
	}

}
