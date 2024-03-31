package edu.paper.vending.model.factory.rules.impl;

import com.example.paps.model.factory.rules.Rule;

public class SimpleRule implements Rule {

	@Override
	public String check() {
		return "Obligatory";
	}
}
