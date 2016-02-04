package test.weixin.message;

import com.momix.sdk.net.http.api.SdkHttp;
import com.momix.sdk.net.http.api.impl.SdkHttpURLConnection;
import com.momix.sdk.net.http.bean.HttpRequestParams;
import com.momix.sdk.net.http.bean.HttpResponseParam;

import java.io.IOException;

/**
 * Created by rono on 2015/12/11.
 */
public class HttpTest {
    public static void main(String[] args) {
        SdkHttp http = new SdkHttpURLConnection();
        HttpRequestParams req = new HttpRequestParams();
        req.setUri("http://sms.api.ums86.com:8899/sms/Api/Send.do");
        try {
            HttpResponseParam res =  http.post(req);
            System.out.println(res.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
