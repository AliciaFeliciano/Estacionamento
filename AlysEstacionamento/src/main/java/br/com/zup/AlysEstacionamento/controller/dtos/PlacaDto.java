package br.com.zup.AlysEstacionamento.controller.dtos;

import jakarta.validation.constraints.NotBlank;

public class PlacaDto {

    @NotBlank(message = "A placa não pode estar em branco")
    private String placa;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
