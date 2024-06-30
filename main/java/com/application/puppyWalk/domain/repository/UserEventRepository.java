package com.application.puppyWalk.domain.repository;

import com.application.puppyWalk.domain.Event;
import com.application.puppyWalk.domain.UserEvent;
import com.application.puppyWalk.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserEventRepository {

    boolean existsByUserAndEvent(final long userId, final long eventId);

    UserEvent saveUserEvent(User user, Event event, LocalDateTime appliedDate);

    List<UserEvent> getAppliedEventsByUser(final long userId, final LocalDateTime now);

    List<UserEvent> getAppliedEvents(final User user, final LocalDateTime now);


    /*
    * 파라미터에 final사용 : 파라미터가 메서드 내부에서 변경되지 않음을 보장함
    * */
}
