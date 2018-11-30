package json.placeholder.demo.controller;

import json.placeholder.demo.builder.UrlBuilder;
import json.placeholder.demo.entity.Post;
import json.placeholder.demo.entity.User;
import json.placeholder.demo.serialization.JsonDeserializer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class UserRestJsonControllerImpl implements UserRestJsonController {

    private static final String USER_RESOURCE = "users";
    private static final String POST_RESOURCE = "posts";
    private static final String USER_ID_PARAM = "userId";

    private final RestController restController;
    private final JsonDeserializer jsonSerializer;
    private final UrlBuilder urlBuilder;


    public UserRestJsonControllerImpl(RestController restController, JsonDeserializer jsonSerializer, UrlBuilder urlBuilder) {
        this.restController = restController;
        this.jsonSerializer = jsonSerializer;
        this.urlBuilder = urlBuilder;
    }



    @Override
    public User getUserById(Integer userId) throws IOException {
        URL userUrl = urlBuilder.buildResourceURLWithPathParam(USER_RESOURCE,String.valueOf(userId));
        String userAsString = restController.getResource(userUrl);
        return jsonSerializer.toUser(userAsString);
    }




    @Override
    public List<Post> getPostsByUserId(Integer userId) throws IOException {
        Map<String,String> queryParams =  Map.of(USER_ID_PARAM,String.valueOf(userId));

        URL postsUrl = urlBuilder.buildResourceURLWithQueryParams(POST_RESOURCE,queryParams);
        String postAsString = restController.getResource(postsUrl);

        return jsonSerializer.toPosts(postAsString);
    }

}
