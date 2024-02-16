package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;

public class UserEndpoints2 {
	
	 static ResourceBundle getURL()
	{
		ResourceBundle resRoute = ResourceBundle.getBundle("routes");
		return resRoute;
	}
	
	public static Response createUser(User payload)
	{
		
		String post_url = getURL().getString("post_url");
		Response res = 
				given()
				   .contentType("application/json")
				   .accept("application/json")	
				   .body(payload)
				   
				.when()
				   .post(post_url);
				   
				 return res;
		
	}
	
	//------------For get Request
	public static Response readUser(String userName)
	{
		String get_url = getURL().getString("get_url");
		Response res = 
				given()
				   .pathParam("username",userName)
				   
				.when()
				   .get(get_url);
				   
				 return res;
		}
	
	//-----------For updating Request----------
	public static Response updateUser(User payload, String userName)
	{
		String update_url = getURL().getString("update_url");
		Response res = 
				given()
				   .contentType(ContentType.JSON)
				   .accept(ContentType.JSON)
				   .body(payload)
				   .pathParam("username",userName)
				   
				.when()
				   .put(update_url);
				   
				 return res;
		
	}
	
	//------------For Delete Request-----------------------
		public static Response deleteUser(String userName)
		{
			
			String delete_url = getURL().getString("delete_url");
			Response res = 
					given()
					   .pathParam("username",userName)
					   
					.when()
					   .delete(delete_url);
					   
					 return res;
			}

		
}
