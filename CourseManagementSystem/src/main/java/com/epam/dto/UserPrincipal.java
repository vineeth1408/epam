package com.epam.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.entities.AuthGroup;
import com.epam.entities.InstructorEntity;

public class UserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  transient InstructorEntity instructorEntity;
	private transient List<AuthGroup> authGroup;
	
	public UserPrincipal(InstructorEntity instructorEntity, List<AuthGroup> authGroup) {
		super();
		this.authGroup = authGroup;
		this.instructorEntity = instructorEntity;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(null ==authGroup) {
			return Collections.emptySet();
		}
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
		authGroup.forEach( group -> 
			grantedAuthorities.add( new SimpleGrantedAuthority(group.getAuthenticGroup()))
		);
		return grantedAuthorities;
	}
	
	@Override
	public String getPassword() {
		return instructorEntity.getPassword();
	}
	@Override
	public String getUsername() {
		return instructorEntity.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}