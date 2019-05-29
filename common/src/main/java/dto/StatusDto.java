package dto;

import dto.inter.InterfaceDto;
import exception.LocalException;

public class StatusDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6734101703446299791L;

	private Integer id;
	private String status;

	public StatusDto() {
		super();
		setAll(defaultId, defaultString);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		try {
			if (isValidString(status))
				this.status = status;
		} catch (LocalException ex) {
			this.status = defaultString;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

	public void setAll(Integer id, String status) {
		setId(id);
		setStatus(status);
	}

}
