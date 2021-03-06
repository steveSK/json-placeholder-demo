package json.placeholder.demo.serialization;

import json.placeholder.demo.entity.Post;
import json.placeholder.demo.entity.User;

import java.io.IOException;
import java.util.List;

public interface JsonDeserializer {

    User toUser(String jsonUser) throws IOException;

    List<Post> toPosts(String jsonPost) throws IOException;
}
