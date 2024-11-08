package br.com.back_end.clinica_hospitalar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaHospitalarApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaHospitalarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicação iniciada com sucesso!");
	}
}
