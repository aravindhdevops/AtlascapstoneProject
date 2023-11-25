package com.Utils;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class CSVReader {
	public static String getData(String colName, int index) {
		List<String> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("src/inputCSV.csv"));
				CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
			) {
			for (CSVRecord record : parser) {
				data.add(record.get(colName));
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return data.get(index);
	}
	
	public static List<String> getData(String colName) {
		List<String> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("src/mysql_input_queries/query.csv"));
				CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
			) {
			for (CSVRecord record : parser) {
				data.add(record.get(colName));
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return data;
	}
	
	public static int getRowLength(String colName) {
		List<String> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("src/mysql_input_queries/query.csv"));
				CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
			) {
			for (CSVRecord record : parser) {
				data.add(record.get(colName));
			}
			data.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return data.size();	
	}
	
}
