package json.service.demo.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class OkHttpRestController implements RestController {

    private final OkHttpClient client = new OkHttpClient();


    @Override
    public String getResource(URL url) throws IOException {

        Request userRequest = new Request.Builder()
                .url(url)
                .get().build();

        Response response = client.newCall(userRequest).execute();

        return response.body().string();
    }
}
