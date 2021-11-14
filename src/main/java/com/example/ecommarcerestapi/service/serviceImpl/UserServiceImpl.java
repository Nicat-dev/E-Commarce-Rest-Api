package com.example.ecommarcerestapi.service.serviceImpl;

import com.example.ecommarcerestapi.dto.request.CreateUserRequest;
import com.example.ecommarcerestapi.dto.request.UpdateUserRequest;
import com.example.ecommarcerestapi.enums.EnumAvaibleStatus;
import com.example.ecommarcerestapi.exception.DomainNotFoundException;
import com.example.ecommarcerestapi.exception.MethodNullArgumentException;
import com.example.ecommarcerestapi.mapper.UserMapper;
import com.example.ecommarcerestapi.model.User;
import com.example.ecommarcerestapi.repository.UserRepository;
import com.example.ecommarcerestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public void create(CreateUserRequest userRequest) {
        log.info("User successfuly added");
        userRequest.setUserPassword(encoder.encode(userRequest.getUserPassword()));
        userRepository.save(userMapper.toEntity(userRequest));
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAllByStatus(EnumAvaibleStatus.ACTIVE.getValue());
        if (users == null)
            throw new DomainNotFoundException("no user avaible");
        return users;
    }

    @Override
    public User findUserById(Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be null");
        return userRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException("Domain not found")
        );
    }

    @Override
    public void delete(Long id) {
        if(id == null)
            throw new MethodNullArgumentException("id can not be null");
        User user = userRepository.findUserByUserIdAndStatus(id, EnumAvaibleStatus.ACTIVE.getValue());
        if (user == null)
            throw new DomainNotFoundException("User not found !");
        user.setStatus(EnumAvaibleStatus.DEACTIVE.getValue());
        userRepository.save(user);
    }

    @Override
    public boolean doesExistByUserName(String username) {
        if (username == null)
            throw new MethodNullArgumentException("id can not be null");
        return userRepository.findUserByUserName(username).isPresent();
    }

    @Override
    public void update(UpdateUserRequest userRequest,Long id) {
        if (id == null)
            throw new MethodNullArgumentException("id can not be null");
        User user = userRepository.findById(id).orElseThrow(
                ()-> new DomainNotFoundException(" user not found")
        );
        userRepository.save(updateUser(user,userRequest));

    }
    private User updateUser(User user,UpdateUserRequest request){
        User user1 = User.builder()
                .userName(request.getUserName() == null?
                        user.getUserName() : user.getUserName())
                .userEmail(request.getUserGmail() == null?
                        user.getUserEmail() : user.getUserEmail())
                .userAdress(request.getUserAddress() == null?
                        user.getUserAdress() : user.getUserAdress())
                .userAge(request.getAge() == null?
                        user.getUserAge() : user.getUserAge())
                .userSurname(request.getUserSurname() == null?
                        user.getUserSurname() : user.getUserSurname())
                .userPassword(request.getPassword() == null?
                        user.getUserPassword() : user.getUserPassword())
                .userGeneder(request.getGender() == null?
                        user.getUserGeneder() : user.getUserGeneder())
                .build();
        return null;
    }

}
