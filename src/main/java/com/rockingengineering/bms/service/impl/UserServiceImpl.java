/**
 * 
 */
package com.rockingengineering.bms.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockingengineering.bms.adapter.UserAdapter;
import com.rockingengineering.bms.dto.UserDto;
import com.rockingengineering.bms.exception.DuplicateRecordException;
import com.rockingengineering.bms.model.UserEntity;
import com.rockingengineering.bms.repository.UserRepository;
import com.rockingengineering.bms.service.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto addUser(UserDto userDto) {

		if (userRepository.existsByMobile(userDto.getMobile())) {
			throw new DuplicateRecordException("User Already Exists with Mobile: " + userDto.getMobile());
		}

		log.info("Adding New User: " + userDto);

		UserEntity userEntity = UserAdapter.toEntity(userDto);

		userEntity = userRepository.save(userEntity);

		log.info("Added New User [id: " + userEntity.getId() + ", Mobile: " + userEntity.getMobile() + "]");

		return UserAdapter.toDto(userEntity);
	}

	@Override
	public UserDto getUser(long id) {

		log.info("Searching User by id: " + id);

		Optional<UserEntity> userEntity = userRepository.findById(id);

		if (!userEntity.isPresent()) {
			log.error("User not found for id: " + id);
			throw new EntityNotFoundException("User Not Found with ID: " + id);
		}

		return UserAdapter.toDto(userEntity.get());
	}

}