package edu.paper.vending.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="user_id")
	private Long id;

	@Column(name="login")
	private String login;
	@Column(name="name")
	private String name;
	@Column(name="date")
	private String date;
	@Column(name="email")
	private String email;
	@Column(name="hist")
	private String hist;
	@Column(name="auth")
	private String auth;
	@Column(name="bucket")
	private String bucket;
}
