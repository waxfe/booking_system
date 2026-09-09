package com.waxfe.booking_system;

import com.waxfe.booking_system.entity.RoomEntity;
import com.waxfe.booking_system.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingSystemApplication.class, args);
	}
	@Bean
	CommandLineRunner testRun(RoomRepository roomRepository) {
		return args -> {
			// Создаем комнату через сеттеры
			RoomEntity room = new RoomEntity();
			room.setName("Meeting Room A");
			room.setCapacity(10);
			room.setLocation("Floor 3, Room 301");
			room.setActive(true);

			roomRepository.save(room);
			System.out.println("✅ Room saved with ID: " + room.getId());

			// Выводим все комнаты
			roomRepository.findAll().forEach(r ->
					System.out.println("📋 Room: " + r.getName() + ", Capacity: " + r.getCapacity())
			);
		};
	}
}
