package cl.Duoc.MiMicorservicio1;

import cl.Duoc.MiMicorservicio1.model.Venta;
import cl.Duoc.MiMicorservicio1.repository.VentaRepository;
import cl.Duoc.MiMicorservicio1.service.VentaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private VentaService ventaService;

    @Test
    void testBuscarTodaVenta() {
        Venta venta1 = new Venta();
        venta1.setIdventa(1L);
        venta1.setDetalle("Producto A");

        Venta venta2 = new Venta();
        venta2.setIdventa(2L);
        venta2.setDetalle("Producto B");

        List<Venta> ventasMock = Arrays.asList(venta1, venta2);

        when(ventaRepository.findAll()).thenReturn(ventasMock);

        List<Venta> resultado = ventaService.BuscarTodaVenta();

        assertEquals(2, resultado.size());
        assertEquals("Producto A", resultado.get(0).getDetalle());
        assertEquals("Producto B", resultado.get(1).getDetalle());
    }

    @Test
    void testBuscarUnaVenta() {
        Long idventa = 1L;

        Venta ventaMock = new Venta();
        ventaMock.setIdventa(idventa);
        ventaMock.setDetalle("Producto Test");

        when(ventaRepository.findById(idventa)).thenReturn(Optional.of(ventaMock));

        Venta resultado = ventaService.BuscarUnaVenta(idventa);

        assertEquals(idventa, resultado.getIdventa());
        assertEquals("Producto Test", resultado.getDetalle());
    }

    @Test
    void testBuscarUnaVentaNoEncontrada() {
        Long idventa = 2L;

        when(ventaRepository.findById(idventa)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            ventaService.BuscarUnaVenta(idventa);
        });
    }

    @Test
    void testGuardarVenta() {
        Venta ventaNueva = new Venta();
        ventaNueva.setIdventa(3L);
        ventaNueva.setDetalle("Producto Nuevo");

        when(ventaRepository.save(ventaNueva)).thenReturn(ventaNueva);

        Venta resultado = ventaService.GuardarVenta(ventaNueva);

        assertEquals(3L, resultado.getIdventa());
        assertEquals("Producto Nuevo", resultado.getDetalle());
    }

    @Test
    void testEliminarVenta() {
        Long idventa = 4L;

        ventaService.EliminarVenta(idventa);

        verify(ventaRepository).deleteById(idventa);
    }
}
