package json.placeholder.demo.tests;

import json.placeholder.demo.entity.User;
import json.placeholder.demo.serialization.JacksonJsonDeserializer;
import json.placeholder.demo.serialization.JacksonJsonSerializer;
import json.placeholder.demo.serialization.JsonDeserializer;
import json.placeholder.demo.serialization.JsonSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JsonUserSerializationTest {


    @Test
    public void testSerializeUser() throws IOException {
        JsonSerializer jsonSerializer = new JacksonJsonSerializer();
        User user = getUser();
        String userAsString = jsonSerializer.fromUser(user);
        Assert.assertTrue(userAsString.contains("John Smith"));
        Assert.assertTrue(userAsString.contains("Johny"));
        Assert.assertTrue(userAsString.contains("abc@email.com"));
    }


    @Test
    public void testDeSerializeUser() throws IOException {
        JsonDeserializer jsonSerializer = new JacksonJsonDeserializer();
        User user = jsonSerializer.toUser(getJSONUser());

        Assert.assertTrue(user.getName().equals("Leanne Graham"));
        Assert.assertTrue(user.getUserName().equals("Bret"));
        Assert.assertTrue(user.getEmail().equals("Sincere@april.biz"));
    }


    private User getUser() {
        User user = new User();
        user.setName("John Smith");
        user.setUserName("Johny");
        user.setEmail("abc@email.com");

        return user;
    }

    private String getJSONUser() {
        return "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Leanne Graham\",\n" +
                "    \"username\": \"Bret\",\n" +
                "    \"email\": \"Sincere@april.biz\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Kulas Light\",\n" +
                "      \"suite\": \"Apt. 556\",\n" +
                "      \"city\": \"Gwenborough\",\n" +
                "      \"zipcode\": \"92998-3874\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-37.3159\",\n" +
                "        \"lng\": \"81.1496\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Romaguera-Crona\",\n" +
                "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "      \"bs\": \"harness real-time e-markets\"\n" +
                "    }" +
                "}";
    }

}
