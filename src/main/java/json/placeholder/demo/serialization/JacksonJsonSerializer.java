package json.placeholder.demo.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.placeholder.demo.entity.Post;
import json.placeholder.demo.entity.User;

import java.io.IOException;
import java.util.List;

public class JacksonJsonSerializer implements JsonSerializer {

    @Override
    public String fromUser(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    @Override
    public String fromPosts(List<Post> posts) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(posts);

    }
}
