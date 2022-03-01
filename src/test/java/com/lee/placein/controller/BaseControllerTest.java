package com.lee.placein.controller;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("[view][GET] 기본 페이지 요청")
    @Test
    void root() throws Exception{
        // given


        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk()) // http status 검사
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // view 가 html인지 검사
                .andExpect(content().string(containsString("this is default page")))
                .andExpect(view().name("index")) // view 이름이 index인지
                .andDo(print()); // 마지막에 프린트 할 수 있도록 해준다

    }
}