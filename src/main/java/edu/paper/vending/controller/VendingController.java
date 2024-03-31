package edu.paper.vending.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vending")
public class VendingController {
    VendingService vendingService;

    public UserController(VendingService vendingService) {
        this.vendingService = vendingService;
        this.authenticationManager = authenticationManager;
    }

    /**@GetMapping("/current")
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
    }*/
}
