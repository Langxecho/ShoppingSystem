package org.example.factory;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:46
 */

public class ServiceFactory {
    public static UserDao getUserServiceInstance(){
        return new UserDaoImpl();
    }
}
