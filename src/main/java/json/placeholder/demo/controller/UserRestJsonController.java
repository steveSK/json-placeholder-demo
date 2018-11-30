package json.placeholder.demo.controller;

import json.placeholder.demo.entity.Post;
import json.placeholder.demo.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserRestJsonController {

    User getUserById(Integer userId) throws IOException;
    List<Post> getPostsByUserId(Integer userId) throws IOException;
}
