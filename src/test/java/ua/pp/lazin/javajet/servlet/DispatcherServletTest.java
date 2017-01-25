package ua.pp.lazin.javajet.servlet;

import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.pp.lazin.javajet.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Ruslan Lazin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommandResolver.class)
public class DispatcherServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private Command command;

    @InjectMocks
    private DispatcherServlet underTest;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(new NullAppender());
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(any())).thenReturn(requestDispatcher);
        PowerMockito.mockStatic(CommandResolver.class);
        PowerMockito.when(CommandResolver.getCommand(request)).thenReturn(command);
        when(request.getContextPath()).thenReturn("");
    }

    @Test
    public void doGet_CorrectPath_Forward() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("test_viewName");
        //use
        underTest.doGet(request, response);
        //check
        verify(command).execute(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/pages/test_viewName.jsp");
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test
    public void doGet_CorrectPath_Redirect() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("redirect:/path");
        //use
        underTest.doGet(request, response);
        //check
        verify(command).execute(request, response);
        verify(request).getContextPath();
        verify(response).sendRedirect("/path");
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test
    public void doGet_IncorrectPath_ForwardToErrorPage() throws ServletException, IOException {
        //init
        PowerMockito.when(CommandResolver.getCommand(request)).thenReturn(null);
        //use
        underTest.doGet(request, response);
        //check
        verify(request).getRequestDispatcher("/WEB-INF/pages/errors/error.jsp");
        verify(request).getRequestURI();
        verify(request).getMethod();
        verify(request).setAttribute(eq("message"), anyString());
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test
    public void doGet_ExceptionInCommand_ForwardToErrorPage() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenThrow(new RuntimeException("test exception in command"));
        //use
        underTest.doGet(request, response);
        //check
        verify(command).execute(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/pages/errors/error.jsp");
        verify(request).setAttribute(eq("message"), anyString());
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test(expected = IOException.class)
    public void doGet_ExceptionWhenForward_ThrowsException() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("test_viewName");
        doThrow(IOException.class).when(requestDispatcher).forward(request, response);
        //use
        underTest.doGet(request, response);
    }

    @Test
    public void doGet_ExceptionWhenRedirect_ForwardToErrorPage() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("redirect:/path");
        doThrow(IOException.class).when(response).sendRedirect("/path");
        //use
        underTest.doGet(request, response);
        //check
        verify(command).execute(request, response);
        verify(response).sendRedirect("/path");
        verify(request).getRequestDispatcher("/WEB-INF/pages/errors/error.jsp");
        verify(request).getContextPath();
        verify(request).setAttribute(eq("message"), anyString());
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test
    public void doPost_CorrectPath_Forward() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("test_viewName");
        //use
        underTest.doPost(request, response);
        //check
        verify(command).execute(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/pages/test_viewName.jsp");
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test
    public void doPost_CorrectPath_Redirect() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("redirect:/path");
        //use
        underTest.doPost(request, response);
        //check
        verify(command).execute(request, response);
        verify(request).getContextPath();
        verify(response).sendRedirect("/path");
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test
    public void doPost_IncorrectPath_ForwardToErrorPage() throws ServletException, IOException {
        //init
        PowerMockito.when(CommandResolver.getCommand(request)).thenReturn(null);
        //use
        underTest.doPost(request, response);
        //check
        verify(request).getRequestDispatcher("/WEB-INF/pages/errors/error.jsp");
        verify(request).getRequestURI();
        verify(request).getMethod();
        verify(request).setAttribute(eq("message"), anyString());
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test
    public void doPost_ExceptionInCommand_ForwardToErrorPage() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenThrow(new RuntimeException("test exception in command"));
        //use
        underTest.doPost(request, response);
        //check
        verify(command).execute(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/pages/errors/error.jsp");
        verify(request).setAttribute(eq("message"), anyString());
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }

    @Test(expected = IOException.class)
    public void doPost_ExceptionWhenForward_ThrowsException() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("test_viewName");
        doThrow(IOException.class).when(requestDispatcher).forward(request, response);
        //use
        underTest.doPost(request, response);
    }

    @Test
    public void doPost_ExceptionWhenRedirect_ForwardToErrorPage() throws ServletException, IOException {
        //init
        when(command.execute(request, response)).thenReturn("redirect:/path");
        doThrow(IOException.class).when(response).sendRedirect("/path");
        //use
        underTest.doPost(request, response);
        //check
        verify(command).execute(request, response);
        verify(response).sendRedirect("/path");
        verify(request).getRequestDispatcher("/WEB-INF/pages/errors/error.jsp");
        verify(request).getContextPath();
        verify(request).setAttribute(eq("message"), anyString());
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response, session, command, requestDispatcher);
    }
}