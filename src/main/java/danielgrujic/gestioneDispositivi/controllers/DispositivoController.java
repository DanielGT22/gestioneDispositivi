package danielgrujic.gestioneDispositivi.controllers;

import danielgrujic.gestioneDispositivi.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    DispositivoService dispositivoService;
}
