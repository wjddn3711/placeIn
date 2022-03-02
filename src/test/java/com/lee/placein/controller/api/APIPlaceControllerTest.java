package com.lee.placein.controller.api;

import com.lee.placein.constant.ErrorCode;
import com.lee.placein.constant.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(APIPlaceController.class)
class APIPlaceControllerTest {

    private final MockMvc mvc;

    public APIPlaceControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[API][GET] 장소리스트 조회")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlacesInStandardResponse() throws Exception{
        // given


        // When & Then
        mvc.perform(get("/api/places"))
                .andExpect(status().isOk()) // http status 검사
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.KOREAN.name()))
                .andExpect(jsonPath("$.data[0].placeName").value("바벤"))
                .andExpect(jsonPath("$.data[0].address").value("서울시 강남구 역삼동 782-4"))
                .andExpect(jsonPath("$.data[0].phoneNumber").value("010372918"))
                .andExpect(jsonPath("$.data[0].capacity").value(30))
                .andExpect(jsonPath("$.data[0].memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][GET] 단일 장소 조회 - 장소 있는 경우 ")
    @Test
    void givenPlaceAndItsId_whenRequestingPlace_thenReturnsPlaceInStandardResponse() throws Exception{
        // given
        int placeId = 1;

        // When & Then
        mvc.perform(get("/api/places/" + placeId))
                .andExpect(status().isOk()) // http status 검사
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.placeType").value(PlaceType.KOREAN.name()))
                .andExpect(jsonPath("$.data.placeName").value("바벤"))
                .andExpect(jsonPath("$.data.address").value("서울시 강남구 역삼동 782-4"))
                .andExpect(jsonPath("$.data.phoneNumber").value("010372918"))
                .andExpect(jsonPath("$.data.capacity").value(30))
                .andExpect(jsonPath("$.data.memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }


    @DisplayName("[API][GET] 단일 장소 조회 - 장소 없는 경우 ")
    @Test
    void givenPlaceId_whenRequestingPlace_thenReturnsEmptyStandardResponse() throws Exception{
        // given
        int placeId = 2;

        // When & Then
        mvc.perform(get("/api/places/" + placeId))
                .andExpect(status().isOk()) // http status 검사
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }
}