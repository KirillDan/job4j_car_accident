package ru.job4j.accident.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.job4j.accident.dto.User;

@Component
public class UserStore {
    private List<User> data = new ArrayList<User>();

    public void add(User value) {
        data.add(value);
    }

    public List<User> getAll() {
        return data;
    }
}
