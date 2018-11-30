package json.placeholder.demo.builder;

import okhttp3.HttpUrl;

import java.net.URL;
import java.util.Map;

public class UrlJsonPlaceholderBuilder implements UrlBuilder {

    private static final String REST_SERVICE_HOST = "jsonplaceholder.typicode.com";
    private static final String REST_SERVICE_SCHEME = "https";

    @Override
    public URL buildResourceURLWithPathParam(String resource, String pathParam) {
        return new HttpUrl.Builder()
                .scheme(REST_SERVICE_SCHEME)
                .host(REST_SERVICE_HOST)
                .addPathSegments(resource + "/" + pathParam)
                .build().url();

    }

    @Override
    public URL buildResourceURLWithQueryParams(String resource, Map<String, String> queryParams) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme(REST_SERVICE_SCHEME)
                .host(REST_SERVICE_HOST)
                .addPathSegments(resource);

        for (Map.Entry<String, String> queryParam : queryParams.entrySet()) {
            urlBuilder = urlBuilder.addQueryParameter(queryParam.getKey(), queryParam.getValue());
        }

        return urlBuilder.build().url();
    }


}
