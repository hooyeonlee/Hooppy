package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.IntegrationDto;

public interface UserDAO {
	List<IntegrationDto> selectUsers();
}
