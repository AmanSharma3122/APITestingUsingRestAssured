import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TestRunner extends Common {

    JavaToJason j2j = new JavaToJason();
    Json2Java js2ja = new Json2Java();
    ApiTesting apiTest = new ApiTesting();


    @BeforeClass
    void dbconnect() throws SQLException, ClassNotFoundException {
        connectDB();
    }

    @Test
    void Test() throws SQLException, IOException {
        j2j.getDbDataAsSeparateJson();
        ApiResponse resp = new ApiResponse();
        apiTest.runAPI();
        System.out.println(resp.getMsg());
        System.out.println(resp.getID());


    }
    @AfterClass
    void  tearDown() throws SQLException {
        connection.close();
    }
}
