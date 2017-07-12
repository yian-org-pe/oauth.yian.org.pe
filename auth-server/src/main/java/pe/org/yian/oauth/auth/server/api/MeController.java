package pe.org.yian.oauth.auth.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.auth.data.dto.request.PasswordResetRequest;
import pe.org.yian.oauth.auth.server.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by jaxkodex on 30/04/17.
 */
@RestController
@RequestMapping(value = "/api/me")
public class MeController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public MeController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("#oauth2.hasScope('read') and hasRole('USER')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity me(Principal principal) {
        String username = principal.getName();
        LOGGER.trace("Cargando usuario {}", username);
        UserDto user = userService.load(username);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("#oauth2.hasScope('write') and hasRole('USER')")
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public ResponseEntity resetPassword(@RequestBody PasswordResetRequest passwordResetRequest,
                                        Principal principal) {
        String username = principal.getName();
        userService.changePassword(username, passwordResetRequest.getNewPassword());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity logout(HttpServletRequest request) {
        try {
            request.logout();
        } catch (ServletException e) {
            LOGGER.error("Error logout", e);
        }
        return ResponseEntity.ok().build();
    }
}
