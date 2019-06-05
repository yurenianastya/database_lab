package ua.lviv.iot.lab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LabWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabWorkApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ConcertHallRepository repository) {
		return (args) ->  {
			repository.save(new ConcertHall(134, 254,
					"White St", 5, true,
					8));
			repository.save(new ConcertHall(100, 200,
					"Black St", 3, false,
					0));
			repository.save(new ConcertHall(421, 786,
					"Red St", 8, true,
					3));
			repository.save(new ConcertHall(140, 1234,
					"Blue St", 2, true,
					1));

			repository.findAll().forEach(concertHall -> System.out.println(concertHall));

		};
	}

}
