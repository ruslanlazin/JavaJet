package ua.pp.lazin.javajet.command.settings;

import ua.pp.lazin.javajet.command.Command;
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
    private static final String ROLES_ATTRIBUTE = "roles";
    private static final String POSITIONS_ATTRIBUTE = "positions";
    private static final String TYPE_PARAMETER = "type";
    private static final String TITLE_PARAMETER = "title";
    private static final String AIRCREW_PARAMETER = "aircrew";

    private PositionService positionService = PositionService.getINSTANCE();
    private RoleService roleService = RoleService.getINSTANCE();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter(TYPE_PARAMETER);

        if ("role".equals(type)) {
            Role role = Role.newBuilder().title(request.getParameter(TITLE_PARAMETER)).build();
            roleService.create(role);

        } else if ("position".equals(type)) {
            Position position = ua.pp.lazin.javajet.entity.Position.newBuilder()
                    .title(request.getParameter(TITLE_PARAMETER))
                    .airCrew(Boolean.valueOf(request.getParameter(AIRCREW_PARAMETER))).build();
            positionService.create(position);
        }

        request.setAttribute(ROLES_ATTRIBUTE, roleService.findAll());
        request.setAttribute(POSITIONS_ATTRIBUTE, positionService.findAll());

        return "settings";
    }
}

