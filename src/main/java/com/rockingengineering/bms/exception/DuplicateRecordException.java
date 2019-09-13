/**
 * 
 */
package com.rockingengineering.bms.exception;

import lombok.Getter;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
@Getter
public class DuplicateRecordException extends RuntimeException {

	private static final long serialVersionUID = 646182706219385282L;

	private final String message;

	public DuplicateRecordException(String message) {
		super(message);
		this.message = message;
	}

}