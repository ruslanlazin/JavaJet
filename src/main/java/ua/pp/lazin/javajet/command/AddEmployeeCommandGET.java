package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class AddEmployeeCommandGET implements Command {
    private static final RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static final String ROLES_ATTRIBUTE = "roles";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Role> roles = roleDao.findAll();
        request.setAttribute(ROLES_ATTRIBUTE, roles);
        return "add-employee";
    }
}
