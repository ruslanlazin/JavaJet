package ua.pp.lazin.javajet.command.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.lazin.javajet.entity.User;
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
public class EmployeesCommandGETTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @Mock
    private List<User> users;

    @InjectMocks
    private EmployeesCommandGET underTest;

    @Test
    public void execute() throws Exception {
        //init
        when(userService.findAll()).thenReturn(users);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("employees"));
        verify(userService).findAll();
        verify(request).setAttribute("employees", users);
        verifyNoMoreInteractions(request, response, userService);
    }

}