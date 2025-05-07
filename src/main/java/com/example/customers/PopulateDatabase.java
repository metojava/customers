package com.example.customers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class PopulateDatabase {

	private static final Logger log = LoggerFactory.getLogger(PopulateDatabase.class);

	@Bean
	CommandLineRunner initDatabase(CustomerRepository repository) {

//		final Date date1 = getDate("2025-05-02");
//		final Date date2 = getDate("1980-11-20");
//		final Date date3 = getDate("1986-07-23");
//		final Date date4 = getDate("2025-08-16");
//		final Date date5 = getDate("2025-12-18");
//		final Date date6 = getDate("2025-04-25");
		
		final String date1 = "2025-05-02";
		final String date2 = "1980-11-20";
		final String date3 = "1986-07-23";
		final String date4 = "2025-08-16";
		final String date5 = "2025-12-18";
		final String date6 = "2025-04-25";

		return args -> {
			log.info("Adding " + repository.save(new Customer("Alfred Armstrong", "123-456-7890", date1)));
			log.info("Adding " + repository.save(new Customer("Boris Klizowski", "789-456-0123", date2)));
			log.info("Adding " + repository.save(new Customer("Aaron Gomely", "123-456-7890", date3)));
			log.info("Adding " + repository.save(new Customer("Mike Brighton", "789-456-3333", date4)));
			log.info("Adding " + repository.save(new Customer("Carlos Mendes", "234-456-0422", date5)));
			log.info("Adding " + repository.save(new Customer("Diego Santos", "345-456-0123", date6)));
		};
	}

	private Date getDate(String bd) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = formatter.parse(bd);
			System.out.println("Date object: " + date);
		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
		}
		return date;
	}
}
