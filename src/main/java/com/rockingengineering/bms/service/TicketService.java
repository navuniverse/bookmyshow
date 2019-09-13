/**
 * 
 */
package com.rockingengineering.bms.service;

import com.rockingengineering.bms.dto.BookTicketRequestDto;
import com.rockingengineering.bms.dto.TicketDto;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
public interface TicketService {

	TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto);

	TicketDto getTicket(long id);
}