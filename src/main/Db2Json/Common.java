import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Class.forName;

public class Common {

    public static Connection connection = null;
    ArrayList <APIInput> dataList = new ArrayList();
    ApiResponse ApiResp = new ApiResponse();


    public void connectDB() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
       connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/APItesting", "root", "root");
   }

   public ResultSet getSqlData(Connection connection) throws SQLException, IOException {

       // Object of Statement class
       Statement st = connection.createStatement();
       ResultSet rs = st.executeQuery(getProperty("sqlQuery"));
       return rs;
   }
   public ArrayList<APIInput> getSqldataAsJavaObj(ResultSet rs) throws SQLException {

        while(rs.next()){
            APIInput data = new APIInput();
            data.setName(rs.getNString(1));
            data.setIsbn(rs.getNString(2));
            data.setAisle(rs.getNString(3));
            data.setAuthor(rs.getNString(4));
            dataList.add(data);
        }

        return dataList;
    }
//    public void jasonString2JsonFile(String data, String fileName) throws IOException {
//        FileWriter file = new FileWriter(System.getProperty("user.dir")+ "\\jsonFiles\\"+fileName);
//        file.write(data);
//        file.close();
//    }

    String getProperty(String key) throws IOException {
        Properties prop = new Properties();
        FileReader reader = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
        prop.load(reader);
        return prop.getProperty(key);
    }

    ApiResponse jsonResponse2java(String fileName) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        return ApiResp = obj.readValue(new File(System.getProperty("user.dir")+"\\jsonFiles\\"+fileName), ApiResponse.class);
    }


}
