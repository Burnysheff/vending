package edu.paper.vending.model.factory;

import com.example.paps.model.factory.rules.Rule;

public abstract class Checker {
	private void runCheck() {
		Rule rule = createRules();
		rule.check();
	}
	protected abstract Rule createRules();
}
