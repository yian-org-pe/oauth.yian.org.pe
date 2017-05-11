package pe.org.yian.oauth.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;
import pe.org.yian.oauth.auth.data.dto.UserDto;
import pe.org.yian.oauth.client.service.MeService;

/**
 * Created by jaxkodex on 01/05/17.
 */
//@Service
public class MeServiceImpl implements MeService {
    private OAuth2RestOperations restTemplate;
    private static final String URL = "http://localhost:8081/api/me";

    //@Autowired
    public MeServiceImpl(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto me() {
        return restTemplate.getForObject(URL, UserDto.class);
    }
}
