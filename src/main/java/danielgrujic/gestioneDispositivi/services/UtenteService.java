package danielgrujic.gestioneDispositivi.services;

import danielgrujic.gestioneDispositivi.entities.Utente;
import danielgrujic.gestioneDispositivi.exceptions.BadRequestException;
import danielgrujic.gestioneDispositivi.exceptions.NotFoundException;
import danielgrujic.gestioneDispositivi.payloads.utenti.NewUtenteDTO;
import danielgrujic.gestioneDispositivi.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(NewUtenteDTO body) throws IOException {
        utenteRepository.findByUsername(body.username()).ifPresent( user -> {
            throw new BadRequestException("Il username " +  body.username() + "è già associato ad un altro utente");
        });
        utenteRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("La mail  " +  body.email() + "è già associato ad un altro utente");
        });
        Utente newUtente = new Utente();
        newUtente.setNome(body.nome());
        newUtente.setCognome(body.cognome());
        newUtente.setEmail(body.email());
        newUtente.setUsername(body.username());
        newUtente.setPassword(body.password());
        return utenteRepository.save(newUtente);

    }

    public Page<Utente> getUtenti(int pagina, int elementi, String sortBy) {
        Pageable pageable = PageRequest.of(pagina, elementi, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findById(UUID id ){
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public void findByIdAndDelete(UUID id) {
        Utente trovato = this.findById(id);
        utenteRepository.delete(trovato);
    }

    public Utente findByIdAndUpdate(UUID id, Utente body) {
        Utente trovato = this.findById(id);
        trovato.setNome(body.getNome());
        trovato.setCognome(body.getCognome());
        trovato.setEmail(body.getEmail());
        trovato.setUsername(body.getUsername());
        return utenteRepository.save(trovato);
    }
    public Utente findByEmail(String email) throws NotFoundException {
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }
}
