package com.m22tup.test.service;


import com.m22tup.test.entity.UserEntity;
import com.m22tup.test.entity.m22tupEntity;
import com.m22tup.test.repository.UserRepository;
import com.m22tup.test.repository.m22tupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository user_repository;
    private UserEntity userEntity;

    @Autowired
    private m22tupRepository repository;
    m22tupService service;





    public UserEntity setPlace(String user_name, String phone_number,Integer number_of_reservators, Long place_id) {

       m22tupEntity place = repository.findById(place_id).orElse(null);

        UserEntity entity = new UserEntity();

        entity.setName(user_name);
        entity.setNumber_of_reservators(number_of_reservators);
        entity.setPhone_number(phone_number);
        entity.setPlaces(place);


        return user_repository.save(entity);
    }
}
