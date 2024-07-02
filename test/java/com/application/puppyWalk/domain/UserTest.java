package com.application.puppyWalk.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 유저id는_양수여야한다() {
        final long userId1 = 0L;
        final long userId2 = -1L;
        final String userName = "test";

        Assertions.assertThatThrownBy(() ->
            new User(userId1, userName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("userId는 양수여야 합니다.");

        Assertions.assertThatThrownBy(() -> new User(userId2, userName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("userId는 양수여야 합니다.");

    }

    @Test
    void 유저이름은_존재해야한다() {
        final long userId = 1L;
        final String userName1 = null;

        Assertions.assertThatThrownBy(() -> new User(userId, userName1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("userName은 필수 값입니다.");
    }
}
