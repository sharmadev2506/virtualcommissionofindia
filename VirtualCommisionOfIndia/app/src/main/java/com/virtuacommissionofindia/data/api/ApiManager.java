package com.virtuacommissionofindia.data.api;

import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiManager {

    private static final ApiManager instance = new ApiManager();
    private ApiInterface authenticatedApiClient;
    private OkHttpClient.Builder httpClient;

    private ApiManager() {
        httpClient = getHttpClient();
        authenticatedApiClient = getAuthenticatedRetrofitService();
    }

    public static ApiManager getInstance() {
        return instance;
    }

    private static ApiInterface getRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiInterface.ENDPOINT)
                .build();

        return retrofit.create(ApiInterface.class);
    }

    private ApiInterface getAuthenticatedRetrofitService() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(ApiInterface.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.client(httpClient.build()).build();
        return retrofit.create(ApiInterface.class);
    }

    /**
     * Method to create {@link OkHttpClient} builder by adding required headers in the {@link Request}
     *
     * @return OkHttpClient object
     */
    private OkHttpClient.Builder getHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder;
                        requestBuilder = original.newBuilder()
                                .header("Access-Token", "QVFWV1ZfUjdKeXF3dHVaQVlhckhYSExORGp5QnFSRTRBTmdQVGw3RkZ0dzdBYjNfM09wNy1GazlmYk1rU3Fqc055NUlwdGx5X0VrVzhDWF9saGQwWmJaclJmSWJWWnY3MTRvR1dHelp0S3hfZVo2aUhhQkRoM1ZRNTlYUTQ0dWd5N0NxeU1RY3RqRFQ5NVdxN0RZVi1WRnN0bGtkc2pVdWJ0eG1QeHF1TzNUUzNPWGQ0Wm0yT3RNeWtmTjRDMXJQeWhvYk9lREc4dzF2SVFoRzFmRUw4ZEJhbmY2MWZvYi1LTlhIbXI0Q3VYQzloTzN5N25WTG85TWhoT2lVT0ZCSGxyWWtQRWwwaGRDN0xUMmRFTGh6V0lfbUdiempwMzk5a1BTcTBBM0hIYzZIQTdWeW9KMG5NdEEzRlMyZlltSU03bFR0elVxREItbVhwMzN5d1ZrTFJWcnRxVEpPN3clVVNFUiUzMjAw")
                                .header("Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                                .header("Cache-Control", "no-cache")
                                .header("no-cache", "a656d67c-ed12-4243-9781-2b63fce1f0bf")
                                .header("User-ID", "3200")
                                .header("User-IP", "106.51.66.2")
                                .method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        Response response = chain.proceed(request);
                        Log.e("Response =", response.message());
                        return response;
                    }
                })
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS);
    }



}
