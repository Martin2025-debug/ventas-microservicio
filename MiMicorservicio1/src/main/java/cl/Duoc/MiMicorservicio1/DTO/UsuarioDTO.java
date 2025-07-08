package cl.Duoc.MiMicorservicio1.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long idUsuario;
    private String rutusuario;
    private String nombre;
    private String mail;
    private LocalDate fechaIngreso;

}
