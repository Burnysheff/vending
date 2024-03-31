package edu.paper.vending.controller;

import edu.paper.vending.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    OpeationService opeationService;

    UserService userService;

    public OperationController(OpeationService opeationService, UserService userService) {
        this.opeationService = opeationService;
        this.userService = userService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get comments by guide")
    public List<Operation> getOperations() {
        return operationService.get();
    }

    @GetMapping("/{id}/code")
    @ApiOperation(value = "Get comments by guide")
    public void getOperations(@PathVariable Long id) {
        return operationService.getCode(id);
    }

    @GetMapping("/{id}/check")
    @ApiOperation(value = "Get comments by guide")
    public void getOperations(@PathVariable Long id) {
        return operationService.gteCheck(id);
    }
}
