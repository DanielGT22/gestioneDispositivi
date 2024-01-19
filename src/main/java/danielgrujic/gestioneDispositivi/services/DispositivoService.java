package danielgrujic.gestioneDispositivi.services;

import danielgrujic.gestioneDispositivi.entities.Dispositivo;
import danielgrujic.gestioneDispositivi.entities.Utente;
import danielgrujic.gestioneDispositivi.exceptions.NotFoundException;
import danielgrujic.gestioneDispositivi.payloads.dispositivi.NewDIspositivoPayload;
import danielgrujic.gestioneDispositivi.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {
    @Autowired
    DispositivoRepository dispositivoRepository;

    @Autowired
    UtenteService utenteService;


    public Dispositivo save (NewDIspositivoPayload body){
        Utente utente = utenteService.findById(body.utenteId());
        Dispositivo nuovoDispositivo = new Dispositivo();
        nuovoDispositivo.setTipo(body.tipo());
        nuovoDispositivo.setDisponibile(body.disponibile());
        nuovoDispositivo.setUtente(utente);
        return dispositivoRepository.save(nuovoDispositivo);


    }
    public Dispositivo findById(int id ){
        return dispositivoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));


    }

    public void findByIdAndDelete(int id) {
        Dispositivo found = this.findById(id);
        dispositivoRepository.delete(found);
    }

    public Dispositivo findByIdAndUpdate(int id, NewDIspositivoPayload body) {
        Dispositivo trovato = this.findById(id);

        trovato.setDisponibile(body.disponibile());
        if(trovato.getUtente().getId()!= body.utenteId()) {
            Utente newUtente = utenteService.findById(body.utenteId());
            trovato.setUtente(newUtente);
        }



        return dispositivoRepository.save(trovato);
    }

    public List<Dispositivo> findByUtente(int utenteId){
        Utente utente = utenteService.findById(utenteId);
        return dispositivoRepository.findByUtente(utente);
    }
    public List<Dispositivo> getDispositivo() {
        return dispositivoRepository.findAll();
    }


}
