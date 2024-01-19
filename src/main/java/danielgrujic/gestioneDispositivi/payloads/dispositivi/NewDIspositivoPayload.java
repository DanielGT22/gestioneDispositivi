package danielgrujic.gestioneDispositivi.payloads.dispositivi;

import jakarta.validation.constraints.NotNull;


public record NewDIspositivoPayload(


        @NotNull(message = "Inserisci il tipo di dispositivo(Cell, Laptop, Desktop...")
        String tipo,
        @NotNull(message = "Disponibilità dell dispositivo(disponibile, assegnato, in manutenzione, dismesso)")
        String disponibile,
        Integer utenteId
)  {    public int getUtenteId() {

        return utenteId != null ? utenteId : 0;
                // qua ci ho provato qualcosa che sembra che non va
}

        }
