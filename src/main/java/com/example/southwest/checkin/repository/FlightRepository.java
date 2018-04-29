/*
 * @author ydp
 */
package com.example.southwest.checkin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.southwest.checkin.model.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long>
{
	
}
