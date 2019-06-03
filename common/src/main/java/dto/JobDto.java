package dto;

import dto.inter.InterfaceDto;
import exception.LocalException;

public class JobDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2951026190344300972L;

	private Integer id;
	private Integer idBill;
	private Integer idDiningTable;
	private Integer idDish;
	private Integer idStatus;

	public JobDto() {
		super();
		setAll(defaultId, defaultId, defaultId, defaultId, defaultStatus);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		try {
			if (isValidInteger(id))
				this.id = id;
		} catch (LocalException e) {
			this.id = defaultId;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public Integer getIdBill() {
		return idBill;
	}

	public void setIdBill(Integer idBill) {
		try {
			if (isValidInteger(idBill))
				this.idBill = idBill;
		} catch (LocalException e) {
			this.idBill = defaultId;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public Integer getIdDiningTable() {
		return idDiningTable;
	}

	public void setIdDiningTable(Integer idTable) {
		try {
			if (isValidInteger(idTable))
				this.idDiningTable = idTable;
		} catch (LocalException e) {
			this.idDiningTable = defaultId;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public Integer getIdDish() {
		return idDish;
	}

	public void setIdDish(Integer idDish) {
		try {
			if (isValidInteger(idDish))
				this.idDish = idDish;
		} catch (LocalException e) {
			this.idDish = defaultId;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		try {
			if (isValidInteger(idStatus))
				this.idStatus = idStatus;
		} catch (LocalException e) {
			this.idStatus = defaultId;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public void setAll(Integer idBill, Integer idDiningTable, Integer idDish) {
		setIdBill(idBill);
		setIdDiningTable(idDiningTable);
		setIdDish(idDish);
	}

	public void setAll(Integer id, Integer idBill, Integer idDiningTable, Integer idDish) {
		setId(id);
		setIdBill(idBill);
		setIdDiningTable(idDiningTable);
		setIdDish(idDish);
	}

	public void setAll(Integer id, Integer idBill, Integer idDiningTable, Integer idDish, Integer idStatus) {
		setId(id);
		setIdBill(idBill);
		setIdDiningTable(idDiningTable);
		setIdDish(idDish);
		setIdStatus(idStatus);
	}

}
