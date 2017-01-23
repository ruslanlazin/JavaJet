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
import static org.mockito.Mockito.*;

/**
 * @author Ruslan Lazin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EntityParser.class)
public class EditEmployeeCommandPOSTTest {

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

    @Mock
    private User userFromDB;

    @InjectMocks
    private EditEmployeeCommandPOST underTest;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(EntityParser.class);
        when(EntityParser.parseUserWithRoles(request)).thenReturn(testUser);
        when(roleService.findAll()).thenReturn(roles);
        when(positionService.findAll()).thenReturn(positions);
        when(testUser.getId()).thenReturn(1L);
        when(userService.findById(1L)).thenReturn(userFromDB);
        when(userFromDB.getEmail()).thenReturn("email");
        when(userFromDB.getUsername()).thenReturn("username");
        when(userService.findByIdWithRoles(1L)).thenReturn(userFromDB);
    }

    @Test
    public void execute_EmailUsernameNotChangedUpdateSuccess_ViewName() throws Exception {
        //init
        when(testUser.getEmail()).thenReturn("email");
        when(testUser.getUsername()).thenReturn("username");
        when(userService.updateWithRoles(testUser)).thenReturn(true);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).findById(1L);
        verify(userService).updateWithRoles(testUser);
        verify(userService).findByIdWithRoles(1L);
        verify(request).setAttribute("success", true);
        verify(request).setAttribute("employee", userFromDB);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test
    public void execute_EmailUsernameNotChangedUpdateFail_ViewName() throws Exception {
        //init
        when(testUser.getEmail()).thenReturn("email");
        when(testUser.getUsername()).thenReturn("username");
        when(userService.updateWithRoles(testUser)).thenReturn(false);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).findById(1L);
        verify(userService).updateWithRoles(testUser);
        verify(userService).findByIdWithRoles(1L);
        verify(request).setAttribute("concurrent", true);
        verify(request).setAttribute("employee", userFromDB);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test
    public void execute_EmailUsernameAvailableUpdateSuccess_ViewName() throws Exception {
        //init
        when(testUser.getEmail()).thenReturn("new_email");
        when(testUser.getUsername()).thenReturn("new_username");
        when(userService.isEmailAvailable("new_email")).thenReturn(true);
        when(userService.isUsernameAvailable("new_username")).thenReturn(true);
        when(userService.updateWithRoles(testUser)).thenReturn(true);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).findById(1L);
        verify(userService).isEmailAvailable("new_email");
        verify(userService).isUsernameAvailable("new_username");
        verify(request).setAttribute("wrongemail", false);
        verify(request).setAttribute("wrongusername", false);
        verify(userService).updateWithRoles(testUser);
        verify(userService).findByIdWithRoles(1L);
        verify(request).setAttribute("success", true);
        verify(request).setAttribute("employee", userFromDB);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }


    @Test
    public void execute_EmailUsernameAvailableUpdateFail_ViewName() throws Exception {
        //init
        when(testUser.getEmail()).thenReturn("new_email");
        when(testUser.getUsername()).thenReturn("new_username");
        when(userService.isEmailAvailable("new_email")).thenReturn(true);
        when(userService.isUsernameAvailable("new_username")).thenReturn(true);
        when(userService.updateWithRoles(testUser)).thenReturn(false);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).findById(1L);
        verify(userService).isEmailAvailable("new_email");
        verify(userService).isUsernameAvailable("new_username");
        verify(request).setAttribute("wrongemail", false);
        verify(request).setAttribute("wrongusername", false);
        verify(userService).updateWithRoles(testUser);
        verify(userService).findByIdWithRoles(1L);
        verify(request).setAttribute("concurrent", true);
        verify(request).setAttribute("employee", userFromDB);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test
    public void execute_EmailNotAvailable_ViewName() throws Exception {
        //init
        when(testUser.getEmail()).thenReturn("new_email");
        when(testUser.getUsername()).thenReturn("new_username");
        when(userService.updateWithRoles(testUser)).thenReturn(true);
        when(userService.isEmailAvailable("new_email")).thenReturn(false);
        when(userService.isUsernameAvailable("new_username")).thenReturn(true);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).findById(1L);
        verify(userService).isEmailAvailable("new_email");
        verify(userService).isUsernameAvailable("new_username");
        verify(request).setAttribute("wrongemail", true);
        verify(request).setAttribute("wrongusername", false);
        verify(request).setAttribute("employee", testUser);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test
    public void execute_UsernameNotAvailable_ViewName() throws Exception {
        //init
        when(testUser.getEmail()).thenReturn("new_email");
        when(testUser.getUsername()).thenReturn("new_username");
        when(userService.updateWithRoles(testUser)).thenReturn(true);
        when(userService.isEmailAvailable("new_email")).thenReturn(true);
        when(userService.isUsernameAvailable("new_username")).thenReturn(false);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).findById(1L);
        verify(userService).isEmailAvailable("new_email");
        verify(userService).isUsernameAvailable("new_username");
        verify(request).setAttribute("wrongemail", false);
        verify(request).setAttribute("wrongusername", true);
        verify(request).setAttribute("employee", testUser);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test
    public void execute_UsernameAndEmailNotAvailable_ViewName() throws Exception {
        //init
        when(testUser.getEmail()).thenReturn("new_email");
        when(testUser.getUsername()).thenReturn("new_username");
        when(userService.updateWithRoles(testUser)).thenReturn(true);
        when(userService.isEmailAvailable("new_email")).thenReturn(false);
        when(userService.isUsernameAvailable("new_username")).thenReturn(false);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(userService).findById(1L);
        verify(userService).isEmailAvailable("new_email");
        verify(userService).isUsernameAvailable("new_username");
        verify(request).setAttribute("wrongemail", true);
        verify(request).setAttribute("wrongusername", true);
        verify(request).setAttribute("employee", testUser);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

}