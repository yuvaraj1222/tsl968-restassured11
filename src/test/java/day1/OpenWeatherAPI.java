package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherAPI {
	
  @Test(enabled=false, description="Getting weather information")
  public void getWeatherInfo() {
	  
	  RestAssured.given()
	             .when()
	                 .get("http://api.openweathermap.org/data/2.5/weather?q=chennai&appid=8e831820f79f88d614047fbcd7306bc5")
	             .then()    
	                 .log()
	                 .body()
	                 .statusCode(200);
	  }
  
  @Test(enabled=false, description="Getting weather information")
  public void getWeatherInfo1() {
	  
	 Response res= RestAssured.given()
	             .when()
	                 .get("http://api.openweathermap.org/data/2.5/weather?q=chennai&appid=8e831820f79f88d614047fbcd7306bc5");
	           System.out.println(res.prettyPrint());
	           System.out.println(res.getTime());
	           System.out.println(res.getStatusCode());
	           System.out.println(res.getContentType());
	  }
  
  @Test(enabled=true, description="Getting weather information")
  public void getWeatherInfo2() {
	  //using map method, we passing parameters
	  
	  Map<String,String> param=new HashMap<String,String>();
	  param.put("q", "Mumbai");
	  param.put("appid", "8e831820f79f88d614047fbcd7306bc5");
	  
	  RestAssured.given()
	            
	                 // .queryParam("q", "Mumbai")
	                  //.queryParam("appid", "8e831820f79f88d614047fbcd7306bc5")
	           .params(param)
	              .when()
	                 .get("http://api.openweathermap.org/data/2.5/weather")
	              .then()
	                 .log()
	                 .body()
	                 .statusCode(200);
	         
	  }
  
  

  
  
}
