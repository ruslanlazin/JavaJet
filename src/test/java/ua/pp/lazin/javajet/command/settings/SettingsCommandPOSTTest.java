package ua.pp.lazin.javajet.command.settings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Ruslan Lazin
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingsCommandPOSTTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PositionService positionService;

    @Mock
    private RoleService roleService;

    @Mock
    private List<Role> roles;

    @Mock
    private List<Position> positions;

    @InjectMocks
    private SettingsCommandPOST underTest;

    @Before
    public void setUp() throws Exception {
        when(roleService.findAll()).thenReturn(roles);
        when(positionService.findAll()).thenReturn(positions);
    }

    @Test
    public void execute_TypeRole_CreateRole() throws Exception {
        //init
        when(request.getParameter("type")).thenReturn("role");
        when(request.getParameter("title")).thenReturn("test_title");
        Role role = Role.newBuilder().title("test_title").build();
        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("settings"));
        verify(request).getParameter("type");
        verify(request).getParameter("title");
        verify(roleService).create(role);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService);
    }

    @Test
    public void execute_TypePositionAirCrewTrue_CreatePosition() throws Exception {
        //init
        when(request.getParameter("type")).thenReturn("position");
        when(request.getParameter("title")).thenReturn("test_title");
        when(request.getParameter("aircrew")).thenReturn("true");
        Position position = Position.newBuilder().title("test_title").airCrew(true).build();
        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("settings"));
        verify(request).getParameter("type");
        verify(request).getParameter("title");
        verify(request).getParameter("aircrew");
        verify(positionService).create(position);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService);
    }

    @Test
    public void execute_TypePositionAirCrewFalse_CreatePosition() throws Exception {
        //init
        when(request.getParameter("type")).thenReturn("position");
        when(request.getParameter("title")).thenReturn("test_title");
        when(request.getParameter("aircrew")).thenReturn("false");
        Position position = Position.newBuilder().title("test_title").airCrew(false).build();
        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("settings"));
        verify(request).getParameter("type");
        verify(request).getParameter("title");
        verify(request).getParameter("aircrew");
        verify(positionService).create(position);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService);
    }

    @Test
    public void execute_TypeNull_ViewName() throws Exception {
        //init
        when(request.getParameter("type")).thenReturn(null);
        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("settings"));
        verify(request).getParameter("type");
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService);
    }


}