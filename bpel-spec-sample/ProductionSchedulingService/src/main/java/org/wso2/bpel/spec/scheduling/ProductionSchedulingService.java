package org.wso2.bpel.spec.scheduling;

public class ProductionSchedulingService{
	
	public long schedule(String orderId){
		System.out.println("Scheduled production for order " + orderId);

		return 1000;
	}

}
