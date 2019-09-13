/**
 * 
 */
package com.rockingengineering.bms.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

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
public class UserDto {

	private long id;

	@NotBlank(message = "User name is Mandatory")
	private String name;

	@NotBlank(message = "Mobile is Mandatory")
	private String mobile;

	private List<TicketDto> tickets;
}