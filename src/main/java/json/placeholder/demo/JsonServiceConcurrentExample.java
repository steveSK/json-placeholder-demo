package json.placeholder.demo;

import json.placeholder.demo.concurrent.ConcurrentUserDispatcher;
import json.placeholder.demo.concurrent.ConcurrentUserDispatcherImpl;
import json.placeholder.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class JsonServiceConcurrentExample {


    private final static Integer userId = 1;
    private final static ConcurrentUserDispatcher concurrentUserDispatcher = new ConcurrentUserDispatcherImpl(8);
    private final static List<CompletableFuture<Optional<User>>> allTasks = new ArrayList<>();


    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            CompletableFuture<Optional<User>> asyncUser = concurrentUserDispatcher.getUserByIdWithPosts(userId);
            allTasks.add(asyncUser);
        }

        for (CompletableFuture<Optional<User>> task : allTasks) {
            task.whenComplete((result, exception) -> {
                if (exception == null) {
                    result.ifPresent(x -> System.out.println(x));
                } else {
                    task.completeExceptionally(exception);
                    System.out.println(exception.getMessage());
                }
            });
        }


    }

}
