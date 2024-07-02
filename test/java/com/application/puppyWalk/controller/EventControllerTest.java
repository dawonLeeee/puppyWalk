package com.application.puppyWalk.controller;

import com.application.puppyWalk.application.EventService;
import com.application.puppyWalk.domain.DateTimeProvider;
import com.application.puppyWalk.domain.Event;
import com.application.puppyWalk.domain.User;
import com.application.puppyWalk.domain.UserEvent;
import com.application.puppyWalk.domain.repository.ApplyHistoryRepository;
import com.application.puppyWalk.domain.repository.EventRepository;
import com.application.puppyWalk.domain.repository.UserEventRepository;
import com.application.puppyWalk.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplyHistoryRepository applyHistoryRepository;

    @Mock
    private UserEventRepository userEventRepository;

    @Mock
    private DateTimeProvider dateTimeProvider;

    @InjectMocks
    private EventService eventService;

    private static final LocalDateTime EVENT_APPLY_DATE = LocalDateTime.of(2024, 4, 20, 13, 20);
    private static final LocalDateTime EVENT_OPEN_DATE = LocalDateTime.of(2024, 4, 23, 13, 20);

    /*
    * 이벤트 신청 성공 테스트
    * */
    @Test
    void applyEvent() {
        // given
        final long userId = 1L;
        final long eventId = 1L;
        final LocalDateTime applyDate = LocalDateTime.of(2024, 4, 20, 13, 20);
        final UserEvent 유저가_이벤트_신청 = new UserEvent(userId, eventId, "테스트유저", applyDate);

        given(userRepository.getUserById(userId))
            .willReturn(new User(userId, "테스트유저"));
        given(eventRepository.getEventById(eventId))
            .willReturn(new Event(eventId, "새 이벤트", EVENT_APPLY_DATE, EVENT_OPEN_DATE, 0));
        given(dateTimeProvider.now())
            .willReturn(applyDate);


        // when
        UserEvent 유저가_신청한_이벤트 = eventService.applyEvent(유저가_이벤트_신청);

        // then
        assertThat(유저가_신청한_이벤트.getEventId()).isEqualTo(eventId);
        assertThat(유저가_신청한_이벤트.getUserId()).isEqualTo(userId);
        assertThat(유저가_신청한_이벤트.getOpenDate()).isEqualTo(applyDate);
        assertThat(유저가_신청한_이벤트.getEventName()).isEqualTo("새 이벤트");
    }

    /*
    * 이벤트 신청자가 30명 초과시 실패테스트
    * */
    @Test
    void 이벤트_신청자가_30명초과시_실패() {
        // given & when
        final long userId = 1L;
        final long eventId = 1L;
        final LocalDateTime applyDate = LocalDateTime.of(2024, 4, 20, 13, 20);
        final UserEvent 유저가_이벤트_신청 = new UserEvent(userId, eventId, "테스트유저", applyDate);

        given(userRepository.getUserById(userId))
            .willReturn(new User(userId, "테스트유저"));
        given(eventRepository.getEventById(eventId))
            .willReturn(new Event(eventId, "새 이벤트", EVENT_APPLY_DATE, EVENT_OPEN_DATE, 30));
        given(dateTimeProvider.now())
            .willReturn(applyDate);

        // then
        assertThatThrownBy(() -> eventService.applyEvent(유저가_이벤트_신청))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이벤트 신청자가 30명을 초과했습니다.");

    }


    /*
    * 에빈트 신청시 신청내역 기록 테스트
    * */
    @Test
    void 이벤트_신청시_내역_기록() {
        // given
        final long userId = 1L;
        final long eventId = 1L;
        final LocalDateTime applyDate = LocalDateTime.of(2024, 4, 20, 13, 20);
        final UserEvent 유저가_이벤트_신청 = new UserEvent(userId, eventId, "테스트유저", applyDate);

        given(userRepository.getUserById(userId))
            .willReturn(new User(userId, "테스트유저"));
        given(eventRepository.getEventById(eventId))
            .willReturn(new Event(eventId, "새 이벤트", EVENT_APPLY_DATE, EVENT_OPEN_DATE, 0));
        given(dateTimeProvider.now())
            .willReturn(applyDate);

        // when
        eventService.applyEvent(유저가_이벤트_신청);

        // then
        verify(applyHistoryRepository, times(1))
            .saveApplyHistory(argThat(applyHistory ->
                applyHistory.getUserId() == userId &&
                applyHistory.getEventId() == eventId
            ));
    }

    /*
    * 이벤트 신청 시 지정 시간(2024.04.20 13:20) 검증 테스트
    * */
    void 이벤트_신청시_지정시간_검증() {
        // given
        final long userId = 1L;
        final long eventId = 1L;
        final LocalDateTime applyDate = LocalDateTime.of(2024, 4, 20, 13, 20);
        final UserEvent 유저가_이벤트_신청 = new UserEvent(userId, eventId, "테스트유저", applyDate);

        given(userRepository.getUserById(userId))
            .willReturn(new User(userId, "테스트유저"));
        given(eventRepository.getEventById(eventId))
                .willReturn(new Event(eventId, "새 이벤트", EVENT_APPLY_DATE, EVENT_OPEN_DATE, 0));
        given(dateTimeProvider.now())
                .willReturn(applyDate);

        // when

        // then
        assertThatThrownBy(() -> eventService.applyEvent(유저가_이벤트_신청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이벤트 신청 시간이 아닙니다.");
    }

    /*
    * 이벤트 목록 조회
    * */
    @Test
    void getEventList() {
        // given
        given(eventRepository.getEvents())
                .willReturn(List.of(new Event(1L, "테스트 이벤트", EVENT_APPLY_DATE, EVENT_OPEN_DATE, 0))
        );

        // when
        final List<Event> 이벤트목록 = eventService.getEvents();

        // then
        assertThat(이벤트목록.size()).isEqualTo(1);
        assertThat(이벤트목록.get(0).getEventName()).isEqualTo("테스트 이벤트");
        assertThat(이벤트목록.get(0).getOpenDate()).isEqualTo("2024-04-23T13:20:00");
        assertThat(이벤트목록.get(0).getApplyDate()).isEqualTo(EVENT_APPLY_DATE);

    }

    @Test
    void 이벤트_목록_조회시_없으면_빈목록반환() {
        // given

        // when
        final List<Event> 이벤트목록 = eventService.getEvents();

        // then
        assertThat(이벤트목록.size()).isEqualTo(0);
    }

    @Test
    void 이벤트_신청_완료여부_조회() {
        // given
        final long userId = 1L;
        final long eventId = 1L;
        given(userEventRepository.getAppliedEvents(any(), any()))
            .willReturn(List.of(new UserEvent(userId, eventId, "테스트유저", LocalDateTime.now())));

        // when
        final List<UserEvent> 특강신청_완료_목록 = eventService.getAppliedEvents(userId);

        // then
        assertThat(특강신청_완료_목록.size()).isEqualTo(1);
        assertThat(특강신청_완료_목록.get(0).getUserId()).isEqualTo(userId);
        assertThat(특강신청_완료_목록.get(0).getEventId()).isEqualTo(eventId);
        assertThat(특강신청_완료_목록.get(0).getEventName()).isEqualTo("테스트유저");
    }




}
