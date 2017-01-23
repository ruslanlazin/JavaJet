package ua.pp.lazin.javajet.command.employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;
import ua.pp.lazin.javajet.service.UserService;

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
public class EditEmployeeCommandGETTest {

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
    private EditEmployeeCommandGET underTest;

    @Before
    public void setUp() throws Exception {
        when(roleService.findAll()).thenReturn(roles);
        when(positionService.findAll()).thenReturn(positions);
    }

    @Test
    public void execute_UserIdCorrect_ViewName() throws Exception {
        //init
        when(request.getParameter("userId")).thenReturn("123");
        when(userService.findByIdWithRoles(123L)).thenReturn(testUser);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(request).getParameter("userId");
        verify(userService).findByIdWithRoles(123L);
        verify(request).setAttribute("employee", testUser);
        verify(request).setAttribute("editMode", true);
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }

    @Test(expected = NumberFormatException.class)
    public void execute_UserIdIncorrect_ThrowsException() throws Exception {
        //init
        when(request.getParameter("userId")).thenReturn("NotANumber");

        //use
        String viewName = underTest.execute(request, response);
    }

    @Test
    public void execute_UserIdNull_ViewName() throws Exception {
        //init
        when(request.getParameter("userId")).thenReturn(null);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employee"));
        verify(request).getParameter("userId");
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService, userService);
    }
}

