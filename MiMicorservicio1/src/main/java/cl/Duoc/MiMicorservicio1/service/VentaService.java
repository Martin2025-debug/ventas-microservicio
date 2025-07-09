package cl.Duoc.MiMicorservicio1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.Duoc.MiMicorservicio1.model.Venta;
import cl.Duoc.MiMicorservicio1.repository.VentaRepository;
import cl.Duoc.MiMicorservicio1.DTO.UsuarioDTO;
import jakarta.transaction.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class VentaService {

    private final WebClient webClient;

    public VentaService(WebClient webClient){
        this.webClient = webClient;
    }

    @Autowired
    private VentaRepository ventaRepository;


    public UsuarioDTO BuscarUsuario(String rut){
        UsuarioDTO usuario = webClient.get()
                                .uri("/buscarporrut/{rut}", rut)
                                .retrieve()
                                .bodyToMono(UsuarioDTO.class)
                                .block();
        return usuario;
    }

    public List<Venta> BuscarTodaVenta(){
        return ventaRepository.findAll();
    }

    public Venta BuscarUnaVenta(Long idventa){
        return ventaRepository.findById(idventa).get();
    }

    public Venta GuardarVenta(Venta venta){
        return ventaRepository.save(venta);
    }

    public void EliminarVenta(Long idventa){
        ventaRepository.deleteById(idventa);
    }

}
