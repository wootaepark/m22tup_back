package com.m22tup.test.entity;

import javax.persistence.*;

@Entity
@Table(name = "m22tup_table")
public class m22tupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk 값, 필수


    private Integer type;// 공공회의실, 카페, 민간회의실, 필수
    private String location; // 세종시
    private String name; // 업체명
    private String address;// 상세주소

    private String road_address;// 도로명 주소

    public String getRoad_address() {
        return road_address;
    }

    public void setRoad_address(String road_address) {
        this.road_address = road_address;
    }

    private String phone;// 전화번호
    private String url;// 업체의 url이 있으면 (Null 가능)
    private Double x;// x좌표, 필수
    private Double y;// y좌표 ,필수
    private String imgUrl;// 이미지 주소







    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public m22tupEntity() {

    }


    public m22tupEntity( Integer type, String location, String name, String address, String phone, String url, Double x, Double y, String imgUrl) {

        this.type = type;
        this.location = location;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.url = url;
        this.x = x;
        this.y = y;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
