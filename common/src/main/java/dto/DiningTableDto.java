package dto;

import dto.inter.InterfaceDto;
import exception.LocalException;

public class DiningTableDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5201848931832508311L;

	private Integer id;
	private Integer size;
	private Integer idStatus;

	public DiningTableDto() {
		super();
		setAll(defaultId, defaultSize, defaultStatus);
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		try {
			if (isValidInteger(size))
				this.size = size;
		} catch (LocalException ex) {
			this.size = defaultSize;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer status) {
		try {
			if (isValidInteger(status))
				this.idStatus = status;
		} catch (LocalException e) {
			this.idStatus = defaultStatus;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public void setAll(Integer id, Integer size) {
		setId(id);
		setSize(size);
	}

	public void setAll(Integer id, Integer size, Integer status) {
		setId(id);
		setSize(size);
		setIdStatus(status);
	}

}
