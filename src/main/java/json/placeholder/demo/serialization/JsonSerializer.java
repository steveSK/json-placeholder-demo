package json.placeholder.demo.serialization;

import json.placeholder.demo.entity.Post;
import json.placeholder.demo.entity.User;

import java.io.IOException;
import java.util.List;

public interface JsonSerializer {

    String fromUser(User user) throws IOException;

    String fromPosts(List<Post> posts) throws IOException;

}
