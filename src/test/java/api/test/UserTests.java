package api.test;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setUsername(faker.name().username());
		userPayload.setPhone(faker.phoneNumber().phoneNumber());
		
		logger = LogManager.getLogger(this.getClass());
				
	}
	
	
	@Test (priority = 1)
	public void testPostUser()
	{
		logger.info("-------------Creating new User---------");
		Response res = UserEndpoints.createUser(userPayload);
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("-------------Created new User---------");

	}
	
	@Test (priority = 2)
	public void testGetUser()
	{
		logger.info("-------------Get User Details---------");

		Response res = UserEndpoints.readUser(this.userPayload.getUsername());
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("-------------Got User Details---------");
		
	}
	
	@Test (priority = 3)
	public void testupdateUserbyName()
	{
		logger.info("-------------Updating User Details---------");
		
		//-------Data to be updated-------
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		
		Response res = UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
		
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("-------------Updated User Details---------");
	}
	
	@Test (priority = 4)
	public void testDeleteUserbyName()
	{
		logger.info("-------------Deleting Details---------");
		Response res = UserEndpoints.deleteUser(this.userPayload.getUsername());
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("-------------Deleted User Details---------");
	}

}
