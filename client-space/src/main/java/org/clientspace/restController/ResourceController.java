package org.clientspace.restController;


import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Because this application is also a User Info Resource Server, we expose info about the logged in user at:
 *
 *     http://localhost:9090/auth/user
 */
@RestController
public class ResourceController {

    @RequestMapping("/user")
   // @PreAuthorize("hasAnyRole( 'USER')")
    public Principal user(Principal user) {
      return user;
    }
}