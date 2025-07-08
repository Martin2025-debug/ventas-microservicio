package cl.Duoc.MiMicorservicio1.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaUsuarioDTO {

// Datos de la Venta
    private Long idventa;
    private String rutusuario;
    private LocaDate fechaventa;    

// Datos del usuario (traidos desde UsaurioDTO)

    private String nombre;
    private String mail;
}
