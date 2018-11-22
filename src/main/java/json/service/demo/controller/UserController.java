package json.service.demo.controller;

import json.service.demo.entity.Post;
import json.service.demo.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserController {

    Optional<User> getUserByIdWithPosts(Integer userId) throws IOException;
    List<Post> getPostsByUserId(Integer userId) throws IOException;



}
