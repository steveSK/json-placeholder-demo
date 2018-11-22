package json.placeholder.demo.tests;

import json.placeholder.demo.builder.UrlJsonPlaceholderBuilder;
import json.placeholder.demo.controller.UserContollerImpl;
import json.placeholder.demo.controller.UserController;
import json.placeholder.demo.entity.Post;
import json.placeholder.demo.serialization.JacksonJsonDeserializer;
import json.placeholder.demo.controller.OkHttpRestController;
import json.placeholder.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserControllerTest {

    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    private static final int validId = 2;
    private static final int notValidId = 0;
    private static final int notExistingId = 9999;

    @Test
    public void testGetPostsByUserIdWithValidUserId() throws IOException {

        UserController userContoller = buildUserController();

        List<Post> posts = userContoller.getPostsByUserId(validId);
        Assert.assertTrue(posts.size() == 10);
    }


    @Test
    public void testGetUserByIdWithValidUserId() throws IOException {

        UserController userContoller = buildUserController();

        Optional<User> userOp = userContoller.getUserByIdWithPosts(validId);
        Assert.assertTrue(userOp.isPresent());

        User user = userOp.get();


        Assert.assertTrue(user.getName().equals("Ervin Howell"));
        Assert.assertTrue(user.getUserName().equals("Antonette"));
        Assert.assertTrue(user.getEmail().equals("Shanna@melissa.tv"));
        Assert.assertTrue(user.getPosts().size() == 10);

    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByIdWithNotValidUserId() throws IOException {

        UserController userContoller = buildUserController();
        userContoller.getUserByIdWithPosts(notValidId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPostsByUserIdWithNotValidUserId() throws IOException {

        UserController userContoller = buildUserController();
        userContoller.getPostsByUserId(notValidId);
    }

    @Test
    public void testGetUserByIdWithNotExistingUserId() throws IOException {
        UserController userContoller = buildUserController();

        Optional<User> user = userContoller.getUserByIdWithPosts(notExistingId);
        Assert.assertTrue(!user.isPresent());
    }

    @Test
    public void testGetPostsByUserIdWithnotExistingUserId() throws IOException {
        UserController userContoller = buildUserController();

        List<Post> posts = userContoller.getPostsByUserId(notExistingId);
        Assert.assertTrue(posts.isEmpty());
    }





    private UserContollerImpl buildUserController(){
        return new UserContollerImpl(new OkHttpRestController(),new JacksonJsonDeserializer(),new UrlJsonPlaceholderBuilder());
    }


}
