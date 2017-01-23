package ua.pp.lazin.javajet.command.employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;
import ua.pp.lazin.javajet.service.UserService;
import ua.pp.lazin.javajet.util.EntityParser;

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
@RunWith(PowerMockRunner.class)
@PrepareForTest(EntityParser.class)
public class AddEmployeeCommandPOSTTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PositionService positionService;

    @Mock
    private RoleService roleService;

    @Mock
    private UserService userService;

    @Mock
    private List<Role> roles;

    @Mock
    private List<Position> positions;

    @Mock
    private User testUser;

    @InjectMocks
    private AddEmployeeCommandPOST underTest;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(EntityParser.class);
        when(EntityParser.parseUserWithRoles(request)).thenReturn(testUser);
        when(roleService.findAll()).thenReturn(roles);
        when(positionService.findAll()).thenReturn(positions);
        when(testUser.getEmail()).thenReturn("email");
        when(testUser.getUsername()).thenReturn("username");
    }

    @Test
    public void execute_EmailUsernameAvailable_Create() throws Exception {
        //init
        when(userService.isEmailAvailable("email")).thenReturn(true);
        when(userService.isUsernameAvailable("username")).thenReturn(true);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).isEmailAvailable("email");
        verify(userService).isUsernameAvailable("username");
        verify(userService).create(testUser);
        verify(request).setAttribute("success", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test
    public void execute_EmailNotAvailable_ViewName() throws Exception {
        //init
        when(userService.isEmailAvailable("email")).thenReturn(false);
        when(userService.isUsernameAvailable("username")).thenReturn(true);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).isEmailAvailable("email");
        verify(userService).isUsernameAvailable("username");
        verify(request).setAttribute("wrongemail", true);
        verify(request).setAttribute("wrongusername", false);
        verify(request).setAttribute("employee", testUser);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }


    @Test
    public void execute_UsernameNotAvailable_ViewName() throws Exception {
        //init
        when(userService.isEmailAvailable("email")).thenReturn(true);
        when(userService.isUsernameAvailable("username")).thenReturn(false);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).isEmailAvailable("email");
        verify(userService).isUsernameAvailable("username");
        verify(request).setAttribute("wrongemail", false);
        verify(request).setAttribute("wrongusername", true);
        verify(request).setAttribute("employee", testUser);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test
    public void execute_EmailUsernameNotAvailable_ViewName() throws Exception {
        //init
        when(userService.isEmailAvailable("email")).thenReturn(false);
        when(userService.isUsernameAvailable("username")).thenReturn(false);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).isEmailAvailable("email");
        verify(userService).isUsernameAvailable("username");
        verify(request).setAttribute("wrongemail", true);
        verify(request).setAttribute("wrongusername", true);
        verify(request).setAttribute("employee", testUser);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }
}