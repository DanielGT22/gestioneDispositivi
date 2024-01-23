package danielgrujic.gestioneDispositivi.services;

import danielgrujic.gestioneDispositivi.entities.Dispositivo;
import danielgrujic.gestioneDispositivi.entities.Utente;
import danielgrujic.gestioneDispositivi.exceptions.BadRequestException;
import danielgrujic.gestioneDispositivi.exceptions.NotFoundException;
import danielgrujic.gestioneDispositivi.payloads.dispositivi.NewDIspositivoPayload;
import danielgrujic.gestioneDispositivi.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DispositivoService {
    @Autowired
    DispositivoRepository dispositivoRepository;

    @Autowired
    UtenteService utenteService;


    public Dispositivo save (NewDIspositivoPayload body){

       /* Utente utente;

        if (body.utenteId() != null) {
            utente = utenteService.findById(body.utenteId());
        }
        else {
            utente = new Utente();
            utente.setId("0");
        } */

      Utente utente = utenteService.findById(body.utenteId());


        Dispositivo nuovoDispositivo = new Dispositivo();
        nuovoDispositivo.setTipo(body.tipo());
        nuovoDispositivo.setDisponibile(body.disponibile());
        nuovoDispositivo.setUtente(utente);

        return dispositivoRepository.save(nuovoDispositivo);


    }
    public Dispositivo findById(UUID id ){
        return dispositivoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));


    }

    public void findByIdAndDelete(UUID id) {
        Dispositivo found = this.findById(id);
        dispositivoRepository.delete(found);
    }

    public Dispositivo findByIdAndUpdate(UUID id, NewDIspositivoPayload body) {
        Dispositivo trovato = this.findById(id);

        trovato.setDisponibile(body.disponibile());
        /* if (body.utenteId() == null) {
            trovato.getUtente().setId(0);
        } */
        if (trovato.getUtente().getId() != body.utenteId()) {
                Utente newUtente = utenteService.findById(body.utenteId());
                trovato.setUtente(newUtente);
            }



        return dispositivoRepository.save(trovato);
    }

    public List<Dispositivo> findByUtente(UUID utenteId){
        Utente utente = utenteService.findById(utenteId);
        return dispositivoRepository.findByUtente(utente);
    }
    public List<Dispositivo> getDispositivo() {
        return dispositivoRepository.findAll();
    }


}
