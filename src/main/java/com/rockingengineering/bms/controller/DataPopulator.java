/**
 * 
 */
package com.rockingengineering.bms.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockingengineering.bms.enums.CertificateType;
import com.rockingengineering.bms.enums.MovieLanguage;
import com.rockingengineering.bms.enums.SeatType;
import com.rockingengineering.bms.enums.TheaterType;
import com.rockingengineering.bms.model.MovieEntity;
import com.rockingengineering.bms.model.ShowEntity;
import com.rockingengineering.bms.model.ShowSeatsEntity;
import com.rockingengineering.bms.model.TheaterEntity;
import com.rockingengineering.bms.model.TheaterSeatsEntity;
import com.rockingengineering.bms.model.UserEntity;
import com.rockingengineering.bms.repository.MovieRepository;
import com.rockingengineering.bms.repository.ShowRepository;
import com.rockingengineering.bms.repository.ShowSeatsRepository;
import com.rockingengineering.bms.repository.TheaterRepository;
import com.rockingengineering.bms.repository.TheaterSeatsRepository;
import com.rockingengineering.bms.repository.TicketRepository;
import com.rockingengineering.bms.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

/**
 * @author naveen
 *
 * @date 04-Sep-2019
 */
@Log4j2
@RestController
@RequestMapping("initialize")
public class DataPopulator {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showsRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private ShowSeatsRepository showSeatsRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;

	@GetMapping("generate")
	public ResponseEntity<String> generate() {

		log.info("Deleting Data from Shows, Movies and Theaters");

		userRepository.deleteAllInBatch();
		ticketRepository.deleteAllInBatch();

		showSeatsRepository.deleteAllInBatch();
		theaterSeatsRepository.deleteAllInBatch();

		showsRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();
		theaterRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();

		log.info("Adding Starting Shows");

		UserEntity userEntity = UserEntity.builder().name("Test").mobile("1234567890").build();

		userRepository.save(userEntity);

		TheaterEntity pvrDelhi = getTheater("PVR", "Delhi", "Rajori Garden");
		TheaterEntity pvrGurugram = getTheater("PVR", "Gurugram", "MG Road");
		TheaterEntity carnivalDelhi = getTheater("Carnival", "Delhi", "Subhash Garden");

		MovieEntity spidermanMovie = getMovie("Spiderman", MovieLanguage.ENGLISH, CertificateType.UA, LocalDate.now());
		MovieEntity batmanMovie = getMovie("Batman", MovieLanguage.ENGLISH, CertificateType.UA, LocalDate.now().minusDays(1));

		List<ShowEntity> showEntities = new ArrayList<>();

		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.0f, pvrDelhi, spidermanMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.1f, pvrGurugram, spidermanMovie));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.0f, pvrDelhi, spidermanMovie));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.4f, pvrGurugram, spidermanMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.2f, carnivalDelhi, spidermanMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON.plusHours(1), 1.5f, carnivalDelhi, batmanMovie));

		showsRepository.saveAll(showEntities);

		log.debug("Added " + showEntities.size() + " Shows");

		return ResponseEntity.ok(showEntities.size() + " Shows Added");
	}

	private ShowEntity getShow(LocalDate showDate, LocalTime showTime, float multiplier, TheaterEntity theaterEntity, MovieEntity movieEntity) {

		ShowEntity showEntity =
				ShowEntity.builder()
						.showDate(showDate)
						.showTime(showTime)
						.rateMultiplier(multiplier)
						.theater(theaterEntity)
						.movie(movieEntity)
						.build();

		theaterEntity.getShows().add(showEntity);
		movieEntity.getShows().add(showEntity);
		showEntity.setSeats(generateShowSeats(theaterEntity.getSeats(), showEntity));

		for (ShowSeatsEntity seatsEntity : showEntity.getSeats()) {
			seatsEntity.setShow(showEntity);
		}

		return showEntity;

	}

	private List<ShowSeatsEntity> generateShowSeats(List<TheaterSeatsEntity> theaterSeatsEntities, ShowEntity showEntity) {

		List<ShowSeatsEntity> showSeatsEntities = new ArrayList<>();

		for (TheaterSeatsEntity theaterSeatsEntity : theaterSeatsEntities) {

			ShowSeatsEntity showSeatsEntity =
					ShowSeatsEntity.builder()
							.seatNumber(theaterSeatsEntity.getSeatNumber())
							.seatType(theaterSeatsEntity.getSeatType())
							.rate((int) (theaterSeatsEntity.getRate() * showEntity.getRateMultiplier()))
							.build();

			showSeatsEntities.add(showSeatsEntity);
		}

		return showSeatsRepository.saveAll(showSeatsEntities);
	}

	private TheaterEntity getTheater(String name, String city, String address) {

		TheaterEntity theaterEntity =
				TheaterEntity.builder()
						.name(name)
						.type(TheaterType.SINGLE_SCREEN)
						.city(city)
						.address(address)
						.build();

		theaterEntity.getSeats().addAll(getTheaterSeats());

		for (TheaterSeatsEntity seatsEntity : theaterEntity.getSeats()) {
			seatsEntity.setTheater(theaterEntity);
		}

		theaterEntity = theaterRepository.save(theaterEntity);

		return theaterEntity;
	}

	private List<TheaterSeatsEntity> getTheaterSeats() {

		List<TheaterSeatsEntity> seats = new ArrayList<>();

		seats.add(getTheaterSeat("1A", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1B", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1C", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1D", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1E", SeatType.CLASSIC, 100));

		seats.add(getTheaterSeat("2A", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2B", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2C", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2D", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2E", SeatType.PREMIUM, 120));

		seats = theaterSeatsRepository.saveAll(seats);

		return seats;
	}

	private TheaterSeatsEntity getTheaterSeat(String seatNumber, SeatType seatType, int rate) {
		return TheaterSeatsEntity.builder().seatNumber(seatNumber).seatType(seatType).rate(rate).build();
	}

	private MovieEntity getMovie(String name, MovieLanguage language, CertificateType certificateType, LocalDate releaseDate) {
		MovieEntity movieEntity =
				MovieEntity.builder()
						.name(name)
						.language(language)
						.certificateType(certificateType)
						.releaseDate(releaseDate)
						.build();

		movieEntity = movieRepository.save(movieEntity);

		return movieEntity;
	}
}