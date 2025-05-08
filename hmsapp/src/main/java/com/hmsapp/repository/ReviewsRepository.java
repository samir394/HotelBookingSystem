package com.hmsapp.repository;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

     List<Reviews> findByUser(User user);
     Reviews findByPropertyAndUser(Optional<Property> property, User user);
}