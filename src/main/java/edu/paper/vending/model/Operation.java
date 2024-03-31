package edu.paper.vending.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oper")
@Entity
public class Operation {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="operation_id")
	private Long id;

	@OneToMany
	@JoinColumn(name = "cards_id")
	private ProductCard productCard;

	@ManyToOne
	@JoinColumn(name = "vending_id")
	private Vending vending;

	@Column(name="date")
	private String date;

	@Column(name="price")
	private Long price;
}
