package edu.paper.vending.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity
public class Product {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="product_id")
	private Long id;

	@Column(name="date")
	private String creationDate;

	@Column(name="name")
	private String name;

	@Column(name="price")
	private Long price;
}
