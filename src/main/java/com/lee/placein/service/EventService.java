package com.lee.placein.service;

import com.lee.placein.constant.EventStatus;
import com.lee.placein.dto.EventDTO;
import com.lee.placein.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
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
        return eventRepository.findEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);
    }

    public Optional<EventDTO> getEvent(Long eventId){
        return eventRepository.findEvent(eventId);
    }

    public boolean createEvent(EventDTO eventDTO){
        return eventRepository.insertEvent(eventDTO);
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO){
        return eventRepository.updateEvent(eventId,eventDTO);
    }

    public boolean removeEvent(Long eventId){
        return eventRepository.deleteEvent(eventId);
    }
}
