/**
 * 
 */
package com.rockingengineering.bms.service;

import java.time.LocalDate;
import java.time.LocalTime;

import com.rockingengineering.bms.dto.PageResponse;
import com.rockingengineering.bms.dto.ShowDto;

/**
 * @author naveen
 *
 * @date 04-Sep-2019
 */
public interface ShowService {

	ShowDto addShow(ShowDto showDto);

	PageResponse<ShowDto> searchShows(String movieName, String city, LocalDate showDate, LocalTime showTime, int pageNo, int limit);

}