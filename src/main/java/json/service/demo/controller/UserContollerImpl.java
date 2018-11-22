package json.service.demo.controller;

import json.service.demo.builder.UrlBuilder;
import json.service.demo.entity.Post;
import json.service.demo.entity.User;
import json.service.demo.serialization.JsonDeserializer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserContollerImpl implements UserController {

    private static final String USER_RESOURCE = "users";
    private static final String POST_RESOURCE = "posts";
    private static final String USER_ID_PARAM = "userId";

    private final RestController restController;
    private final JsonDeserializer jsonSerializer;
    private final UrlBuilder urlBuilder;


    public UserContollerImpl(RestController restController, JsonDeserializer jsonSerializer, UrlBuilder urlBuilder) {
        this.restController = restController;
        this.jsonSerializer = jsonSerializer;
        this.urlBuilder = urlBuilder;
    }

    @Override
    public Optional<User> getUserByIdWithPosts(Integer userId) throws IOException {
        validateUserId(userId);

        URL userUrl = urlBuilder.buildResourceURLWithPathParam(USER_RESOURCE,String.valueOf(userId));
        String userAsString = restController.getResource(userUrl);
        User user = jsonSerializer.toUser(userAsString);
        if(user.getName() == null){
            return Optional.empty();
        }
        user.setPosts(getPostsByUserId(userId));

        return Optional.of(user);
    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) throws IOException {
        validateUserId(userId);

        Map<String,String> queryParams =  Map.of(USER_ID_PARAM,String.valueOf(userId));

        URL postsUrl = urlBuilder.buildResourceURLWithQueryParams(POST_RESOURCE,queryParams);
        String postAsString = restController.getResource(postsUrl);

        return jsonSerializer.toPosts(postAsString);

    }


    private void validateUserId(Integer userId){
        if(userId <= 0){
            throw  new IllegalArgumentException("User ID have to be higher than 0");
        }
        if(userId == null){
            throw  new IllegalArgumentException("User ID can not be null");
        }
    }
}
