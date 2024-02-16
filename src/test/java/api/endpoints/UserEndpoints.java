package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payloads.User;
import io.restassured.http.ContentType;

public class UserEndpoints {
	
	public static Response createUser(User payload)
	{
		Response res = 
				given()
				   .contentType("application/json")
				   .accept("application/json")	
				   .body(payload)
				   
				.when()
				   .post(Routes.post_url);
				   
				 return res;
		
	}
	
	//------------For get Request
	public static Response readUser(String userName)
	{
		Response res = 
				given()
				   .pathParam("username",userName)
				   
				.when()
				   .get(Routes.get_url);
				   
				 return res;
		}
	
	//-----------For updating Request----------
	public static Response updateUser(User payload, String userName)
	{
		Response res = 
				given()
				   .contentType(ContentType.JSON)
				   .accept(ContentType.JSON)
				   .body(payload)
				   .pathParam("username",userName)
				   
				.when()
				   .put(Routes.update_url);
				   
				 return res;
		
	}
	
	//------------For Delete Request-----------------------
		public static Response deleteUser(String userName)
		{
			Response res = 
					given()
					   .pathParam("username",userName)
					   
					.when()
					   .delete(Routes.delete_url);
					   
					 return res;
			}

		
}
