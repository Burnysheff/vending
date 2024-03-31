package edu.paper.vending.controller;

import edu.paper.vending.service.VendingService;
import edu.paper.vending.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/Guide")
public class ProductController {
    VendingService vendingService;
    UserService userService;

    public ProductController(VendingService vendingService, UserService userService) {
        this.vendingService = vendingService;
        this.userService = userService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Guide by id")
    public Guide guideId(Long id) {
        return vendingService.searchById(id);
    }

    @GetMapping("/checker")
    @ApiOperation(value = "Checks guide by id")
    public void checker(Long id, List<String> preview, String text) {
        if (vendingService.check(id, preview, text)) {
            throw new ResponseStatusException(
                    HttpStatus.OK, "ok"
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "Creates guide")
    public void creator(@RequestBody GuideForm form) {
        vendingService.save(form);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Deletes guide")
    public void delete(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByName(String.valueOf(auth.getPrincipal()));

        if (!vendingService.searchById(id).getUser().equals(user)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Not user's guide!"
            );
        } else {
            if (vendingService.deleteGuide(id)) {
                throw new ResponseStatusException(
                        HttpStatus.OK, "ok"
                );
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "No such guide!"
                );
            }
        }
    }
}
