package cl.Duoc.MiMicorservicio1.controller;

import cl.Duoc.MiMicorservicio1.model.Venta;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.Duoc.MiMicorservicio1.service.VentaService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/Ventas")

public class VentaController {


    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<?> ListarVentas(){
        List<Venta> ventas = ventaService.BuscarTodaVenta();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentran Datos");
        } else {
            return ResponseEntity.ok(ventas);
        }
    }

    @GetMapping("/{idventa}")
    public ResponseEntity<?>BuscarVenta(@PathVariable Long idventa){
        try{
            Venta buscada = ventaService.BuscarUnaVenta(idventa);
            return ResponseEntity.ok(buscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Ventas");
            
        }
    }

    @PostMapping
    public ResponseEntity<?> GuardarVenta(@RequestBody Venta ventaguardar){
        try{
            Venta ventaRegistrar = ventaService.GuardarVenta(ventaguardar);
            return ResponseEntity.ok(ventaRegistrar);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar la venta");
        }
    }

    @DeleteMapping("/{idventa}")
    public ResponseEntity<String>EliminarVenta(@PathVariable Long idventa){
        try{
            Venta ventabuscada = ventaService.BuscarUnaVenta(idventa);
            ventaService.EliminarVenta(idventa);
            return ResponseEntity.status(HttpStatus.OK).body("se elimina venta");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no esta registrada");
        }

    }

    @PutMapping("/{idventa}")
    public ResponseEntity<?> ActualizarVenta(@PathVariable Long idventa, @RequestBody Venta ventaactualizar){
        try{
            Venta ventaactualizada = ventaService.BuscarUnaVenta(idventa);
            ventaactualizada.setRutusuario(ventaactualizar.getRutusuario());
            ventaactualizada.setFechaventa(ventaactualizar.getFechaventa());
            ventaService.GuardarVenta(ventaactualizada);
           return ResponseEntity.ok(ventaactualizada);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no esta registrada");
        }
    }
}
