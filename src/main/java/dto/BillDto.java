package dto;

import dto.inter.InterfaceDto;

public class BillDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7160787167155766721L;
	private Integer id;
	private String paymentMethod;
	private Double total;
	
	public BillDto() {
		super();
	}
	
	public BillDto(String paymentMethod, Double total) {
		this.paymentMethod = paymentMethod;
		this.total = total;
	}

	public BillDto(int id, String paymentMethod, Double total) {
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

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
