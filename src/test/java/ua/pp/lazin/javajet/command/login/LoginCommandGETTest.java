package ua.pp.lazin.javajet.command.login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Ruslan Lazin
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginCommandGETTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private LoginCommandGET underTest;

    @Test
    public void execute_OK_ViewName() throws Exception {

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("login"));
        verifyNoMoreInteractions(request, response);
    }

}