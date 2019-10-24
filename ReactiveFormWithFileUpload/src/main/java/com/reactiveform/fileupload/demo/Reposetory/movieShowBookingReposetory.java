package com.reactiveform.fileupload.demo.Reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactiveform.fileupload.demo.model.movieShowBooking;

@Repository
public interface movieShowBookingReposetory extends JpaRepository<movieShowBooking, Long> {

}
