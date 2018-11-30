package json.placeholder.demo.controller;

import json.placeholder.demo.builder.UrlJsonPlaceholderBuilder;
import json.placeholder.demo.entity.User;
import json.placeholder.demo.serialization.JacksonJsonDeserializer;

import java.io.IOException;
import java.util.Optional;

public class UserControllerImpl implements UserController {

    private static final String USER_RESOURCE = "users";
    private static final String POST_RESOURCE = "posts";
    private static final String USER_ID_PARAM = "userId";

    private final UserRestJsonController restJsonController = new UserRestJsonControllerImpl(new OkHttpRestController(),new JacksonJsonDeserializer(),new UrlJsonPlaceholderBuilder());


    @Override
    public Optional<User> getUserByIdWithPosts(Integer userId) throws IOException {
        validateUserId(userId);

        User user = restJsonController.getUserById(userId);
        if(ifUserReturnedEmpty(user)){
            return Optional.empty();
        }
        user.setPosts(restJsonController.getPostsByUserId(userId));
        return Optional.of(user);
    }



    private void validateUserId(Integer userId){
        if(userId <= 0){
            throw  new IllegalArgumentException("User ID have to be higher than 0");
        }
        if(userId == null){
            throw  new IllegalArgumentException("User ID can not be null");
        }
    }

    private boolean ifUserReturnedEmpty(User user){
        return user.getName() == null && user.getUserName() == null && user.getEmail() == null;
    }
}
