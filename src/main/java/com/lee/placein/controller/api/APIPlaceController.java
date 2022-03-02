package com.lee.placein.controller.api;

import com.lee.placein.constant.PlaceType;
import com.lee.placein.dto.ApiDataResponse;
import com.lee.placein.dto.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public ApiDataResponse<List<PlaceDTO>> getPlaces(){
        return ApiDataResponse.of(List.of(PlaceDTO.of(
                PlaceType.KOREAN,
                "바벤",
                "서울시 강남구 역삼동 782-4",
                "010372918",
                30,
                "신장개업"
        )));
    }

    @PostMapping("/places")
    public Boolean createPlace(){
        return true;
    }

    @GetMapping("/places/{placeId}")
    public ApiDataResponse<PlaceDTO> getPlace(@PathVariable Integer placeId){
        if (placeId.equals(2)){
            return ApiDataResponse.of(null); // 장소가 없는 경우 null 리턴
        }

        return ApiDataResponse.of(PlaceDTO.of(
                PlaceType.KOREAN,
                "바벤",
                "서울시 강남구 역삼동 782-4",
                "010372918",
                30,
                "신장개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId){
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(@PathVariable Integer placeId){
        return true;
    }
}
