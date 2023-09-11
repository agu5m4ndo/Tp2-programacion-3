package com.example.TPPersistenciaTUP;

import com.example.TPPersistenciaTUP.Entities.*;
import com.example.TPPersistenciaTUP.Enum.EnumEntity;
import com.example.TPPersistenciaTUP.Enum.EnumFormaPago;
import com.example.TPPersistenciaTUP.Enum.EnumTipoEnvio;
import com.example.TPPersistenciaTUP.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@SpringBootApplication
public class TpPersistenciaTupApplication {

    @Autowired
	ClienteRepository clienteRepo;
	@Autowired
	DetallePedidoRepository detalleRepo;
	@Autowired
	DomicilioRepository domicilioRepo;
	@Autowired
	FacturaRepository facturaRepo;
	@Autowired
	PedidoRepository pedidoRepo;
	@Autowired
	ProductoRepository productoRepo;
	@Autowired
	RubroRepository rubroRepo;
	@Autowired
	UsuarioRepository usuarioRepo;
	@Autowired
	ConfiguracionGeneralRepository configuracionGeneralRepo;

	public static void main(String[] args) {
		SpringApplication.run(TpPersistenciaTupApplication.class, args);
		System.out.println("Funcionando en el 8080");
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo,
						   FacturaRepository facturaRepo,
						   PedidoRepository pedidoRepo,
						   ProductoRepository productoRepo,
						   DetallePedidoRepository detalleRepo,
						   UsuarioRepository usuarioRepo,
						   DomicilioRepository domicilioRepo,
						   RubroRepository rubroRepo,
						   ConfiguracionGeneralRepository configuracionGeneralRepo) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			//Configuración general de la empresa
			ConfiguracionGeneral carrodillaGrill = ConfiguracionGeneral.builder()
					.emailEmpresa("carrodillaGrill")
					.cantidadCocineros(3)
					.tokenMercadoPago("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ik1lcmNhZG9QYWdvIiwiaWF0IjoxNTE2MjM5MDIyfQ.d4z-hrYlHz19PTyHUVWijvH4SgWZk1x6D7yLwLgVDm0\n")
					.build();

			configuracionGeneralRepo.save(carrodillaGrill);

			//Primero agregamos al operador de pedidos del local
			Usuario miguel = Usuario.builder()
					.nombre("Miguel Baez")
					.password("losabuelosdelanada")
					.rol("operador de pedidos")
					.build();
			usuarioRepo.save(miguel);

			//Agregamos los rubros del negocio
			Rubro rubroLomos = Rubro.builder()
					.denominacion("Lomos")
					.build();
			rubroRepo.save(rubroLomos);

			Rubro rubroHamburguesas = Rubro.builder()
					.denominacion("Hamburguesas")
					.build();
			rubroRepo.save(rubroHamburguesas);

			//Creamos un par de productos y los guardamos en el rubro correspondiente
			Producto hamburguesaAmericana = Producto.builder()
					.tiempoEstimadoCocina(12)
					.tipo(Producto.Tipo.INSUMO)
					.denominacion("burger americana")
					.precioVenta(1800)
					.precioCompra(1300)
					.stockActual(10)
					.stockMinimo(1)
					.unidadMedida("gramos")
					.foto("https://cloudinary.com/user=agustin-mandolesi/hamburgesa-americana.jpg")
					.receta("https://blogger.com/blog/posts/352175442507561632")
					.build();
			productoRepo.save(hamburguesaAmericana);

			Producto lomoBarroluco = Producto.builder()
					.tiempoEstimadoCocina(8)
					.tipo(Producto.Tipo.INSUMO)
					.denominacion("lomo barroluco")
					.precioVenta(3000)
					.precioCompra(2300)
					.stockActual(15)
					.stockMinimo(1)
					.unidadMedida("gramos")
					.foto("https://cloudinary.com/user=agustin-mandolesi/barroluco.jpg")
					.receta("https://blogger.com/blog/posts/352175442507561632")
					.build();
			productoRepo.save(lomoBarroluco);


			rubroLomos.addProducto(lomoBarroluco);
			rubroHamburguesas.addProducto(hamburguesaAmericana);

			//Entra un pedido de dos productos
			DetallePedido detalleGuillermo1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(1800)
					.producto(hamburguesaAmericana)
					.build();
			detalleRepo.save(detalleGuillermo1);

			DetallePedido detalleGuillermo2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(3000)
					.producto(lomoBarroluco)
					.build();
			detalleRepo.save(detalleGuillermo2);

			//Se genera una factura para cada pedido
			Factura facturaGuillermo = Factura.builder()
					.fecha(LocalDateTime.now())
					.numero(1)
					.descuento(200.0)
					.formaPago(EnumFormaPago.DEBITO)
					.total(4800)
					.build();
			facturaRepo.save(facturaGuillermo);

			//Se añade la factura al pedido del cliente y Miguel agrega el pedido
			Pedido pedidoGuillermo = Pedido.builder()
					.fecha(LocalDateTime.now())
					.estado(EnumEntity.INICIADO)
					.horaEstimadaEntrega(LocalDateTime.now().plus(40, ChronoUnit.MINUTES))
					.tipoEnvio(EnumTipoEnvio.DELIVERY)
					.total(4800.0)
					.factura(facturaGuillermo)
					.build();
			pedidoGuillermo.addDetallePedidos(detalleGuillermo1);
			pedidoGuillermo.addDetallePedidos(detalleGuillermo2);

			System.out.println(pedidoGuillermo);// Esto lo hace bien, abajo no persiste por alguna razón
			try {
				pedidoRepo.save(pedidoGuillermo);
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
			miguel.addPedido(pedidoGuillermo);

			//Se añade a los datos del cliente los pedidos que realizó
			Cliente guillermo = Cliente.builder()
					.nombre("Guillermo")
					.apellido("Massera")
					.email("guillemasse@gmail.com")
					.telefono("2613839423")
					.build();
			guillermo.addPedidos(pedidoGuillermo);
			clienteRepo.save(guillermo);

			//Se agrega el domicilio de Guillermo y su pedido
			Domicilio casaGuillermo = Domicilio.builder()
					.calle("Granaderos")
					.numero("2315")
					.localidad("Luján de Cuyo")
					.cliente(guillermo)
					.build();
			casaGuillermo.addPedidos(pedidoGuillermo);
			domicilioRepo.save(casaGuillermo);

			// Recuperar el objeto Persona desde la base de datos

			/* Persona personaRecuperada = personaRepository.findById(persona.getId()).orElse(null);
			if (personaRecuperada != null) {
				System.out.println("Nombre: " + personaRecuperada.getNombre());
				System.out.println("Apellido: " + personaRecuperada.getApellido());
				System.out.println("Edad: " + personaRecuperada.getEdad());
				System.out.println("Calle : " + personaRecuperada.getDomicilio().getCalle());
				System.out.println("Número :" + personaRecuperada.getDomicilio().getNumero());
			}
			*/

			/*
			System.out.println("......  Muestro la bidireccionalidad.......");
			// Recuperar el objeto Persona desde la base de datos
			Domicilio domicilioRecuperado = domicilioRepository.findById(persona.getId()).orElse(null);
			if (domicilioRecuperado!= null) {
				System.out.println("Nombre: " + domicilioRecuperado.getPersona().getNombre());
				System.out.println("Apellido: " + domicilioRecuperado.getPersona().getApellido());
				System.out.println("Edad: " + domicilioRecuperado.getPersona().getEdad());
				System.out.println("Calle : " + domicilioRecuperado.getCalle());
				System.out.println("Número :" + domicilioRecuperado.getNumero());
			}
			*/
		};
	}
}
