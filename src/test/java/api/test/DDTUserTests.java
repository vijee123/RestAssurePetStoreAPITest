package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTUserTests {
	
	User userPayload;
	
	@Test (priority =1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void createUserDDT(String UID, String UName, String fName, String lName, String eMail, String pwd, String ph)
	{
		userPayload = new User();
		
		userPayload.setId(Integer.parseInt(UID));
		userPayload.setUsername(UName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(eMail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response res = UserEndpoints.createUser(userPayload);
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test (priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetUser(String UserName)
	{
		Response res = UserEndpoints.readUser(UserName);
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test (priority = 3,  dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserbyName(String UserName)
	{
		Response res = UserEndpoints.deleteUser(UserName);
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}	
	

}
