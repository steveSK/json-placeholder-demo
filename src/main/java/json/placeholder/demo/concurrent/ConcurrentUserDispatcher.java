package json.placeholder.demo.concurrent;

import json.placeholder.demo.entity.User;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ConcurrentUserDispatcher {

    CompletableFuture<Optional<User>> getUserByIdWithPosts(Integer userId);

}
