package com.home.kanban_service.users;

import com.home.kanban_service.kanban_item.KanbanItem;
import com.home.kanban_service.kanban_item.KanbanItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final KanbanItemRepository kanbanItemRepository;


    @Autowired
    public UserService(UserRepository userRepository, KanbanItemRepository kanbanItemRepository) {
        this.userRepository = userRepository;
        this.kanbanItemRepository = kanbanItemRepository;
    }

    public List<User> getUsers() {
       return userRepository.findAll();
    }

    public void addUser(User user){

        Optional<User> optionalUser = userRepository.findUserByUsername(user.getUsername());
        if (optionalUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
        } else {
            userRepository.save(user);
        }
    }

    public void deleteUser(Long userId){
        boolean exists = userRepository.existsById(userId);
        if (exists){
            userRepository.deleteById(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User does not exists.");
        }
    }

    public void updateUser(Long userId, User user){
        User oldUser = userRepository.getById(userId);
        if (oldUser !=null){
            String newUsername = user.getUsername();
            List<KanbanItem> items = kanbanItemRepository.findByUser(oldUser.getUsername());

            for (KanbanItem kanbanItem : items) {
                kanbanItem.setUser(newUsername);
            }

            user.setId(userId);
            kanbanItemRepository.saveAll(items);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User does not exists.");
        }
    }

}
