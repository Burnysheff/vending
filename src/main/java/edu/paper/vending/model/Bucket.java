package edu.paper.vending.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bucket")
@Entity
public class Bucket {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="bucket_id")
	private Long id;

	@Column(name="price")
	private Long price;

	@ManyToOne
	@JoinColumn(name = "vending_id")
	private Vending vending;

	@ManyToOne
	@JoinColumn(name = "prioduct_id")
	private List<Product> products;
}
