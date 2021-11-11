package day1;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;
public class PositiveTest {
 String id;// declaring id global
 
 //for getting all contacts
 
  @Test(enabled=false,description="for getting all contacts")
  
  public void getAllContactList() {
	  
	  given()
	   .when()
	       .get("http://3.13.86.142:3000/contacts")
	   .then()
	       .log()
	       .body()
	       .statusCode(200);
  }


	//for adding contacts
  
	  @Test(enabled=true,description="adding contacts")
	  public void addContact() {
		  JSONObject loc=new JSONObject();
		  loc.put("city", "pune");
		  loc.put("country", "India");
		  JSONObject emp=new JSONObject();
		  emp.put("jobTitle","Automation Tester");
		  emp.put("company","LTI");
		  JSONObject ob=new JSONObject();
		  ob.put("firstName","yuvaraj");
		  ob.put("lastName","KP");
		  ob.put("email","yuvaraj@gmail.com");
		  ob.put("location",loc);
		  ob.put("employer",emp);
		  
		   id=given()
				  .header("Content-Type","application/json")
				  .body(ob.toJSONString())
		   .when()
		       .post("http://3.13.86.142:3000/contacts")
		   .then()
		       .log()
		       .body()
		       .statusCode(200)
		       .extract()
		       .jsonPath()
		       .get("_id");
		  System.out.println("ID is"+id);
	  }
	  
	  
	 //for getting specific contacts 
	  
	  @Test(enabled=true,dependsOnMethods="addContact",description="getting specific contacts")
	  public void GetSpecificContact() {
		  given()
		  .when()
		     .get("http://3.13.86.142:3000/contacts/"+id)
		  .then()
		    .log()
		    .body()
		    .statusCode(200);
	  } 
	  
	  //for updating contacts
	  
	  @Test(enabled=true,dependsOnMethods="GetSpecificContact",description="updating contacts")
	  public void updateContact() {
		  JSONObject loc=new JSONObject();
		  loc.put("city", "pune");
		  loc.put("country", "India");
		  JSONObject emp=new JSONObject();
		  emp.put("jobTitle","Automation Tester");
		  emp.put("company","LTI");
		  JSONObject ob=new JSONObject();
		  ob.put("firstName","yuvi");
		  ob.put("lastName","KP");
		  ob.put("email","yuvaraj@gmail.com");
		  ob.put("location",loc);
		  ob.put("employer",emp);
		  
		   given()
				  .header("Content-Type","application/json")
				  .body(ob.toJSONString())
		   .when()
		       .put("http://3.13.86.142:3000/contacts/"+id)
		   .then()
		       .log()
		       .body()
		       .statusCode(204);
		      
	  }
	  //for deleting contacts
	  
	  @Test(enabled=true,dependsOnMethods="updateContact",description="deleting specific contacts")
	  public void deleteSpecificContact() {
		  given()  
   .when()
       .delete("http://3.13.86.142:3000/contacts/"+id)
   .then()
       .log()
       .body()
       .statusCode(204);
	  }
	  
	}
