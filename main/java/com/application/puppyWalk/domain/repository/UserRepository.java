package com.application.puppyWalk.domain.repository;

import com.application.puppyWalk.domain.User;

public interface UserRepository {
    User getUserById(long id);
}
