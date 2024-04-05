import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.*;

public class ApiTesting {
    Common com = new Common();
    ApiResponse apiRespJava = new ApiResponse();
    Json2Java json2Java2jv = new Json2Java();
    JavaToJason javaToJason2ja = new JavaToJason();
    Response runAPI() throws SQLException, IOException {

        //Validate id API is working as expected

        RestAssured.baseURI = com.getProperty("BaseURI");
        // given
        Response response = given().log().all().header("Content-Type","application/json").body(javaToJason2ja.getDbDataAsSeparateJson().get(1))
                .when().post(com.getProperty("resourcePost"))
                .then().log().all().extract().response();

        System.out.println(response.asString());
        ApiResponseValidation(response);
        return response;
        }
        void ApiResponseValidation(Response response) throws IOException {
            Assert.assertEquals(response.statusCode(),200);
            String resp = response.asString();
            javaToJason2ja.jasonString2JsonFile(resp,"response.json");
            json2Java2jv.jsResp2java("response.json");

            Assert.assertEquals(apiRespJava.getMsg(),"Book Already Exists");
        }


}
