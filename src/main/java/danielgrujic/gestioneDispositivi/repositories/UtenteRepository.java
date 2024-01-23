package danielgrujic.gestioneDispositivi.repositories;

import danielgrujic.gestioneDispositivi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByUsername(String username);
    Optional<Utente> findByEmail(String email);
}