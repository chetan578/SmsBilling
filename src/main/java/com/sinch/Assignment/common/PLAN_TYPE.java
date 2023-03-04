package com.sinch.Assignment.common;

public enum PLAN_TYPE {

	BASIC(0.001), SILVER(0.002), GOLD(0.003);

	PLAN_TYPE(double d) {
		this.planAmount = d;
	}

	private double planAmount;

	public double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(double planAmount) {
		this.planAmount = planAmount;
	}

}
