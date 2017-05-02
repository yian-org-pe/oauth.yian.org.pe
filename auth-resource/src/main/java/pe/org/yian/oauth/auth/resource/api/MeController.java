package pe.org.yian.oauth.auth.resource.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.client.service.MeService;

/**
 * Created by jaxkodex on 01/05/17.
 */
@RestController
@RequestMapping("/api/me")
public class MeController {
    private MeService meService;

    @Autowired
    public MeController(MeService meService) {
        this.meService = meService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity me () {
        UserDto u = meService.me();
        return ResponseEntity.ok(u);
    }
}
