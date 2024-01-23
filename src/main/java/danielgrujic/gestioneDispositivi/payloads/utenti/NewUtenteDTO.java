package danielgrujic.gestioneDispositivi.payloads.utenti;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(
    @NotEmpty(message = "Il nome è obbligatorio")
    @Size(min = 2, max = 15, message = "Nome non valido ")
    String nome,
    @NotEmpty(message = "Il cognome è obbligatorio")
    String cognome,
    @NotEmpty(message = "L'email è obbligatoria")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email  non valida")
    String email,
    @NotEmpty(message = "Il username è obbligatorio")
    @Size(min = 1, max = 10, message = "Username non valido ")
    String username,

    @NotEmpty(message = "La password è obbligatoria")
    @Size(min = 6, message = "Password troppo corta ")
    String password
){
}
