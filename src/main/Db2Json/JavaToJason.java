import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JavaToJason extends Common {

    Common db = new Common();
    Gson gson = new Gson();
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    String JsonString;
    ArrayList<APIInput> dataList;

    JSONArray getDbDataAsSeparateJson() throws SQLException, IOException {

        ResultSet dbResult = db.getSqlData(connection);
        dataList = db.getSqldataAsJavaObj(dbResult);
//        ObjectMapper objectMapper = new ObjectMapper();
//        for(int i=0; i<dataList.size();i++){
//            objectMapper.writeValue(new File(System.getProperty("user.dir")+"\\jsonFiles\\"+(i+1)+"_"+fileName+".json"), dataList.get(i));
//        }
        for (int i = 0; i < dataList.size(); i++) {
            JsonString = gson.toJson(dataList.get(i));
            jasonString2JsonFile(JsonString, "InputBody" + (i + 1) + ".json");
            jsonArray.add(JsonString);
        }


        // To generate consolidated JSON file

        jsonObject.put("data", jsonArray);
        String string1 = StringEscapeUtils.unescapeJava(jsonObject.toJSONString());
        String string2 = string1.replace("\"{", "{");
        String unEscapedJson = string2.replace("}\"", "}");
        jasonString2JsonFile(unEscapedJson, "ConsolidatedFile.json");

        return jsonArray;

    }
    public void jasonString2JsonFile(String data, String fileName) throws IOException {
        FileWriter file = new FileWriter(System.getProperty("user.dir")+ "\\jsonFiles\\"+fileName);
        file.write(data);
        file.close();
    }
}




