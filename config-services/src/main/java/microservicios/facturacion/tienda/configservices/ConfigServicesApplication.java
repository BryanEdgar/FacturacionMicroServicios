package microservicios.facturacion.tienda.configservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer //habilitamos para q sea un servidor de configuraciones
@SpringBootApplication
public class ConfigServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServicesApplication.class, args);
	}

}
