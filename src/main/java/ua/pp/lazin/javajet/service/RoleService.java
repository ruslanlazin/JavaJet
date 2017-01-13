package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.persistence.dao.AircraftDao;
import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
public class RoleService {
    private static final Logger logger = Logger.getLogger(RoleService.class);
    private static final RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static final RoleService INSTANCE = new RoleService();

    private RoleService() {
    }

    public static RoleService getINSTANCE() {
        return INSTANCE;
    }


    public List<Role> findAll() {
        return roleDao.findAll();
    }


    public Long create(Role role) {
        return roleDao.create(role);
    }

    public Role findById(Long roleId) {
        return roleDao.findById(roleId);
    }
}
