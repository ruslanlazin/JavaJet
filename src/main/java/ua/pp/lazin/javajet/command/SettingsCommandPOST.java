package ua.pp.lazin.javajet.command;

import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Ruslan Lazin
 */
public class SettingsCommandPOST implements Command {
    private static final PositionService positionService = PositionService.getINSTANCE();
    private static final RoleService roleService = RoleService.getINSTANCE();
    private static final String ROLES_ATTRIBUTE = "roles";
    private static final String POSITIONS_ATTRIBUTE = "positions";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter("setType");

        if ("role".equals(type)) {
            Role role = Role.newBuilder().title(request.getParameter("title")).build();
            roleService.create(role);

        } else if ("position".equals(type)) {
            Position position = ua.pp.lazin.javajet.entity.Position.newBuilder()
                    .title(request.getParameter("title"))
                    .airCrew(Boolean.valueOf(request.getParameter("aircrew"))).build();
        }


        request.setAttribute(ROLES_ATTRIBUTE, roleService.findAll());
        request.setAttribute(POSITIONS_ATTRIBUTE, positionService.findAll());
        return "settings";
    }
}

