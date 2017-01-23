package ua.pp.lazin.javajet.command.login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.lazin.javajet.jaas.UserPrincipal;
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
public class LogoutCommandGETTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private LogoutCommandGET underTest;

    @Test
    public void execute_CorrectLogout_RemoveFromSessionRedirect() throws Exception {
        //init
        when(request.getSession()).thenReturn(session);
        when(request.getUserPrincipal()).thenReturn(new UserPrincipal("TEST"));

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("redirect:/login"));
        verify(request).logout();
        verify(request).getSession();
        verify(request).getUserPrincipal();
        verify(userService).removeCurrentUserFromSession(session);
        verifyNoMoreInteractions(request, response, userService);
    }

    @Test
    public void execute_IncorrectLogout_Redirect() throws Exception {
        //init
        when(request.getSession()).thenReturn(session);
        when(request.getUserPrincipal()).thenReturn(new UserPrincipal("TEST"));
        doThrow(ServletException.class).when(request).logout();

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("redirect:/login"));
        verify(request).logout();
        verify(request).getUserPrincipal();
        verifyNoMoreInteractions(request, response, userService);
    }
}