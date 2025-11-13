package com.mitesh.portfolio_service.service;

import com.mitesh.portfolio_service.model.User;
import com.mitesh.portfolio_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void createNewUser(User user) {
        userRepository.save(user);
    }

    public void followCoin(Integer userid,String coinid) {
        Optional<User> user = userRepository.findById(userid);
        user.ifPresent(u->{
            List<String> followedcoins = user.get().getFollowedcoins();
            if(followedcoins==null) followedcoins = new ArrayList<>();
            followedcoins.add(coinid);
            user.get().setFollowedcoins(followedcoins);
            userRepository.save(user.get());
        });
    }

    public void unFollowCoin(Integer userid, String coinid) {
        Optional<User> user = userRepository.findById(userid);
        user.ifPresent(u->{
            List<String> followedcoins = user.get().getFollowedcoins();
            if(followedcoins==null) followedcoins = new ArrayList<>();
            followedcoins.remove(coinid);
            user.get().setFollowedcoins(followedcoins);
            userRepository.save(user.get());
        });
    }
}
