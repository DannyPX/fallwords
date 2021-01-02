package com.scrumble.userapi.Logic;

import com.scrumble.userapi.Models.User;
import com.scrumble.userapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private final Board board;

    UserService(User user)
    {
        //this.board = board;
    }

    public UserService() {}

    public User create(final User board) {
        return userRepository.save(board);
    }

    public User getById(int id) {
        Optional<User> user = userRepository.findById(id);

        return user.isEmpty() ? null : user.get();
    }

    public List<User> getAll() {
        var users = new ArrayList<User>();
         userRepository.findAll().forEach(users::add);
         return users;
    }

}
