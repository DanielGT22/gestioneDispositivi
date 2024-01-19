package danielgrujic.gestioneDispositivi.controllers;

import danielgrujic.gestioneDispositivi.entities.Dispositivo;
import danielgrujic.gestioneDispositivi.entities.Utente;
import danielgrujic.gestioneDispositivi.payloads.dispositivi.NewDIspositivoPayload;
import danielgrujic.gestioneDispositivi.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    DispositivoService dispositivoService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo saveDispositivo(@RequestBody @Validated NewDIspositivoPayload body)  {
     return dispositivoService.save(body);
    }

    @GetMapping("")
    public List<Dispositivo> getDispositivo(Integer dispositivoId) {
        return dispositivoService.getDispositivo();
    }

    @GetMapping("/{dispositivoId}")
    public Dispositivo findById(@PathVariable int dispositivoId) throws Exception {
        return dispositivoService.findById(dispositivoId);
    }

    @PutMapping("/{dispositivoId}")
    public Dispositivo findAndUpdate(@PathVariable int dispositivoId, @RequestBody NewDIspositivoPayload body) throws Exception {
        return dispositivoService.findByIdAndUpdate(dispositivoId, body);
    }


    @DeleteMapping("/{dispositivoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int dispositivoId) {
        dispositivoService.findByIdAndDelete(dispositivoId);
    }

}
