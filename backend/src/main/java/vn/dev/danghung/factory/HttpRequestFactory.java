package vn.dev.danghung.factory;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class HttpRequestFactory {

    private static HttpRequestFactory ourInstance = new HttpRequestFactory();

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(200, TimeUnit.MILLISECONDS)
            .readTimeout(1000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(false)
            .connectionPool(
                    new ConnectionPool(20,
                            5L, TimeUnit.MINUTES))
            .build();

    public static HttpRequestFactory getInstance() {
        return ourInstance;
    }

    private HttpRequestFactory() {
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }
}