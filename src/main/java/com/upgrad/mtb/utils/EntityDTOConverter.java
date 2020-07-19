package com.upgrad.mtb.utils;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.dto.TheatreDTO;
import com.upgrad.mtb.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityDTOConverter {
    @Autowired
    CustomerService customerService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    CityService cityService;
    @Autowired
    MovieService movieService;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;

    public MovieDTO convertToMovieDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movie.getId());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setName(movie.getName());
        movieDTO.setCoverURL(movie.getCoverPhotoURL());
        movieDTO.setTrailerURL(movie.getTrailerURL());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setLanguageId(movie.getLanguage().getId());
        movieDTO.setStatusId(movie.getStatus().getId());
        if(movie.getTheatres() != null) {
            List<Theatre> theatres = movie.getTheatres();
            List<Integer> theatreIds = new ArrayList<>();
            for (Theatre theatre : theatres) {
                theatreIds.add(theatre.getId());
            }
            movieDTO.setTheatreIds(theatreIds);
        }
        return movieDTO;
    }

    public TheatreDTO convertToTheatreDTO(Theatre theatre){
        TheatreDTO theatreDTO = new TheatreDTO();
        theatreDTO.setTheatreId(theatre.getId());
        theatreDTO.setCityId(theatre.getCity().getId());
        if(theatre.getMovies() != null) {
            List<Movie> movies = theatre.getMovies();
            List<Integer> movieIds = new ArrayList<>();
            for (Movie movie : movies) {
                movieIds.add(movie.getId());
            }
            theatreDTO.setMovieIds(movieIds);
        }
        theatreDTO.setNoOfSeats(theatre.getNoOfSeats());
        theatreDTO.setTheatreName(theatre.getTheatreName());
        theatreDTO.setTicketPrice(theatre.getTicketPrice());
        if(theatre.getBookings() != null) {
            List<Booking> bookingList = theatre.getBookings();
            List<Integer> bookingIds = new ArrayList<>();
            for (Booking booking : bookingList) {
                bookingIds.add(booking.getId());
            }
            theatreDTO.setBookingIds(bookingIds);
        }
        return theatreDTO;
    }

    public BookingDTO convertToBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(booking.getCustomer().getId());
        bookingDTO.setTheatreId(booking.getTheatre().getId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setNoOfSeats(booking.getNoOfSeats());
        bookingDTO.setBookingId(booking.getId());
        return bookingDTO;
    }

    public CustomerDTO convertToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setCustomerId(customer.getId());
        customerDTO.setUserTypeId(customer.getUserType().getId());
        customerDTO.setPhoneNumbers(customer.getPhoneNumbers());
        if(customer.getBookings() != null) {
            List<Booking> bookingList = customer.getBookings();
            List<Integer> bookingIds = new ArrayList<>();
            for (Booking booking : bookingList) {
                bookingIds.add(booking.getId());
            }
            customerDTO.setBookingIds(bookingIds);
        }
        return customerDTO;
    }

}
