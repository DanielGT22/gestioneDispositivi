package danielgrujic.gestioneDispositivi.services;

import danielgrujic.gestioneDispositivi.entities.Utente;
import danielgrujic.gestioneDispositivi.exceptions.UnauthorizedException;
import danielgrujic.gestioneDispositivi.payloads.login.UtenteLogin;
import danielgrujic.gestioneDispositivi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UtenteLogin body) {
        Utente utente = utenteService.findByEmail(body.email());
        if (body.password().equals(utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}