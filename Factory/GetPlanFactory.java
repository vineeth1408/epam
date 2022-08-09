class GetPlanFactory {
	public Plan getPlan(String planType) {
		if(planType == null) {
			return null;
		}
		if(planType.equalsIgnoreCase("DOMESTIC")) {
			return new DomesticPlan();
		}
		else if(planType.equalsIgnoreCase("COMMERCIAL")) {
			return new CommercialPan();
		}
		else if(planType.equalsIgnoreCase("INSTITUTIONAL")) {
			return new InstitutionalPlan();
		}
		return null;
	}
}