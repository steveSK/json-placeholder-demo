package json.service.demo.concurrent;

import json.service.demo.entity.User;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ConcurrentUserDispatcher {

    CompletableFuture<Optional<User>> getUserByIdWithPosts(Integer userId);

}
