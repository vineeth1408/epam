package com.epam.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.dao.AuthGroupRepo;
import com.epam.dao.InstructorRepo;
import com.epam.dto.UserPrincipal;
import com.epam.entities.AuthGroup;
import com.epam.entities.InstructorEntity;

@Service
public class AppUserDetailsService implements UserDetailsService {

	private final InstructorRepo instructorRepo;
	private final AuthGroupRepo authGroupRepo;
	
	public AppUserDetailsService(InstructorRepo instructorRepo, AuthGroupRepo authGroupRepository) {
		super();
		this.instructorRepo = instructorRepo;
		this.authGroupRepo = authGroupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		InstructorEntity instructorEntity = instructorRepo.findByUsername(username);
		if(instructorEntity == null) {
			throw new UsernameNotFoundException("cannot find username : "+username);
		}
		List<AuthGroup> authorites = authGroupRepo.findByUsername(username);
		return new UserPrincipal(instructorEntity, authorites);
	}	
}
