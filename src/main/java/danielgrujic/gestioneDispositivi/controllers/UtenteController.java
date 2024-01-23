package danielgrujic.gestioneDispositivi.controllers;

import danielgrujic.gestioneDispositivi.entities.Utente;
import danielgrujic.gestioneDispositivi.exceptions.BadRequestException;
import danielgrujic.gestioneDispositivi.payloads.utenti.NewUtenteDTO;
import danielgrujic.gestioneDispositivi.payloads.utenti.NewUtenteResponseDTO;
import danielgrujic.gestioneDispositivi.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    UtenteService utenteService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteResponseDTO saveUtente(@RequestBody @Validated NewUtenteDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Utente newUtente = utenteService.save(body);
        return new NewUtenteResponseDTO(newUtente.getId());
    }

    @GetMapping("")
    public Page<Utente> getUtente(@RequestParam(defaultValue = "0") int pagina,
                                   @RequestParam(defaultValue = "10") int elementi, @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.getUtenti(pagina, elementi, sortBy);
    }

    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable UUID utenteId) {
        return utenteService.findById(utenteId);
    }

    @PutMapping("/{utenteId}")
    public Utente findAndUpdate(@PathVariable UUID utenteId, @RequestBody Utente body) {
        return utenteService.findByIdAndUpdate(utenteId, body);
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //
    public void findAndDelete(@PathVariable UUID utenteId) {
        utenteService.findByIdAndDelete(utenteId);
    }


    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentUser) {
        return currentUser;
    }


    @PutMapping("/me")
    public Utente getMeAndUpdate(@AuthenticationPrincipal Utente currentUser, @RequestBody Utente body) {
        return utenteService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    public void getMeAnDelete(@AuthenticationPrincipal Utente currentUser) {
        utenteService.findByIdAndDelete(currentUser.getId());
    }


}
