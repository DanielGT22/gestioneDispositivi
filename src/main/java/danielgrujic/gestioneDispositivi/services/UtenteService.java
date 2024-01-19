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

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(Utente body) throws IOException {
        utenteRepository.findByUsername(body.getUsername()).ifPresent( user -> {
            throw new BadRequestException("Il username " +  body.getUsername() + "è già associato a un altro utente");
        });
        return utenteRepository.save(body);

    }

    public Page<Utente> getUtenti(int pagina, int elementi, String sortBy) {
        Pageable pageable = PageRequest.of(pagina, elementi, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findById(int id ){
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public void findByIdAndDelete(int id) {
        Utente trovato = this.findById(id);
        utenteRepository.delete(trovato);
    }

    public Utente findByIdAndUpdate(int id, Utente body) {
        Utente trovato = this.findById(id);
        trovato.setNome(body.getNome());
        trovato.setCognome(body.getCognome());
        trovato.setEmail(body.getEmail());
        trovato.setUsername(body.getUsername());
        return utenteRepository.save(trovato);
    }

}
