package com.saraha.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "saraha_user")
@Data
public class User extends BaseIdEntity implements UserDetails {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone", unique = true)
	private String phone;

	@Column(name = "birth_day")
	@Temporal(TemporalType.DATE)
	private Date birthDay;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "isMale")
	private Boolean isMale;

	@Column(name = "user_key", unique = true)
	private long userKey;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST })
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Message> messages;

	public Map<String, Object> getUserAuthorities() {

		Map<String, Object> authorities = new HashMap<String, Object>();

		authorities.put("role", new SimpleGrantedAuthority(role.getRole()));

		List<SimpleGrantedAuthority> permissionList = new ArrayList<SimpleGrantedAuthority>();

		role.getPermissions().forEach(p -> {

			permissionList.add(new SimpleGrantedAuthority(p.getName()));
		});

		authorities.put("permission", permissionList);

		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(role.getRole()));
		role.getPermissions().forEach(p -> {
			authorities.add(new SimpleGrantedAuthority(p.getName()));
		});

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
}