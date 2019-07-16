package com.syk.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "t_name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	public UserEntity() {}
	public UserEntity(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
}
