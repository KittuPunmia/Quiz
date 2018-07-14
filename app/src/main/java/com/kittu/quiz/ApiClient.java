package com.kittu.quiz;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class ApiClient {

    //private static final String Key="97205db52ddf42e9a8324a9648083d28";
    private static final String BASE_URL="http://2a58beba.ngrok.io/quizApp/quizdetails.php/";
    public static Retrofit retrofit=null;
    private static final String ATTEMPT_URL="http://2a58beba.ngrok.io/quizApp/quizAttempted.php/";
    public static QuizService quizService=null;

    public static QuizService getApiClient()
    {
        if(quizService==null)
        {

            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();

            quizService=retrofit.create(QuizService.class);
        }
        return quizService;
    }

    public interface QuizService
    {
        @Headers("Content-Type: application/json")
        @POST(BASE_URL)
        Call <List<List<Quiz>>> getquiz(@Body Map<String, String> fields);

        @Headers("Content-Type: application/json")
        @POST(ATTEMPT_URL)
        Call<List<StudentAttemptedquiz>> get_attempted_quiz(@Body Map<String, String> fields);

    }
}
