package com.rockingengineering.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rockingengineering.bms.model.TicketEntity;

/**
 * 
 * @author naveen
 *
 * @date 04-Sep-2019
 */
@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long>, JpaSpecificationExecutor<TicketEntity> {

}