package com.m22tup.test.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.m22tup.test.entity.m22tupEntity;
import com.m22tup.test.repository.m22tupRepository;
import com.m22tup.test.service.m22tupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.35.5:3000", allowedHeaders = "Access-Control_Allow_Origin")
public class m22tupController {
    @Autowired
    private m22tupService service;
    private m22tupRepository repository;
    private m22tupEntity entity;
    




    @GetMapping("/maps")
    public String getMap() {

        return "maps.html";
    }


    @GetMapping(value = "/compare", consumes = MediaType.APPLICATION_JSON_VALUE) // 동일한 데이터가 있는지
    public Map<String, Object> getJsonData(@RequestParam(name = "x") Double x, @RequestParam(name = "y") Double y) {
        Map<String, Object> jsonData = new HashMap<>();
        List<m22tupEntity> entityList = service.compareEntities(x, y);

        jsonData.put("entityList", entityList);
        return jsonData;
    } // x,y 좌표를 받고 그것을 중심으로 지정된 범위 내에 있는 모든 시설을 json 데이터로 리턴

    /*{
    "entityList": [
        {"id": 1, "name": "John"},
        {"id": 2, "name": "Alice"},
        // 위의 getJsonData 컨트롤러는 위와 같은 형태로 front 로 옮겨지게 된다.
    ]
}*/
    @PostMapping(value = "/receiveLocation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> receiveLocation(@RequestBody JsonNode locationData) {
        Double x = locationData.get("x").asDouble();
        Double y = locationData.get("y").asDouble();

        // json 데이터의 위,경도 값을 double 형태로 치환함
        Map<String, Object> jsonList = new HashMap<>();
        List<m22tupEntity> entityList = service.compareEntities(x,y);

        jsonList.put("ServiceList",entityList);

        return jsonList;

    } // json 데이터로 위도, 경도를 받고 범위 내에 있는 모든 시설 json 반환

    @GetMapping("/calculate")
    public String calculateAge(Model model) {
        service.createPlace(1, "세종시", "무한카페", "상세주소","상세 도로명 주소" ,"010-1111-1111", "www.yyy,yyy", 139.33, 36.265, "www.xxx.xxx");

        boolean isIn = service.whereisIt(120.0, 35.0);

        if (isIn == true) {
            return "이 장소는 범위 내에 있습니다";
        } else return "이 장소는 범위 내에 존재하지 않습니다.";
    }


    @GetMapping("/")
    public String go(Model model) {
        return "helloFront!";
    }
    //public m22tupEntity get(){return new m22tupEntity(1,"a","a","a","a","a",6.0,8.0,"a");}
    // 주석과 같이 하면 반환으로서 json 값을 시각적으로 보여준다. (프론트 서버에 데이터를 반환시에 위와 같이 하면 될듯)

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPlace(@RequestBody m22tupEntity placeData) {
        if (service.isDataValid(placeData)) {
            m22tupEntity createdPlace = service.createPlace(
                    placeData.getType(),
                    placeData.getLocation(),
                    placeData.getName(),
                    placeData.getAddress(),
                    placeData.getRoad_address(),
                    placeData.getPhone(),
                    placeData.getUrl(),
                    placeData.getX(),
                    placeData.getY(),
                    placeData.getImgUrl()

            );

            if (createdPlace != null) {

                return ResponseEntity.ok("장소 등록을 성공했습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알 수 없는 오류로 장소 등록 불가능합니다.");
            }
        } else {
            return ResponseEntity.badRequest().body("올바르지 않은 데이터가 입력되었습니다.");
        }


    }
}
