package json.placeholder.demo.builder;

import java.net.URL;
import java.util.Map;

public interface UrlBuilder {

    URL buildResourceURLWithPathParam(String resource, String pathParam);

    URL buildResourceURLWithQueryParams(String resource, Map<String, String> queryParams);

}
