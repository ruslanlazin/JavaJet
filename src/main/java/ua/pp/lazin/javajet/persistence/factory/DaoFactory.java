package ua.pp.lazin.javajet.persistence.factory;

import ua.pp.lazin.javajet.persistence.dao.UserDao;

/**
 * @author Ruslan Lazin
 */
public interface DaoFactory {
    UserDao getUserDao();

}
