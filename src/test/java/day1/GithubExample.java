package day1;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GithubExample {

	@Test(enabled = false, description = "getting all repositories")
	public void getAllrepo() {
		given()
		.auth()
		.oauth2("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")
		.when()
				.get("https://api.github.com/user/repos")
				.then()
				.log()
				.body()
				.statusCode(200)
				.time(Matchers.lessThan(2000L), TimeUnit.MILLISECONDS);
	}

	@Test(enabled = true, description = "Adding repositories")
	public void addRepo() {
		JSONObject js = new JSONObject();
		js.put("name", "tsl968-restAssured");
		js.put("description", "i am created by RestAssured");
		js.put("homepage", "http://github.com/yuvaraj1222");

		given()
		.auth()
		.oauth2("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")
		.header("Content-Type", "application/json")
		.body(js.toJSONString())
				.when()
				  .post("https://api.github.com/user/repos")
				     .then()
				       .log()
				       .body()
				       .statusCode(201)// when creating, status code=201
				       .time(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS);
	}
}
