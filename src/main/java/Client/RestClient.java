package Client;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpget =  new HttpGet(url);   //http get method
            CloseableHttpResponse httpResponse= httpClient.execute(httpget);
            return httpResponse;
    }

    //2. GET Method with Headers:
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url); //http get request

        for(Map.Entry<String,String> entry : headerMap.entrySet()){
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse httpResponse =  httpClient.execute(httpget); //hit the GET URL
        return httpResponse;

    }
}
