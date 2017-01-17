package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import java.util.List;

/**
 * The type Role service.
 *
 * @author Ruslan Lazin
 */
public class RoleService {
    private static final Logger logger = Logger.getLogger(RoleService.class);
    private static final RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static final RoleService INSTANCE = new RoleService();

    private RoleService() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RoleService getINSTANCE() {
        return INSTANCE;
    }


    /**
     * Create long.
     *
     * @param role the role
     * @return the long - role id
     */
    public Long create(Role role) {
        return roleDao.create(role);
    }


    /**
     * Find role by id.
     *
     * @param roleId the role id
     * @return the role
     */
    public Role findById(Long roleId) {
        return roleDao.findById(roleId);
    }

    /**
     * Find all list.
     *
     * @return the list of Role
     */
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
