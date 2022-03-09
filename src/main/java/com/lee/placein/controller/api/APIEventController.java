package com.lee.placein.controller.api;

import com.lee.placein.constant.ErrorCode;
import com.lee.placein.constant.EventStatus;
import com.lee.placein.dto.ApiDataResponse;
import com.lee.placein.dto.ApiErrorResponse;
import com.lee.placein.dto.EventResponse;
import com.lee.placein.exception.GeneralException;
import com.lee.placein.repository.EventRepository;
import com.lee.placein.service.EventService;
import com.sun.jdi.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class APIEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public ApiDataResponse<List<EventResponse>> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
    ) {
        List<EventResponse> eventResponses = eventService.getEvents(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime
        ).stream().map(EventResponse::from).toList();

        return ApiDataResponse.of(eventResponses);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public ApiDataResponse<Void> createEvent(@RequestBody EventRequest eventRequest) {
        return ApiDataResponse.empty();
    }

    @GetMapping("/events/{eventId}")
    public ApiDataResponse<EventResponse> getEvent(@PathVariable Long eventId) {
        if (eventId.equals(2L)) {
            return ApiDataResponse.empty();
        }

        return ApiDataResponse.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021, 1, 1, 13, 0, 0),
                LocalDateTime.of(2021, 1, 1, 16, 0, 0),
                0,
                24,
                "마스크 꼭 착용하세요"
        ));
    }

    @PutMapping("/events/{eventId}")
    public ApiDataResponse<Void> modifyEvent(
            @PathVariable Long eventId,
            @RequestBody EventRequest eventRequest
    ) {
        return ApiDataResponse.empty();
    }

    @DeleteMapping("/events/{eventId}")
    public ApiDataResponse<Void> removeEvent(@PathVariable Long eventId) {
        return ApiDataResponse.empty();
    }

}