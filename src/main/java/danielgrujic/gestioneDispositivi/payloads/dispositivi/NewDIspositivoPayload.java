package danielgrujic.gestioneDispositivi.payloads.dispositivi;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record NewDIspositivoPayload(


        @NotNull(message = "Inserisci il tipo di dispositivo(Cell, Laptop, Desktop...")
        String tipo,
        @NotNull(message = "Disponibilità dell dispositivo(disponibile, assegnato, in manutenzione, dismesso)")
        String disponibile,
        UUID utenteId
) {}// {    public UUID getUtenteId() {

       // return utenteId != null ? utenteId : ;
                // qua ci ho provato qualcosa che sembra che non va } }
