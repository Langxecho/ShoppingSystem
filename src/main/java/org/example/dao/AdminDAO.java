package org.example.dao;

import org.example.domain.User;

public interface AdminDAO {
    boolean checkLogin(User user);
}
