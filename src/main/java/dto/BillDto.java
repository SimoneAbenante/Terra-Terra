package dto;

import dao.Bill;
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
	
	public static Bill fromDtoToBill(BillDto billDto) {
		Bill bill = new Bill();
		bill.setId(billDto.getId());
		bill.setPaymentMethod(billDto.getPaymentMethod());
		bill.setTotal(billDto.getTotal());
		return bill;
	}
	
	public static BillDto fromBillToDto(Bill bill) {
		BillDto billDto = new BillDto();
		billDto.setId(bill.getId());
		billDto.setPaymentMethod(bill.getPaymentMethod());
		billDto.setTotal(bill.getTotal());
		return billDto;
	}

}
