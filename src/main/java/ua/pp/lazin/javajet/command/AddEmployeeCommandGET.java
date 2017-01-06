package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.persistence.dao.PositionDao;
import ua.pp.lazin.javajet.persistence.entity.Position;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ruslan Lazin
 */
public class AddEmployeeCommandGET implements Command {
    private static final PositionDao POSITION_DAO = DaoFactoryCreator.getFactory().getRoleDao();
    private static final String ROLES_ATTRIBUTE = "roles";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Position> positions = POSITION_DAO.findAll();
        request.setAttribute(ROLES_ATTRIBUTE, positions);
        return "add-employee";
    }
}
