package com.application.puppyWalk.domain.repository;

import org.apache.catalina.User;

public interface UserRepository {
    User getUserById(long id);
}
