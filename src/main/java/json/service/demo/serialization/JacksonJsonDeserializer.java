package json.service.demo.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.service.demo.entity.Post;
import json.service.demo.entity.User;

import java.io.IOException;
import java.util.List;

public class JacksonJsonDeserializer implements JsonDeserializer {

    public User toUser(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonString,User.class);
        return objectMapper.readValue(jsonString,User.class);
    }

    @Override
    public List<Post> toPosts(String jsonPost) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return List.of(objectMapper.readValue(jsonPost,Post[].class));
    }


}
