package learn_to_code.java_se_api.net.URL;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLExample {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://google.com");
        System.out.println(url.getHost());

        URLConnection urlConnection = url.openConnection();

        System.out.println("URL methods:");
        for (String header : urlConnection.getHeaderFields().keySet()) {
            System.out.printf("header: %20s = %s\n", header, urlConnection.getHeaderFields().get(header));
        }

        System.out.println("HTTP url methods:");
        HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
        System.out.println("Response code: " + httpUrlConnection.getResponseCode());
        System.out.println("Response message " + httpUrlConnection.getResponseMessage());
    }
}


