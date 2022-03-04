package com.lee.placein.service;

import com.lee.placein.constant.EventStatus;
import com.lee.placein.dto.EventDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService{

    private final EventRepository eventRepository;

    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        return List.of(EventDTO.of(
                placeId,
                eventName,
                EventStatus.OPENED,
                LocalDateTime.parse("2021-01T00:00:00"),
                LocalDateTime.parse("2021-01T00:00:00"),
                0,
                24,
                "마스크 꼭 착용하세요",
                LocalDateTime.now(),
                LocalDateTime.now()
        ),
                EventDTO.of(
                        placeId,
                        eventName,
                        eventStatus,
                        eventStartDateTime,
                        eventEndDateTime,
                        0,
                        24,
                        "마스크 꼭 착용하세요",
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
    }

    public Optional<EventDTO> getEvent(Long eventId){
        return Optional.empty();
    }

    public boolean createEvent(EventDTO eventDTO){
        return true;
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO){
        return true;
    }

    public boolean removeEvent(Long eventId){
        return true;
    }
}
