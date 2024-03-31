package edu.paper.vending.dto;

import edu.paper.vending.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BukkitForm {
	private Long price;
	List<Product> productList;
}
