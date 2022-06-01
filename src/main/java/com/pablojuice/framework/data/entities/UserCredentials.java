package com.pablojuice.framework.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_credentials")
public class UserCredentials implements BaseEntity<String> {
	@Id
	@Basic(optional = false)
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "latestUpdate")
	private Date latestUpdateDate;

	public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
		this.latestUpdateDate = new Date();
	}

	@Override
	public String getId() {
		return username;
	}

	@Override
	public Object[] toData() {
		return new Object[]{username, password};
	}
}
