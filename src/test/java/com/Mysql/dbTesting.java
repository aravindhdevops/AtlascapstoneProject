package com.Mysql;


import java.sql.SQLException;
import java.util.*;

import com.Utils.CSVReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class dbTesting {
	sqlConnector sc;
	@BeforeTest
	public void setUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		sc = new sqlConnector("jdbc:mysql://localhost:3307/amazon?useSSL=true","root","root","com.mysql.cj.jdbc.Driver");
		List<String> query =  new ArrayList<>();
		query.add("drop table if exists sales;");
		query.add("drop table if exists orders;");
		query.add("drop table if exists customers;");
		query.add("drop table if exists products");
		
		for(String x : query) {
			boolean status = sc.deleteTable(x);
			Assert.assertEquals(status, true);
		}
	}
	
	@Test (priority=1)
	public void createTable() throws SQLException {
		List<String> query = new ArrayList<>();
		query = CSVReader.getData("create_all_tables");
		for(int i=0; i<query.size(); i++) {
			boolean status = sc.createTable(query.get(i));
			Assert.assertEquals(status, true);
		}
		System.out.println("Tables created successfully..!!");
		
	}
	
	@Test (priority=2)
	public void insertTable() throws SQLException {
		List<String> query = new ArrayList<>();
		query = CSVReader.getData("insert_records_to_table");
		for(int i=0; i<query.size(); i++) {
			boolean status = sc.insertRecords(query.get(i));
			Assert.assertEquals(status, true);
		}
		System.out.println("Table records inserted successfully..!!");
	}
	
	@Test(priority=3)
	public void findMaxPrice() throws SQLException {
		System.out.println("\n**************Finding product which has max price**************");
		sc.executeQuery("select * from products where price = (select max(price) from products)");
	}
	
	@Test(priority=4)
	public void findDuplicateRecord() throws SQLException {
		System.out.println("\n**************Finding duplicate records**************");
		sc.executeQuery("select product_id, count(*) as count from products group by product_id having count(*)>1;");
	}
	
	@Test(priority=5)
	public void findMostSoldProduct() throws SQLException {
	
		System.out.println("\n**************Finding most sold product**************");
		sc.executeQuery("select product_id, sum(quantity) as total_sold from sales group by product_id order by total_sold desc limit 1;");
		
	}
	
	@Test(priority=6)
	public void findMostSoldProductByRegion() throws SQLException {
		System.out.println("\n**************Finding most sold product in region wise**************");
		sc.executeQuery("select o.region, s.product_id, sum(s.quantity) as total_sold from orders o join sales s on o.order_id = s.sale_id group by region, product_id order by total_sold desc;");
		System.out.println("\n********************************************************************");
	}
	
	

}
