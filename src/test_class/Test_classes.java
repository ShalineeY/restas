package test_class;



import common_method.Common_method;
import common_method.Utility_common_mehtod_;
import io.restassured.path.json.JsonPath;
import request_repository.requrest_repository;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;


public class Test_classes {
	@Test

	public static void orchestractor() throws IOException
	{
		String baseURI=requrest_repository.baseURI();
		String resource=requrest_repository.post_resource();
		String req_body=requrest_repository.post_request_body();
		String responsebody="";
		int statuscode=0;
		
		for (int i=0;i<5;i++)
		{
			 statuscode=Common_method.post_status_code_extractor(baseURI, resource, req_body);
			if (statuscode == 201)
			{
				responsebody=Common_method.post_response_body_extractor(baseURI, resource, req_body);
				responsebodyValidator(responsebody);
				break;
			}
			else
			{
				System.out.println("incorrect status code is getting hence retry "+ i);
			}
						
		}
		
		Assert.assertEquals(statuscode, 201);
		Utility_common_mehtod_.evidencefilecreator("post_tc_validation", responsebody, req_body);
		
		
		
		
		
	}

public static void responsebodyValidator(String responsebody) {
		
		// TODO Auto-generated method stub
		
		//extract responsebody
		JsonPath res_body=new JsonPath(responsebody);
		
		//extract responsebody parameter
		
		String res_name=res_body.getString("name");
		String res_job=res_body.getString("job");
		String res_id=res_body.getString("id");
		String res_create=res_body.getString("createdAt");
		String date=res_create.substring(0,10);
		String newdate=LocalDate.now().toString();
		
		//validate response body
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
		Assert.assertNotEquals(res_id,"not null");
		//Assert.assertEquals(date, newdate);
		
		
		System.out.println("name : "+ res_name + "\nid : "+ res_id + "\njob : " + res_job + "\ndate : " + newdate);
		
		
		
	}


}
