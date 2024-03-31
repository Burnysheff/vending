package edu.paper.vending.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vending")
@Entity
public class Vending {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="vending_id")
	private Long id;

	@Column(name="name")
	private String name;
	@Column(name="date")
	private String date;
	@Column(name="X")
	private Long X;
	@Column(name="Y")
	private Long Y;
}
