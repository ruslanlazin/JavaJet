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
public class AircraftsCommandPOSTTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    private AircraftService aircraftService;

    @Mock
    private List<Aircraft> aircrafts;

    @InjectMocks
    private AircraftsCommandPOST underTest;
    private Aircraft aircraft;

    @Before
    public void setUp() throws Exception {
        aircraft = Aircraft.newBuilder().model("AIRBUS").regNumber("JJ-6009").build();
        when(request.getParameter("model")).thenReturn("AIRBUS");
        when(request.getParameter("regNumber")).thenReturn("JJ-6009");
        when(aircraftService.findAll()).thenReturn(aircrafts);
        when(aircraftService.create(aircraft)).thenReturn(1L);
    }

    @Test
    public void execute_NullAircraftId_Create() throws Exception {
        //init
        when(request.getParameter("aircraftId")).thenReturn(null);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("aircrafts"));
        verify(request).getParameter("model");
        verify(request).getParameter("aircraftId");
        verify(request).getParameter("regNumber");
        verify(aircraftService).create(aircraft);
        verify(aircraftService).findAll();
        verify(request).setAttribute("aircrafts", aircrafts);
        verifyNoMoreInteractions(request, response, aircraftService);
    }

    @Test
    public void execute_EmptyAircraftId_Create() throws Exception {
        //init
        when(request.getParameter("aircraftId")).thenReturn("");

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("aircrafts"));
        verify(request).getParameter("model");
        verify(request).getParameter("aircraftId");
        verify(request).getParameter("regNumber");
        verify(aircraftService).create(aircraft);
        verify(aircraftService).findAll();
        verify(request).setAttribute("aircrafts", aircrafts);
        verifyNoMoreInteractions(request, response, aircraftService);
    }

    @Test
    public void execute_CorrectAircraftId_Update() throws Exception {
        //init
        when(request.getParameter("aircraftId")).thenReturn("123");
        aircraft.setId(123L);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("aircrafts"));
        verify(request).getParameter("model");
        verify(request).getParameter("aircraftId");
        verify(request).getParameter("regNumber");
        verify(aircraftService).update(aircraft);
        verify(aircraftService).findAll();
        verify(request).setAttribute("aircrafts", aircrafts);
        verifyNoMoreInteractions(request, response, aircraftService);
    }

    @Test(expected = NumberFormatException.class)
    public void execute_IncorrectAircraftId_ExceptionThrown() throws Exception {
        //init
        when(request.getParameter("aircraftId")).thenReturn("bill");

        //use
        underTest.execute(request, response);
    }
}