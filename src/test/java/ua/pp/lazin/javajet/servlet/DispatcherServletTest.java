package ua.pp.lazin.javajet.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.pp.lazin.javajet.command.main.MainCommandGET;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * @author Ruslan Lazin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommandResolver.class)
public class DispatcherServletTest {
//    @Mock
//    private ServletConfig servletConfig;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private MainCommandGET mainCommandGET;

    @Mock
    private HttpSession session;

    @InjectMocks
    private DispatcherServlet underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new DispatcherServlet();
        given(request.getSession()).willReturn(session);
        given(request.getRequestDispatcher(any())).willReturn(mock(RequestDispatcher.class));
    }


    @Test
    public void testSingleMethodMapping() throws ServletException, IOException {
        PowerMockito.spy(CommandResolver.class);
        mockStatic(CommandResolver.class);
        given(CommandResolver.getCommand(any())).willReturn(mainCommandGET);
        given(request.getMethod()).willReturn("GET");
        given(request.getRequestURI()).willReturn("/login");
        given(request.getContextPath()).willReturn("");
        given(mainCommandGET.execute(request,response)).willReturn("main");


//        dispatcher.addMapping("/path1/path2", HttpMethod.GET.mask, controller);
//        underTest.doGet(request, response);
//        PowerMockito.verifyStatic();
//        verify(CommandResolver.getCommand(request), never() );
//        verify(controller, never()).post(any());
    }


    @Test
    public void init() throws Exception {

    }

    @Test
    public void doGet() throws Exception {

    }

    @Test
    public void doPost() throws Exception {

    }

}