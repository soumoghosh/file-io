package com.souvik.practice.file;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionSort {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "root");
		StringBuilder sb = new StringBuilder();
		File f = new File("E:\\code\\file-io\\src\\main\\resources\\csv\\teacher1.csv");
		String name = f.getName();
		String fileName = name.substring(0, name.indexOf("."));
		String sql = "select * from " + fileName;
		int counter = 0;
		PreparedStatement psmt = con.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		int columnCount = rs.getMetaData().getColumnCount();
		TreeSet set = new TreeSet(new MyComperator());
		while (rs.next()) {
			Test test = new Test();

			for (int j = 1; j <= columnCount; j++) {
				String cname = rs.getMetaData().getColumnName(j);
				switch (cname) {
				case "id":
					test.id = rs.getInt(j);
					break;
				case "name":
					test.name = rs.getString(j);
					break;
				case "dept":
					test.dept = rs.getString(j);
					break;
				case "course":
					test.course = rs.getString(j);
					break;
				}
			}
			set.add(test);
		}
		System.out.println(set);
	}

}

class Test {
	int id;
	String name;
	String dept;
	String course;

	@Override
	public String toString() {
		return id + "," + name + "," + dept + "," + course;

	}

	/*
	 * @Override public int compareTo(Test o) { int id1 = o.id; int id2 =
	 * this.id; if (id1 < id2) { return 1; } else if (id1 > id2) { return -1; }
	 * 
	 * return 0; }
	 */
}

class MyComperator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		Test t1 = (Test) o1;
		Test t2 = (Test) o2;

		String name1 = t1.name;
		String name2 = t2.name;
		return name1.compareTo(name2);
	}

}
