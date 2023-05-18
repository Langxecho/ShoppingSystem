package org.example.dao;

import org.example.domain.User;

import java.util.ArrayList;

public interface AdminDAO {
    boolean checkLogin(User user);
    ArrayList checkGoods();//将所有的商品信息调出，储存在数组当中
}
