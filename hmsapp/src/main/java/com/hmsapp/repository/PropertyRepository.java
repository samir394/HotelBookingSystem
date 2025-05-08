package com.hmsapp.repository;

import com.hmsapp.entity.Property;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    //JPQL to search Propertry based on city and country
    @Query("select p from Property p join p.city c join p.country co where co.name=:searchParam ")
    List<Property> searchProperty(
      @Param("searchParam") String searchParam
    );




}