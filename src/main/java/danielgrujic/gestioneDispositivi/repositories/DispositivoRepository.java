package danielgrujic.gestioneDispositivi.repositories;

import danielgrujic.gestioneDispositivi.entities.Dispositivo;
import danielgrujic.gestioneDispositivi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {
    List<Dispositivo> findByUtente(Utente utente);
}