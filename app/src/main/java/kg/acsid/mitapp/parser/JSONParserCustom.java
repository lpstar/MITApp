package kg.acsid.mitapp.parser;

import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Админ on 08.06.2017.
 */


public class JSONParserCustom {
    // Fields
    static InputStream is = null;
    static JSONObject jsonObject = null;
    static  String bufferedReaderString = "";
    String url;
    String method;
    List<NameValuePair> params;
    boolean flag;
//Constructors
    public JSONParserCustom(String url, String method, List<NameValuePair> params) {
        this.url = url;
        this.method = method;
        this.params = params;
    }


    public JSONParserCustom() {
    }

    // Methods
    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {

        // Создаем HTTP запрос
        try {

            // проверяем метод HTTP запроса
            if(method == "POST"){

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params,"UTF-8");
                httpPost.setEntity(urlEncodedFormEntity);
                HttpContext httpContext = new BasicHttpContext();
                HttpResponse httpResponse = httpClient.execute(httpPost,httpContext);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else if(method == "GET"){
                HttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            bufferedReaderString = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // пытаемся распарсить строку в JSON объект
        try {
            jsonObject = new JSONObject(bufferedReaderString);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // возвращаем JSON строку
        return jsonObject;

    }
    public  boolean postDataToServer( String url, String method, List<NameValuePair> params){
        // Создаем HTTP запрос
        try {

            // проверяем метод HTTP запроса
            if(method == "POST"){

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params,"UTF-8");
                httpPost.setEntity(urlEncodedFormEntity);
                HttpContext httpContext = new BasicHttpContext();
                HttpResponse httpResponse = httpClient.execute(httpPost,httpContext);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                if(is != null){
                    return true;
                }

            }else if(method == "GET"){
                HttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                if(is != null){
                    return true;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Getters and Setters
    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<NameValuePair> getParams() {
        return params;
    }

    public void setParams(List<NameValuePair> params) {
        this.params = params;
    }
}

