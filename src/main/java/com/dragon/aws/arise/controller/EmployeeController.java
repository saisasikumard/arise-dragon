package com.dragon.aws.arise.controller;

import com.dragon.aws.arise.entity.dto.EmployeeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee", description = "Employee management")
public class EmployeeController {
    @GetMapping("/greet")
    @Operation(
            summary = "Greet API",
            description = "Api returns Greeting message",
            operationId = "greetApi"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200",description = "Success message",content = @Content)
            }
    )
    public ResponseEntity<?> greet(){
        return ResponseEntity.status(200).body(new EmployeeResponseDto("Hello Employee, Welcome to Portal..Test_Deployment2"));
    }
}
