package cl.Duoc.MiMicorservicio1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.Duoc.MiMicorservicio1.model.Venta;
import cl.Duoc.MiMicorservicio1.repository.VentaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

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
