package br.com.zup.AlysEstacionamento.controller;

import br.com.zup.AlysEstacionamento.controller.dtos.PlacaDto;
import br.com.zup.AlysEstacionamento.controller.dtos.CarroDto;
import br.com.zup.AlysEstacionamento.controller.dtos.MotoDto;
import br.com.zup.AlysEstacionamento.service.EstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @GetMapping("/carros")
    public ResponseEntity<?> exibirEstacionamentoCarros() {
        return ResponseEntity.ok(estacionamentoService.getESTACIONAMENTO_CARROS());
    }

    @GetMapping("/motos")
    public ResponseEntity<?> exibirEstacionamentoMotos() {
        return ResponseEntity.ok(estacionamentoService.getESTACIONAMENTO_MOTOS());
    }


    @PostMapping("/carros")
    public ResponseEntity<?> cadastrarCarro(@RequestBody @Valid PlacaDto placa) {
        try {
            CarroDto carroDTO = estacionamentoService.salvarCarro(placa.getPlaca());
            return ResponseEntity.status(201).body(carroDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("mensagem", e.getMessage()));
        }
    }


    @PostMapping("/motos")
    public ResponseEntity<?> cadastrarMoto(@RequestBody @Valid PlacaDto placa) {
        try {
            MotoDto motoDTO = estacionamentoService.salvarMoto(placa.getPlaca());
            return ResponseEntity.status(201).body(motoDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("mensagem", e.getMessage()));
        }
    }


    @PostMapping("/carros/{placa}/saida")
    public ResponseEntity<?> calcularTarifaCarro(@PathVariable String placa) {
        try {
            LocalDateTime horaSaida = LocalDateTime.now();
            double tarifa = estacionamentoService.calcularTarifaCarro(placa, horaSaida);
            return ResponseEntity.ok(Map.of("placa", placa, "tarifa", tarifa));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("mensagem", e.getMessage()));
        }
    }


    @PostMapping("/motos/{placa}/saida")
    public ResponseEntity<?> calcularTarifaMoto(@PathVariable String placa) {
        try {
            LocalDateTime horaSaida = LocalDateTime.now();
            double tarifa = estacionamentoService.calcularTarifaMoto(placa, horaSaida);
            return ResponseEntity.ok(Map.of("placa", placa, "tarifa", tarifa));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("mensagem", e.getMessage()));
        }
    }
}
