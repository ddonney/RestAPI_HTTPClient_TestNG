package tests;

import Base.TestBase;
import Client.RestClient;
import Util.Testutil;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;


public class getAPItest extends TestBase {

    TestBase testBase;
    String URL;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;
    public getAPItest() throws IOException {
    }

    @BeforeMethod
    public void setUp() throws IOException {
        testBase=new TestBase();
        URL=prop.getProperty("URL");
        apiUrl=prop.getProperty("serviceURL");
        url=URL+apiUrl;
    }

    @Test(priority=1)
    public void getTest() throws IOException {
        restClient=new RestClient();
        httpResponse= restClient.get(url);
        int statusCode=httpResponse.getStatusLine().getStatusCode();         //getting status code from response
        System.out.println("sttauscode--->"+statusCode);
        Assert.assertEquals(statusCode,Response_statusCode_200,"status code is not 200");
        System.out.println("Assertion passed");

        String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");  //getting json response string
        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("response json from API--->"+responseJson);
        int perpageval= Integer.parseInt(Testutil.getValueByJPath(responseJson,"per_page"));
        Assert.assertEquals(perpageval,6);

        String id=Testutil.getValueByJPath(responseJson,"/data[0]/id");
        String firstname=Testutil.getValueByJPath(responseJson,"/data[0]/first_name");
        String lastname=Testutil.getValueByJPath(responseJson,"/data[0]/last_name");
        System.out.println(""+id);
        System.out.println(""+firstname);
        System.out.println(""+lastname);


        Header[] headerArray=httpResponse.getAllHeaders();   //all headers
        HashMap <String,String>allHeaders=new HashMap<String,String>();
        for(Header header:headerArray){
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array--->"+allHeaders);
    }

    @Test(priority=2)
    public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
        restClient = new RestClient();

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        httpResponse = restClient.get(url,headerMap);

        //a. Status Code:
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code--->"+ statusCode);

        Assert.assertEquals(statusCode, Response_statusCode_200, "Status code is not 200");

        //b. Json String:
        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON from API---> "+ responseJson);

        //single value assertion:
        //per_page:
        int perpageval= Integer.parseInt(Testutil.getValueByJPath(responseJson,"per_page"));
        Assert.assertEquals(perpageval,6);

        //total:
        int totval= Integer.parseInt(Testutil.getValueByJPath(responseJson,"total"));
        Assert.assertEquals(totval,12);

        //get the value from JSON ARRAY:
        String lastName = Testutil.getValueByJPath(responseJson, "/data[0]/last_name");
        String id = Testutil.getValueByJPath(responseJson, "/data[0]/id");
        String avatar = Testutil.getValueByJPath(responseJson, "/data[0]/avatar");
        String firstName = Testutil.getValueByJPath(responseJson, "/data[0]/first_name");

        System.out.println(lastName);
        System.out.println(id);
        System.out.println(avatar);
        System.out.println(firstName);


        //c. All Headers
        Header[] headersArray =  httpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for(Header header : headersArray){
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array-->"+allHeaders);



    }
}
