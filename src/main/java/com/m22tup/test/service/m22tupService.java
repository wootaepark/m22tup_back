package com.m22tup.test.service;

import com.m22tup.test.entity.m22tupEntity;
import com.m22tup.test.repository.m22tupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class m22tupService {

    @Autowired // 연결해 주는 느낌 repository와 Service 가 연결되어 있으므로
    private m22tupRepository repository;


    public m22tupEntity createPlace(Integer type, String location, String name, String address, String road_address,String phone, String url, Double x, Double y, String imgUrl) {

        m22tupEntity entity = new m22tupEntity();
        entity.setType(type);
        entity.setLocation(location);
        entity.setName(name);
        entity.setAddress(address);
        entity.setRoad_address(road_address);
        entity.setPhone(phone);
        entity.setUrl(url);
        entity.setX(x);
        entity.setY(y);
        entity.setImgUrl(imgUrl);


        return repository.save(entity);
    }


    // getAllPlace()와 updatePlace() 의 경우는 요소 하나를 가져오거나 수정할 수 있도록 수정이 필요할듯 ......
    public List<m22tupEntity> getAllPlace() {
        return repository.findAll();
    }


    public m22tupEntity getPlaceById(Long PlaceId) {
        return repository.findById(PlaceId).orElseThrow(() -> new RuntimeException("Place not found with ID: " + PlaceId));
    }
    public m22tupEntity updatePlace(m22tupEntity entity) {

        entity.setPhone("010-2222-2222");
        //근데 일일이 다 해야하는건가?
        return repository.save(entity);


    }


    public m22tupEntity deletePlace(m22tupEntity entity, Long locationId) {
        repository.deleteById(locationId);
        return entity;
    } // 이거는 웬만하면 쓸일이 없을 듯, 기존의 장소 정보를 지울일은 거의 없으니까
    // 삭제후 해당 entity 리턴, 다른 곳이 수정하고 저장하는것과 달리 이것은 삭제된 상태의 entity 리턴, 다른 entity 변수에 저장 필요


    public boolean whereisIt(Double x, Double y) { // 해당 범위 내에 가게가 있는지 확인하는 코드 (나중에 수정 필요 할수도)

        List<m22tupEntity> location = repository.findAll();

        m22tupEntity result = location.get(0);

        if (result.getX() < x + 5 && result.getX() > x - 5 && result.getY() < y + 5 && result.getY() > y - 5) {
            return true;
        } else return false;

    }


    public String isInData(m22tupEntity placeData) {

        List<m22tupEntity> allEntities = repository.findAll();

        // 결과를 저장할 리스트 생성
        List<m22tupEntity> matchingEntities = new ArrayList<>();

        // 세종시의 x,y 를 각각     132<=x<=133.5,   37=<y<=38 로 간단하게 가정하자!!!


        if (placeData.getX() <= 133.5 && placeData.getX() >= 132 && placeData.getY() <= 38 && placeData.getY() >= 37) {
            return "세종시";
        }
        else return "서울시";


    }

    public List<m22tupEntity> compareEntities(Double x, Double y,Integer type) { // 해당 위도, 경도 값을 주면 그 안에 있는 모든 가게 보여주기
        List<m22tupEntity> allEntities = repository.findAll();

        // 결과를 저장할 리스트 생성
        List<m22tupEntity> matchingEntities = new ArrayList<>();

        for (m22tupEntity entity : allEntities) {

            if (Math.abs(entity.getX()-x)<2 && Math.abs(entity.getY()-y)<2&&entity.getType()==type) {
                matchingEntities.add(entity);
            }
        }

        return matchingEntities;
    }








public boolean isDataValid(m22tupEntity placeData) {
        if (
                placeData.getX() == null ||
                        placeData.getY() == null ||
                        placeData.getType() == null ||
                       (placeData.getAddress() == null &&placeData.getRoad_address()==null)||
                        placeData.getLocation() == null ||
                        placeData.getName() == null
        )

            return false;
        else return true;
    }





}

