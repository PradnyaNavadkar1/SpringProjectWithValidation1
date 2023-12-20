package com.javguides.springBootrestfulwebservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @Schema(description="User First Name")
    @NotEmpty(message="user firstname  should not be null or empty")
    private String firstName;
    @Schema(description="User last Name")
    @NotEmpty(message="user lastname  should not be null or empty")
    private String lastName;
    @Schema(description="User email")
    @NotEmpty(message="user email  should not be null or empty")
    @Email(message="user email  should be valid")
    private String email;





}
