package danielgrujic.gestioneDispositivi.payloads.errors;

import java.util.Date;
import java.util.List;

public record ErrorsPayload(
        String message,
        Date timestamp) {
    public ErrorsPayload(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}