/**
 * 
 */
package com.rockingengineering.bms.service;

import com.rockingengineering.bms.dto.TheaterDto;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
public interface TheaterService {

	TheaterDto addTheater(TheaterDto theaterDto);

	TheaterDto getTheater(long id);
}