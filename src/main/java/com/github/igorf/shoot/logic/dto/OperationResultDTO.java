package com.github.igorf.shoot.logic.dto;

import lombok.Data;

@Data
public class OperationResultDTO {
    private String result;

    private OperationResultDTO(String result) {
        this.result = result;
    }

    public static OperationResultDTO success() {
        return new OperationResultDTO("OK");
    }

    public static OperationResultDTO error() {
        return new OperationResultDTO("ERROR");
    }
}
