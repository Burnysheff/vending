package edu.paper.vending.controller;

import edu.paper.vending.dto.LoginForm;
import edu.paper.vending.dto.RegistrationForm;
import edu.paper.vending.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    AuthenticationManager authenticationManager;
    UserService userService;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/")
    @ApiOperation(value = "Registration for new user")
    public void auth(@RequestBody RegistrationForm form, HttpServletRequest request) {
        if (!userService.checkNames(form.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Name taken already!"
            );
        }
        userService.addUser(form);
        this.authenticateUserAndSetSession(form.getUsername(), form.getPassword(), request);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "Logging out")
    public void auth(HttpServletRequest request) throws ServletException {
        request.logout();
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping("/login")
    @ApiOperation(value = "Logging in")
    public void login(@RequestBody LoginForm form, HttpServletRequest request) {
        if (!userService.checkUser(form)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad credentials!"
            );
        } else {
            this.authenticateUserAndSetSession(userService.nameByEmail(form.getEmail()), form.getPassword(), request);
        }
    }

    @GetMapping("/current")
    @ApiOperation(value = "Getting current user")
    public Object user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Adding favourite themes")
    public void getUser(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User current = (User)auth.getPrincipal();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Adding favourite themes")
    public void modifyUser(@PathVariable Long id, @RequestParam UserForm userForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User current = (User)auth.getPrincipal();
        userService.modifyUser(id, userForm);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Adding favourite themes")
    public void deleteUser(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User current = (User)auth.getPrincipal();
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/history")
    @ApiOperation(value = "Adding favourite themes")
    public List<Operation> getHistory(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getHistory();
    }

    @GetMapping("/{id}/history")
    @ApiOperation(value = "Adding favourite themes")
    public Bucket getBucket(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getBucket();
    }

    @PostMapping("/{id}/history")
    @ApiOperation(value = "Adding favourite themes")
    public void getBucket(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.setBucket();
    }

    private void authenticateUserAndSetSession(String username, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
