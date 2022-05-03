package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public Properties prop;
    public int Response_statusCode_200=200;
    public int Response_statusCode_500=500;
    public int Response_statusCode_400=400;
    public int Response_statusCode_401=401;
    public int Response_statusCode_201=201;

    public TestBase() throws IOException {
        try{
            prop=new Properties();
            FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Configuration/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
