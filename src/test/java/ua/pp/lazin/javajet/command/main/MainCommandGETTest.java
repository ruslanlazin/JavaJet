package ua.pp.lazin.javajet.command.main;

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
public class MainCommandGETTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    private MainCommandGET underTest;

    @Test
    public void execute_Ok_viewName() throws Exception {

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("main"));
        verifyNoMoreInteractions(request, response);
    }
}