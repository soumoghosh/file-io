package com.souvik.practice.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileReaderNumberCalculation {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		int avg = 0;
		int temp;
		String filepath = "E:\\code\\file-io\\src\\main\\resources\\csv\\number.csv";
		InputStream fis = new FileInputStream(filepath);

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
		int size = perString.length;
		int[] intArr = new int[size];
		for (int i = 0; i < size; i++) {

			intArr[i] = Integer.parseInt(perString[i].trim());
			// System.out.println(intArr[i]);
			sum = sum + intArr[i];
			avg = sum / (size - 1);

		}
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (intArr[j] > intArr[j + 1]) {
					temp = intArr[j];
					intArr[j] = intArr[j + 1];
					intArr[j + 1] = temp;
				}
			}
		}

		for (int i : intArr) {
			System.out.println(i);
		}
		System.out.println(sum);
		System.out.println(avg);

		/*
		 * for (int i = 1; i < perString.length; i++) { //
		 * System.out.println(perString[i]); int a =
		 * Integer.parseInt(perString[i].trim()); sum = sum + a; avg = (sum /
		 * (perString.length - 1)); }
		 * 
		 * } System.out.println(sum); System.out.println(avg);
		 */
		fis.close();

	}
}