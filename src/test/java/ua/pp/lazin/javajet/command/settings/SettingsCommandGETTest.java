package ua.pp.lazin.javajet.command.settings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.lazin.javajet.entity.Position;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.service.PositionService;
import ua.pp.lazin.javajet.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Ruslan Lazin
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingsCommandGETTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PositionService positionService;

    @Mock
    private RoleService roleService;

    @Mock
    private List<Role> roles;

    @Mock
    private List<Position> positions;

    @InjectMocks
    private SettingsCommandGET underTest;

    @Test
    public void execute_Ok_ViewName() throws Exception {
        //init
        when(roleService.findAll()).thenReturn(roles);
        when(positionService.findAll()).thenReturn(positions);

        //use
        String viewName = underTest.execute(request, response);

        //check
        assertTrue(viewName.equals("settings"));
        verify(roleService).findAll();
        verify(positionService).findAll();
        verify(request).setAttribute("roles", roles);
        verify(request).setAttribute("positions", positions);
        verifyNoMoreInteractions(request, response, roleService, positionService);
    }

}