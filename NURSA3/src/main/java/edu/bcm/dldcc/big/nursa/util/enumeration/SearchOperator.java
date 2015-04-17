package edu.bcm.dldcc.big.nursa.util.enumeration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public enum SearchOperator {
	Greater, GreaterInclusive, Equal, LessInclusive, Less;

	public String toString() {
		if (this == Greater) {
			return ">";
		} else if (this == GreaterInclusive) {
			return ">=";
		} else if (this == Equal) {
			return "==";
		} else if (this == LessInclusive) {
			return "<=";
		} else if (this == Less) {
			return "<";
		} else {
			return "";
		}
	}

	public <Y extends Comparable<? super Y>> Predicate getPredicate(
			CriteriaBuilder cb, Expression<? extends Y> x, Y y) {
		if (this == Greater) {
			return cb.greaterThan(x, y);
		} else if (this == GreaterInclusive) {
			return cb.greaterThanOrEqualTo(x, y);
		} else if (this == Equal) {
			return cb.equal(x, y);
		} else if (this == LessInclusive) {
			return cb.lessThanOrEqualTo(x, y);
		} else if (this == Less) {
			return cb.lessThan(x, y);
		} else {
			return null;
		}
	}
}
