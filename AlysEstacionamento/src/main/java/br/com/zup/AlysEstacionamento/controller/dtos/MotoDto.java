package br.com.zup.AlysEstacionamento.controller.dtos;

import java.time.LocalDateTime;

public class MotoDto {
    private String placa;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double calcularTarifa() {
        if (horaSaida == null) {
            throw new IllegalArgumentException("Hora de saída não registrada");
        }

        long horas = java.time.Duration.between(horaEntrada, horaSaida).toHours();
        double tarifaPorHora = 2.50;
        return horas * tarifaPorHora;
    }
}
