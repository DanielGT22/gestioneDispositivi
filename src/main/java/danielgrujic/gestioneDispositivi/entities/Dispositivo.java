package danielgrujic.gestioneDispositivi.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "dispositivo")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private String disponibile;


    @ManyToOne
    @JoinColumn(name = "utenteId")
    private Utente utente;
}
