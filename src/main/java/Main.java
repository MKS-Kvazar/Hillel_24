import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Main().task_1();
        new Main().task_2();
        new Main().task_3();
    }

    void task_1() {
        GoogleWebService service = new Retrofit.Builder()
                .baseUrl("https://google.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleWebService.class);
        Response<String> response = null;
        try {
            response = service.home().execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (response != null && response.isSuccessful()) {
            System.out.println(response.body());
        } else if (response != null) {
            try {
                System.out.println(response.errorBody().string());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void task_2() {
        System.out.println("\n\tTask 2");
        GitHubService service = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GitHubService.class);
        Response<String> response = null;
        try {
            response = service.listRepos("MKS-Kvazar").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null && response.isSuccessful()) {
            System.out.println(response.body());
        } else if (response != null) {
            try {
                System.out.println(response.errorBody().string());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void task_3() {
        System.out.println("\n\tTask 3");
        final TinyUrlService service = new Retrofit.Builder()
                .baseUrl("http://tiny-url.info/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TinyUrlService.class);
        Response<TinyUrlResponse> response = null;
        try {
            response = service.random("json", "https://www.google.com.ua/maps/place/Hillel+IT+School/@49.9908513,36.2357012,19.73z/data=!4m5!3m4!1s0x4127a0f197eaf81b:0x7fb35620b3e843c9!8m2!3d49.9910812!4d36.2359428").execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (response != null && response.isSuccessful()) {
            System.out.println(response.body().shortUrl);
        } else if (response != null) {
            try {
                System.out.println(response.errorBody().string());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
