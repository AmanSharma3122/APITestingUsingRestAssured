import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Json2Java {

    ApiResponse ApiResp= new ApiResponse();
    APIInput apiInput = new APIInput();
    ApiResponse jsResp2java(String fileName) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        return ApiResp = obj.readValue(new File(System.getProperty("user.dir")+"\\jsonFiles\\"+fileName), ApiResponse.class);
    }

    APIInput jsInp2Java(String fileName) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        return apiInput= obj.readValue(new File(System.getProperty("user.dir")+"\\jsonFiles\\"+fileName), APIInput.class);
    }
}
