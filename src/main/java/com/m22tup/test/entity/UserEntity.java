package com.m22tup.test.entity;



import javax.persistence.*;

@Entity
@Table(name="UserTable")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk 값, 필수

    private String user_name;
    private String phone_number;
    private Integer number_of_reservators;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PlaceId")
    private m22tupEntity Places;

    public UserEntity(String name, String phone_number, Integer number_of_reservators, m22tupEntity places) {

        this.user_name= name;
        this.phone_number = phone_number;
        this.number_of_reservators = number_of_reservators;
        Places = places;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        this.user_name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getNumber_of_reservators() {
        return number_of_reservators;
    }

    public void setNumber_of_reservators(Integer number_of_reservators) {
        this.number_of_reservators = number_of_reservators;
    }

    public m22tupEntity getPlaces() {
        return Places;
    }

    public void setPlaces(m22tupEntity places) {
        Places = places;
    }
}
