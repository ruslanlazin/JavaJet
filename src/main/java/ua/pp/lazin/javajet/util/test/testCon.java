package ua.pp.lazin.javajet.util.test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ruslan Lazin
 */
public class testCon {
    public static void main(String[] args) throws Exception {

        String Surl = "https://maps.googleapis.com/maps/api/timezone/json?";

        Map<String,Object> params = new LinkedHashMap<>();
        params.put("location", "38.908133,-77.047119");
        params.put("timestamp", "1483549213");
        params.put("key", "AIzaSyCfRwmQzonPq3P7o9WN_X8u0RyksS8M98o");

        System.out.println(new Date().getTime());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        System.out.println(postData);
        Surl+=postData.toString();
        URL url = new URL(Surl);
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);


        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
    }
}


