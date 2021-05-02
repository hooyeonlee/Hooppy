package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Role;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class IntegrationDto implements UserDetails {
    //가제

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;
    private String email;
    private String upwd;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public IntegrationEntity toEntity() {
        return IntegrationEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .upwd(upwd)
                .role(Role.MEMBER)
                .build();
    }


    @Builder
    public IntegrationDto(String name, String email, String upwd, Role role) {
        this.name = name;
        this.email = email;
        this.upwd = upwd;
        this.role = role;
    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getPassword() {
		return this.upwd;
	}


	@Override
	public String getUsername() {
		return name;
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