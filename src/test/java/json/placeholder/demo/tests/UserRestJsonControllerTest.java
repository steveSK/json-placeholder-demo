package json.placeholder.demo.tests;


import json.placeholder.demo.builder.UrlJsonPlaceholderBuilder;
import json.placeholder.demo.controller.OkHttpRestController;
import json.placeholder.demo.controller.UserController;
import json.placeholder.demo.controller.UserRestJsonController;
import json.placeholder.demo.controller.UserRestJsonControllerImpl;
import json.placeholder.demo.entity.Post;
import json.placeholder.demo.entity.User;
import json.placeholder.demo.serialization.JacksonJsonDeserializer;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserRestJsonControllerTest {


    private final static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    private static final int validId = 2;
    private static final int notExistingId = 9999;


    @Test
    public void testGetUserByUserIdWithValidUserId() throws IOException {

        UserRestJsonController userController = buildUserRestJsonController();

        User user = userController.getUserById(validId);

        Assert.assertTrue(user.getName().equals("Ervin Howell"));
        Assert.assertTrue(user.getUserName().equals("Antonette"));
        Assert.assertTrue(user.getEmail().equals("Shanna@melissa.tv"));
    }


    @Test
    public void testGetPostsByUserIdWithValidUserId() throws IOException {

        UserRestJsonController userController = buildUserRestJsonController();

        List<Post> posts = userController.getPostsByUserId(validId);
        Assert.assertTrue(posts.size() == 10);

    }

    @Test
    public void testGetPostsByUserIdWithnotExistingUserId() throws IOException {
        UserRestJsonController userController = buildUserRestJsonController();

        List<Post> posts = userController.getPostsByUserId(notExistingId);
        Assert.assertTrue(posts.isEmpty());
    }

    private UserRestJsonController buildUserRestJsonController(){
        return new UserRestJsonControllerImpl(
                new OkHttpRestController(),
                new JacksonJsonDeserializer(),
                new UrlJsonPlaceholderBuilder());
    }
}
