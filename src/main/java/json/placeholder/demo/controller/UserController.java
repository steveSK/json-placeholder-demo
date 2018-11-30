package json.placeholder.demo.controller;


import json.placeholder.demo.entity.User;

import java.io.IOException;
import java.util.Optional;

public interface UserController {

    Optional<User> getUserByIdWithPosts(Integer userId) throws IOException;
}
