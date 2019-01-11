package com.kasback.abstestbase;

import java.util.ArrayList;

import org.testng.Assert;

import com.kasback.utils.PostgresqlUtil;

public class UITestBase extends TestBase{
	private PostgresqlUtil pgutil1 = null;
	public String Result;
	public ArrayList<String> testresult;
	
	public UITestBase() {
		this.pgutil1 = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"),
				environment.get("dbpwd"));
		if (this.pgutil1 == null) {
			log.info("DB Connection failed");
			this.pgutil1 = pgutil;
		}
		if (this.pgutil1 == null) {
			Assert.fail("database connection not successful");
		}
	}

	public UITestBase(PostgresqlUtil pgutil) {
		this.pgutil1 = pgutil;

	}
	
	/*public ExpectedDBConditions getDBCondition = (String query) -> {
		String result = pgutil1.executeSelectQuery(query);
		log.info("SQL Query " + query + "result: -" + result + " is  : " + result);
		return result.isEmpty();
	};*/
	
	
	
	public void updateEmailVerificationStatus(String emailId) {
		String email_verification_status = pgutil1.executeSelectQuery("select email_verification from kasback.user where email="+"'"+emailId+"'");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Old Status "+email_verification_status);
		pgutil1.executeUpdateQuery("update kasback.user set email_verification=1 where email='" + emailId + "'");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("New status "+email_verification_status);
		
	}
	
	public String getOrderId() {
		String orderId = pgutil.executeSelectQuery("select * from kasback.buyer_order_product order by created desc limit 1;");
		return orderId;
	}
	
	public void updateOrderStatus() {
		pgutil1.executeUpdateQuery("update buyer_order_product set order_status="+"'shipped'"+ " where id="+getOrderId()+";");
	}
	
	public void deactivateSeller(String sellerEmail) {
		pgutil1.executeUpdateQuery("update kasback.user set is_deleted=1 where email='"+sellerEmail+"';");
	}
	
	public void activateSeller(String sellerEmail) {
		pgutil1.executeUpdateQuery("update kasback.user set is_deleted=0 where email='"+sellerEmail+"';");
	}

}
