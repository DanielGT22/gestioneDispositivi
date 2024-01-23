package danielgrujic.gestioneDispositivi.controllers;

import danielgrujic.gestioneDispositivi.entities.Utente;
import danielgrujic.gestioneDispositivi.exceptions.BadRequestException;
import danielgrujic.gestioneDispositivi.payloads.login.UtenteLogin;
import danielgrujic.gestioneDispositivi.payloads.login.UtenteLoginResponse;
import danielgrujic.gestioneDispositivi.payloads.utenti.NewUtenteDTO;
import danielgrujic.gestioneDispositivi.payloads.utenti.NewUtenteResponseDTO;
import danielgrujic.gestioneDispositivi.services.AuthService;
import danielgrujic.gestioneDispositivi.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginResponse login(@RequestBody UtenteLogin body) {
        String accessToken = authService.authenticateUser(body);
        return new UtenteLoginResponse(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUtenteDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            Utente newUtente = utenteService.save(newUserPayload);

            return new NewUtenteResponseDTO(newUtente.getId());
        }
    }
}