package ua.pp.lazin.javajet.command.aircraft;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.lazin.javajet.entity.Aircraft;
import ua.pp.lazin.javajet.service.AircraftService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Ruslan Lazin
 */
@RunWith(MockitoJUnitRunner.class)
public class AircraftsCommandGETTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    AircraftService aircraftService;

    @InjectMocks
    private AircraftsCommandGET underTest;

    @Mock
    private List<Aircraft> aircrafts;

    @Before
    public void setUp() throws Exception {
        when(aircraftService.findAll()).thenReturn(aircrafts);
    }

    @Test
    public void execute() throws Exception {

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("aircrafts"));
        verify(aircraftService).findAll();
        verify(request).setAttribute("aircrafts", aircrafts);
        verifyNoMoreInteractions(request, response, aircraftService);
    }

}