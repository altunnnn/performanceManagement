package com.altun.techcellhackathon.service;
import com.altun.techcellhackathon.entity.User;
import com.altun.techcellhackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;



    public Map<Long,String> findUser(String name){
        Map<Long,String> map = new HashMap<>();
        List<User> users = userRepository.findByUsernameContaining(name);
        if(users.isEmpty()) return map;
        users.stream().forEach((u)->map.put(u.getId(),u.getUsername()));
        return map;
    }

    private User getUserFromContext(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

}
