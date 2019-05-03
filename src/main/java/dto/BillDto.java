package dto;

import dto.inter.InterfaceDto;

public class BillDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7160787167155766721L;
	private int id;
	private String paymentMethod;
	private double total;
	
	public BillDto() {
		super();
	}
	
	public BillDto(String paymentMethod, double total) {
		this.paymentMethod = paymentMethod;
		this.total = total;
	}

	public BillDto(int id, String paymentMethod, double total) {
		this.id = id;
		this.paymentMethod = paymentMethod;
		this.total = total;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
