package com.example.miniProject.Repo;

import java.util.Optional;

import com.example.miniProject.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    @Autowired
    private JdbcTemplate temp;

    public Optional<User> getUserByEmail(String email) {
        final SqlRowSet q = temp.queryForRowSet(
            "select * from user where email like ?", email
        );
        if(!q.next())
            return Optional.empty();

        return Optional.of(User.create(q));
    }

    public boolean save(User user) {
        final int added = temp.update(
            "insert into user (userId,email,password) values (?,?,?)"
            ,user.getId(),user.getEmail(),user.getPassword()
        );
        return added > 0;
    }
}
