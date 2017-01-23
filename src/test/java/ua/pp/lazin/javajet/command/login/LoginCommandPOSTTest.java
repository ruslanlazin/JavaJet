package ua.pp.lazin.javajet.command.login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.lazin.javajet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Ruslan Lazin
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginCommandPOSTTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginCommandPOST underTest;

    @Before
    public void setUp() throws Exception {
        doThrow(ServletException.class).when(request).login(anyString(), anyString());
        doNothing().when(request).login("test_login", "test_password");
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void execute_CorrectLoginPassword_Redirect() throws Exception {
        //init
        when(request.getParameter("login")).thenReturn("test_login");
        when(request.getParameter("password")).thenReturn("test_password");

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("redirect:/common/"));
        verify(request).getParameter("login");
        verify(request).getParameter("password");
        verify(request).getRemoteAddr();
        verify(request).login("test_login", "test_password");
        verify(request).getSession();
        verify(userService).cacheUserInSession("test_login", session);
        verifyNoMoreInteractions(request, response, userService);
    }

    @Test
    public void execute_WrongLogin_ReturnToLogin() throws Exception {
        //init
        when(request.getParameter("login")).thenReturn("wrong_login");
        when(request.getParameter("password")).thenReturn("test_password");

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("login"));
        verify(request).getParameter("login");
        verify(request).getParameter("password");
        verify(request).login("wrong_login", "test_password");
        verify(request).setAttribute("wronglogin", true);
        verifyNoMoreInteractions(request, response, userService);
    }

    @Test
    public void execute_WrongPassword_ReturnToLogin() throws Exception {
        //init
        when(request.getParameter("login")).thenReturn("test_login");
        when(request.getParameter("password")).thenReturn("wrong_password");

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("login"));
        verify(request).getParameter("login");
        verify(request).getParameter("password");
        verify(request).login("test_login", "wrong_password");
        verify(request).setAttribute("wronglogin", true);
        verifyNoMoreInteractions(request, response, userService);
    }

    @Test
    public void execute_NullLogin_ReturnToLogin() throws Exception {
        //init
        when(request.getParameter("login")).thenReturn(null);
        when(request.getParameter("password")).thenReturn("test_password");

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("login"));
        verify(request).getParameter("login");
        verify(request).getParameter("password");
        verify(request).login(null, "test_password");
        verify(request).setAttribute("wronglogin", true);
        verifyNoMoreInteractions(request, response, userService);
    }

    @Test
    public void execute_NullPassword_ReturnToLogin() throws Exception {
        //init
        when(request.getParameter("login")).thenReturn("test_login");
        when(request.getParameter("password")).thenReturn(null);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("login"));
        verify(request).getParameter("login");
        verify(request).getParameter("password");
        verify(request).login("test_login", null);
        verify(request).setAttribute("wronglogin", true);
        verifyNoMoreInteractions(request, response, userService);
    }
}