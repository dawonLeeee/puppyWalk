package com.application.puppyWalk.controller.dto;

import com.application.puppyWalk.domain.UserEvent;
import lombok.Getter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@ToString
public class CreateApplyEventResponse {

   private long userId;
   private  long eventId;
   private String eventName;
   private LocalDateTime openDate;

   public CreateApplyEventResponse(long userId, long eventId, String eventName, LocalDateTime openDate) {
       this.userId = userId;
       this.eventId = eventId;
       this.eventName = eventName;
       this.openDate = openDate;
   }

   public static CreateApplyEventResponse from(UserEvent userEvent) {
       return new CreateApplyEventResponse(
               userEvent.getUserId(),
               userEvent.getEventId(),
               userEvent.getEventName(),
               userEvent.getOpenDate()
       );
   }
}
