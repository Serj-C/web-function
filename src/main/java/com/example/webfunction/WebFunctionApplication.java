package com.example.webfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class WebFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFunctionApplication.class, args);
	}

	List<TollStation> tollStations;

	public WebFunctionApplication() {
		tollStations = new ArrayList<>();
		tollStations.add(new TollStation("100A", 112.5f, 2));
		tollStations.add(new TollStation("111C", 124f, 4));
		tollStations.add(new TollStation("112C", 126f, 2));
	}

	@Bean
	public Function<String, TollStation> retrieveStation() {
		return stationId -> {
			System.out.println("Received request for station - " + stationId);
			return tollStations.stream()
					.filter(tollStation -> stationId.equals(tollStation.getStationId()))
					.findAny()
					.orElse(null);
		};
	}

	@Bean
	public Consumer<TollRecord> processTollRecord() {
		return tollRecord ->
				System.out.println("Received toll for car with license plate - " + tollRecord.getLicensePlate());
	}

	@Bean
	public Function<TollRecord, Mono<Void>> processTollRecordReactive() {
		return tollRecord -> {
			System.out.println("Received reactive toll for car with license plate - "
					+ tollRecord.getLicensePlate());
			return Mono.empty();
		};
	}

	@Bean
	public Consumer<Flux<TollRecord>> processListOfTollRecordsReactive() {
		return tollRecordFlux -> {
			tollRecordFlux.subscribe(tollRecord -> System.out.println(tollRecord.getLicensePlate()));
		};
	}

	@Bean
	public Supplier<Flux<TollStation>> getTollStations() {
		return () -> Flux.fromIterable(tollStations);
	}
}
