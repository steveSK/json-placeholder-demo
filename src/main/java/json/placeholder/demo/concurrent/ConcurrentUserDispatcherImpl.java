package json.placeholder.demo.concurrent;


import json.placeholder.demo.controller.UserControllerImpl;
import json.placeholder.demo.controller.UserController;
import json.placeholder.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class ConcurrentUserDispatcherImpl implements ConcurrentUserDispatcher {


    private Logger logger = LoggerFactory.getLogger(ConcurrentUserDispatcherImpl.class);

    private final ExecutorService executorService;


    public ConcurrentUserDispatcherImpl(int numberOfParallelTasks){
        executorService =  Executors.newFixedThreadPool(numberOfParallelTasks);
    }

    @Override
    public CompletableFuture<Optional<User>> getUserByIdWithPosts(Integer userId) {
        return CompletableFuture.supplyAsync(new ObtainUserTask(userId),executorService);
    }


    private class ObtainUserTask implements Supplier<Optional<User>> {

        private final UserController userController = new UserControllerImpl();

        private final Integer userId;


        public ObtainUserTask(Integer userId) {
            this.userId = userId;
        }


        @Override
        public Optional<User> get() {
            try {
                return userController.getUserByIdWithPosts(userId);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return Optional.empty();
            }
        }
    }

}
