package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IntegrationRepository;
import com.example.demo.dto.IntegrationDto;
import com.example.demo.dto.IntegrationEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private IntegrationRepository integrationRepository;

    public IntegrationEntity joinUser(IntegrationDto integrationDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        integrationDto.setUpwd(passwordEncoder.encode(integrationDto.getUpwd()));

        return integrationRepository.save(integrationDto.toEntity());
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<IntegrationEntity> integrationEntityOptional = integrationRepository.findByName(username);
        IntegrationEntity integrationEntity = integrationEntityOptional.orElse(null);
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(integrationEntity.getRoleKey()));

        return new User(integrationEntity.getName(), integrationEntity.getUpwd(), authorities);
	}
}