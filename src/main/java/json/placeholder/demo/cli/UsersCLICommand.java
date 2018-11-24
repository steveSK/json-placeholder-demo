package json.placeholder.demo.cli;

import json.placeholder.demo.builder.UrlJsonPlaceholderBuilder;
import json.placeholder.demo.controller.UserContollerImpl;
import json.placeholder.demo.controller.UserController;
import json.placeholder.demo.entity.User;
import json.placeholder.demo.serialization.JacksonJsonDeserializer;
import json.placeholder.demo.serialization.JacksonJsonSerializer;
import json.placeholder.demo.serialization.JsonSerializer;
import json.placeholder.demo.controller.OkHttpRestController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class UsersCLICommand implements CLICommand {

    private static Logger logger = LoggerFactory.getLogger(CLICommand.class);

    private final JsonSerializer jsonSerializer = new JacksonJsonSerializer();
    private final UserController userController = new UserContollerImpl(
            new OkHttpRestController(),
            new JacksonJsonDeserializer(),
            new UrlJsonPlaceholderBuilder());

    @Override
    public void execute(String[] args) {
        boolean isValid = validateUsersCommandArguments(args);

        if (isValid) {
            try {
                Integer userId = Integer.valueOf(args[0]);

                Optional<User> userOp = userController.getUserByIdWithPosts(userId);
                if (userOp.isPresent()) {
                    System.out.println(jsonSerializer.fromUser(userOp.get()));
                } else {
                    System.out.println("The user with id: " + userId + " does not exist.");
                }

            } catch (IOException e) {
                System.out.println("There were some error during processing your request!");
                logger.error(e.getMessage(), e);
            }
        }
    }


    public static boolean validateUsersCommandArguments(String[] args) {

        if (args.length > 1) {
            System.out.println("Too Many arguments, you need to specify only one argument: the userID.");
            return false;
        }

        if (args.length == 0) {
            System.out.println("UserID is missing, Yoo need to specify userId.");
            return false;
        }

        String userID = args[0];

        if (!StringUtils.isNumeric(userID)) {
            System.out.println("UserID has to be a number.");
            return false;
        }

        try {
            if (Integer.valueOf(userID) <= 0) {
                System.out.println("UserID has to be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("The user Id is too big for an integer value!");
            return false;
        }

        return true;

    }
}
