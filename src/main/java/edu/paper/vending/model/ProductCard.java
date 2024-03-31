package edu.paper.vending.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
@Entity
public class ProductCard {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="card_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@JoinColumn(name = "quantity")
	private Long quantity;
}
