package org.holmes;

import org.holmes.evaluator.BooleanEvaluator;
import org.holmes.evaluator.NumberEvaluator;
import org.holmes.evaluator.ObjectEvaluator;
import org.holmes.statement.SimpleStatement;

/**
 * This class provides a way to chain {@link Evaluator} instances within a same
 * {@link Rule}, through a suitable instance of {@link Statement}.
 * 
 * @author diegossilveira
 */
public class Joint {

	private final Rule rule;

	Joint(Rule rule) {

		this.rule = rule;
	}

	/**
	 * Finalizes an {@link Rule}, specifying a descriptor used to represent a
	 * violation to the {@link Rule}.
	 * 
	 * @param violationDescriptor
	 *            the violation descriptor.
	 */
	public void otherwise(String violationDescriptor) {

		rule.setViolationDescriptor(violationDescriptor);
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param bool
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public BooleanEvaluator or(Boolean bool) {

		return or(new BooleanEvaluator(bool));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param bool
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public BooleanEvaluator and(Boolean bool) {

		return and(new BooleanEvaluator(bool));
	}

	// TODO: add public StringEvaluator or(String string)
	// TODO: add public StringEvaluator and(String string)

	// TODO: add public CollectionEvaluator or(Collection collection)
	// TODO: add public CollectionEvaluator and(Collection collection)

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param number
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public NumberEvaluator or(Number number) {

		return or(new NumberEvaluator(number));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param number
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public NumberEvaluator and(Number number) {

		return and(new NumberEvaluator(number));
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param object
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public ObjectEvaluator<Object> or(Object object) {

		return or(new ObjectEvaluator<Object>(object));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param object
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public ObjectEvaluator<Object> and(Object object) {

		return and(new ObjectEvaluator<Object>(object));
	}

	private <E extends Evaluator<?>> E or(E evaluator) {

		rule.addOrStatement(new SimpleStatement(evaluator));
		return evaluator;
	}

	private <E extends Evaluator<?>> E and(E evaluator) {

		rule.addAndStatement(new SimpleStatement(evaluator));
		return evaluator;
	}

}