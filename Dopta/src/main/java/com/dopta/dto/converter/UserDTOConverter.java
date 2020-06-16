package com.dopta.dto.converter;

import com.dopta.dto.user.CreateUserDTO;
import com.dopta.dto.user.UserDTO;
import com.dopta.model.Person;
import com.dopta.model.User;
import com.dopta.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserDTOConverter {
    private final ModelMapper modelMapper;

    //mapExplicitly
    @PostConstruct
    public void init(){
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {

            @Override
            protected void configure() {
                map().setDistrict(source.getUserProfile().getDistrict().getName());
                map().setLocatable(source.getUserProfile().getLocatable().getName());
                map().setGender(source.getPerson().getGender().getName());
            }
        });
    }

    //mapAutomatic
    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);

    }

    public User convertToEntity(CreateUserDTO productDto) {
        return modelMapper.map(productDto, User.class);

    }


}