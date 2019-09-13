/**
 * 
 */
package com.rockingengineering.bms.dto;

import java.util.List;

import com.rockingengineering.bms.enums.TheaterType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TheaterDto {

	private long id;

	private String name;

	private TheaterType type;

	private String city;

	private String address;

	private List<ShowDto> shows;
}