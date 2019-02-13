package com.roypan.learnshiro.service;

import com.roypan.learnshiro.entity.User;
import com.roypan.learnshiro.exception.ResourceNotFoundException;
import com.roypan.learnshiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Roy Pan
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Long userId,User user){
        User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId:" + userId + " not found!"));
        u.setUserName(user.getUserName());
        u.setRoleList(user.getRoleList());
        userRepository.save(u);
    }

    public User queryUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId:" + userId + " not found!"));
    }

    public User queryByUserName(String userName){
        return userRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("userName:" + userName + " not found!"));
    }

    public List<User> queryAll(){
        return userRepository.findAll();
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
