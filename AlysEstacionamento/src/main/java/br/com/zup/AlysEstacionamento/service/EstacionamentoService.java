package br.com.zup.AlysEstacionamento.service;

import br.com.zup.AlysEstacionamento.controller.dtos.CarroDto;
import br.com.zup.AlysEstacionamento.controller.dtos.MotoDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {
    private final List<CarroDto> ESTACIONAMENTO_CARROS = new ArrayList<>();
    private final List<MotoDto> ESTACIONAMENTO_MOTOS = new ArrayList<>();


    public Optional<CarroDto> buscarVeiculo(String placa) {
        return ESTACIONAMENTO_CARROS.stream()
                .filter(carroDTO -> carroDTO.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
    }

    public CarroDto salvarCarro(String placa) {
        Optional<CarroDto> carroDTOOptional = buscarVeiculo(placa);

        if (carroDTOOptional.isPresent()) {
            throw new RuntimeException("Carro já cadastrado");
        }

        CarroDto carroDTO = new CarroDto();
        carroDTO.setPlaca(placa);
        carroDTO.setHoraEntrada(LocalDateTime.now());
        ESTACIONAMENTO_CARROS.add(carroDTO);

        return carroDTO;
    }

    public List<CarroDto> getESTACIONAMENTO_CARROS() {
        return new ArrayList<>(ESTACIONAMENTO_CARROS);  // Retorna uma cópia para evitar mutações externas
    }


    public Optional<MotoDto> buscarMoto(String placa) {
        return ESTACIONAMENTO_MOTOS.stream()
                .filter(motoDTO -> motoDTO.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
    }

    public MotoDto salvarMoto(String placa) {
        Optional<MotoDto> motoDTOOptional = buscarMoto(placa);

        if (motoDTOOptional.isPresent()) {
            throw new RuntimeException("Moto já cadastrada");
        }

        MotoDto motoDTO = new MotoDto();
        motoDTO.setPlaca(placa);
        motoDTO.setHoraEntrada(LocalDateTime.now());
        ESTACIONAMENTO_MOTOS.add(motoDTO);

        return motoDTO;
    }

    public List<MotoDto> getESTACIONAMENTO_MOTOS() {
        return new ArrayList<>(ESTACIONAMENTO_MOTOS);
    }

    // Cálculo de Tarifa
    public double calcularTarifaMoto(String placa, LocalDateTime horaSaida) {
        MotoDto motoDTO = buscarMoto(placa).orElseThrow(() ->
                new RuntimeException("Moto não encontrada")
        );

        motoDTO.setHoraSaida(horaSaida);
        return motoDTO.calcularTarifa();
    }

    public double calcularTarifaCarro(String placa, LocalDateTime horaSaida) {
        CarroDto carroDTO = buscarVeiculo(placa).orElseThrow(() ->
                new RuntimeException("Carro não encontrado")
        );

        carroDTO.setHoraSaida(horaSaida);
        return carroDTO.calcularTarifa();
    }
}
