package com.upgrad.mtb.daos;

import com.upgrad.mtb.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bookingDAO")
public interface BookingDAO  extends JpaRepository<Booking, Integer> {
}
