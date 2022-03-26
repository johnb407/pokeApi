package edu.salleurl;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.infraestructure.adapters.input.console.GetTypesController;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class AppApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(AppApplication.class, args);

		if(ArrayUtils.isNotEmpty(args)){
			GetTypesController controller = run.getBean(GetTypesController.class);
			String types = null;
			try {
				types = controller.GetTypes(args[0]);
				System.out.println("Input:"+ args[0]);
				System.out.println("Output:"+ types);
			} catch (RepositoryNotRespondingException e) {
				System.out.println( e.getMessage());
			} catch (NotFoundException e) {
				System.out.println(e.getMessage());
			}

		}else{
			System.out.println("No commands");
		}

	}



}
