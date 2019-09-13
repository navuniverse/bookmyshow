package com.rockingengineering.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rockingengineering.bms.model.TheaterSeatsEntity;

/**
 * 
 * @author naveen
 *
 * @date 04-Sep-2019
 */
@Repository
public interface TheaterSeatsRepository extends JpaRepository<TheaterSeatsEntity, Long>, JpaSpecificationExecutor<TheaterSeatsEntity> {

}