package com.tech.sincon.xless.api;

import com.tech.sincon.xless.models.Transaction;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import com.tech.sincon.xless.models.Person;

/**
 * Created by getcore03 on 7/24/2016.
 */
public class EndpointApi {

    private final Retrofit retrofit;

    private EndpointApi(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    private interface AccountsApi {
        @POST("/new")
        @Headers({
                "Content-Type: application/json"
        })
        Call<String> createAccount(@Header("Authorization")String token, @Body Person person);
    }

    private interface TransactionsApi {
        Call<String> transfer(@Header("Authorization") String token, @Body Transaction transaction);
    }

    public Call<String> createAccount(String token, Person person) {
        return retrofit.create(AccountsApi.class).createAccount(token, person);
    }

    Transaction transaction = new Transaction();


    public Call<String> transfer(String token, Transaction transaction) {
        return retrofit.create(EndpointApi.class).transfer(token, transaction);
    }

    public static class Builder {
        private final EndpointApi endpointApi;

        private Builder(Retrofit retrofit) {
            endpointApi = new EndpointApi(retrofit);
        }

        public static Builder withRetrofit(Retrofit retrofit) {
            retrofit = retrofit;
            return new Builder(retrofit);
        }

        public EndpointApi build() {
            return endpointApi;
        }
    }

}
