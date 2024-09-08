package com.study.spring6reactive.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotBlank
    @Size(min = 3, max = 255)
    private String customerName;
    @Size(min=5, max = 50)
    private String email;
}
