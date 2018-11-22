package json.service.demo.serialization;

import json.service.demo.entity.Post;
import json.service.demo.entity.User;

import java.io.IOException;
import java.util.List;

public interface JsonDeserializer {

    User toUser(String jsonUser) throws IOException;
    List<Post> toPosts(String jsonPost) throws IOException;
}
