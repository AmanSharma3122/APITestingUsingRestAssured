import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Json2Java {

     
     = new APIInput();
    ApiResponse jsResp2java(String fileName) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ApiResponse ApiResp = obj.readValue(new File(System.getProperty("user.dir")+"\\jsonFiles\\"+fileName), ApiResponse.class);
        return ApiResp;   
    }

    APIInput jsInp2Java(String fileName) throws IOException {
        ObjectMapper obj = new ObjectMapper();
         obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        APIInput apiInput= obj.readValue(new File(System.getProperty("user.dir")+"\\jsonFiles\\"+fileName), APIInput.class);
       return apiInput;
    }
}
