package json.placeholder.demo.tests;

import json.placeholder.demo.controller.UserControllerImpl;
import json.placeholder.demo.controller.UserController;
import json.placeholder.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    private static final int validId = 2;
    private static final int notValidId = 0;
    private static final int notExistingId = 9999;



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

    @Test
    public void testGetUserByIdWithNotExistingUserId() throws IOException {
        UserController userContoller = buildUserController();

        Optional<User> user = userContoller.getUserByIdWithPosts(notExistingId);
        Assert.assertTrue(!user.isPresent());
    }

    private UserControllerImpl buildUserController(){
        return new UserControllerImpl();
    }


}
