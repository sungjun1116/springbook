package com.springbook.user.service;

import com.springbook.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {

    void add(User user);

    @Transactional(readOnly = true)
    User get(String id);

    @Transactional(readOnly = true)
    List<User> getAll();

    void deleteAll();

    void update(User user);

    void upgradeLevels();
}
