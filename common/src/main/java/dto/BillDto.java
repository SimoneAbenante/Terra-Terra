package dto;

import dto.inter.InterfaceDto;
import exception.LocalException;

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
		setAll(defaultId, defaultString, defaultDouble);
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		try {
			if (isValidString(paymentMethod))
				this.paymentMethod = paymentMethod;
		} catch (LocalException ex) {
			this.paymentMethod = defaultString;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		try {
			if (isValidInteger(id))
				this.id = id;
		} catch (LocalException ex) {
			this.id = defaultId;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		try {
			if (isValidDouble(total))
				this.total = total;
		} catch (LocalException ex) {
			this.total = defaultDouble;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

	public void setAll(String paymentMethod, Double total) {
		setPaymentMethod(paymentMethod);
		setTotal(total);
	}

	public void setAll(Integer id, String paymentMethod, Double total) {
		setId(id);
		setPaymentMethod(paymentMethod);
		setTotal(total);
	}

}
