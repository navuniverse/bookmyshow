/**
 * 
 */
package com.rockingengineering.bms.adapter;

import com.rockingengineering.bms.dto.TheaterDto;
import com.rockingengineering.bms.model.TheaterEntity;

import lombok.experimental.UtilityClass;

/**
 * @author naveen
 *
 * @date 04-Sep-2019
 */
@UtilityClass
public class TheaterAdapter {

	public static TheaterEntity toEntity(TheaterDto theaterDto) {

		return TheaterEntity.builder()
				.name(theaterDto.getName())
				.type(theaterDto.getType())
				.city(theaterDto.getCity())
				.address(theaterDto.getAddress())
				.build();
	}

	public static TheaterDto toDto(TheaterEntity theaterEntity) {

		return TheaterDto.builder()
				.id(theaterEntity.getId())
				.name(theaterEntity.getName())
				.type(theaterEntity.getType())
				.city(theaterEntity.getCity())
				.address(theaterEntity.getAddress())
				.build();
	}

}