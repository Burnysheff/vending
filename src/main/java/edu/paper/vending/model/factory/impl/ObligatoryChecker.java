package edu.paper.vending.model.factory.impl;

import com.example.paps.model.factory.Checker;
import com.example.paps.model.factory.rules.Rule;
import com.example.paps.model.factory.rules.impl.ObligatoryRule;

public class ObligatoryChecker extends Checker {

	@Override
	protected Rule createRules() {
		return new ObligatoryRule();
	}
}
