package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
    public void setupData()
	{
		faker =new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		userpayload.setUsername(faker.name().username());
		
		}
@Test(priority=1)
	public void testPostUser()
	{
		Response response= UserEndPoints.CreateUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		
		
		Response response=UserEndPoints.ReadUser(this.userpayload.getUsername());
		response.then().log().all();
		//Assert.assertEquals(response.getStatusCode(),200);
		
		System.out.println(this.userpayload.getUsername());
	}
 @Test(priority=3)
	public void testUpdateUserByName()
	{
		
		//update data using payload
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.UpdateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().body();
				
		Assert.assertEquals(response.getStatusCode(),200);
		
		
		Response responseAfterUpdate=UserEndPoints.ReadUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		
	}
}




