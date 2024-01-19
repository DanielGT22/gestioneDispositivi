package danielgrujic.gestioneDispositivi.payloads.dispositivi;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public record NewDIspositivoPayload(


        @NotNull(message = "Inserisci il tipo di dispositivo(Cell, Laptop, Desktop...")
        String tipo,
        @NotNull(message = "Disponibilità dell dispositivo(disponibile, assegnato, in manutenzione, dismesso)")
        String disponibile,
        Integer utenteId
) {

}
