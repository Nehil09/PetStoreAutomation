package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	
	public static Response CreateUser(User payload)
	{
		
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
	return response;
		
		
	}
	
	public static Response ReadUser(String userName)
	{
		
	Response response=given()
			      
			.pathParam("username", userName)
		 
		
		.when()
		.post(Routes.get_url);
	
	return response;
}
	

public static Response UpdateUser(String userName,User payload)
{
	
Response response=given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(payload)
	.pathParam("username", userName)
	.when()
	.put(Routes.update_url);
return response;
	
	
}

public static Response DeleteUser(String userName)
{
	
Response response=given()
                  .pathParam("username", userName)
	
	
	.when()
	.post(Routes.delete_url );

return response;
}

}