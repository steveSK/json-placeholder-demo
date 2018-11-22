package json.service.demo.serialization;

import json.service.demo.entity.Post;
import json.service.demo.entity.User;

import java.io.IOException;
import java.util.List;

public interface JsonSerializer {

    String fromUser(User user) throws IOException;
    String fromPosts(List<Post> posts) throws IOException;

}
