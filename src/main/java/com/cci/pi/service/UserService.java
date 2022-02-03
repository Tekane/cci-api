package com.cci.pi.service;

import com.cci.pi.error.ResourceNotFoundException;
import com.cci.pi.model.User;
import com.cci.pi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User updateUser, long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User userUpdate = user.get();
            userUpdate.setUsername(userUpdate.getUsername());
            userUpdate.setLast_name(updateUser.getLast_name());
            userRepository.save(userUpdate);
        } else {
            throw new ResourceNotFoundException("Record for this id " + userId + "is not found");
        }
    }

    @Transactional
    public User getUserById(long userId) throws Exception {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new ResourceNotFoundException("Record for this id " + userId + "is not found");
        }
        return user;
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
