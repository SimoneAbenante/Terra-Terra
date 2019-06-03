package dto.inter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exception.LocalException;

public interface InterfaceDto extends Serializable {

	public static final String defaultString = "Undefined";
	public static final Integer defaultId = 0;
	public static final Integer defaultStatus = 3;
	public static final Integer defaultSize = 2;
	public static final Double defaultDouble = 0.0;
	public static final List<Integer> defaultList = new ArrayList<>();

	public static final String setFailMessage = "Set fallito per i valoti rrichiesti:\n"
			+ "Valori inizializzati ai loro default";
	public static final String invalidInteger = "Errato inserimento:\n" + "Integer non valido:\n"
			+ "[valori: 0 - (+2147483647)]\n";
	public static final String invalidString = "Errato inserimento:\n" + "String non valida:\n"
			+ "[valori: qualunque 'String' escluso null]\n";
	public static final String invalidDouble = "Errato inserimento:\n" + "Double non valido:\n"
			+ "[valori: 0 - (1.79769313486231570 e + 308)]\n";
	public static final String invalidList = "Errato inserimento:\n" + "Lista non valida:\n"
			+ "[valori: qualunque 'List<Integer>' escluso null]\n";

	public Integer getId();

	public default Boolean isValidInteger(Integer i) throws LocalException {
		if (i != null && i > 0)
			return true;
		throw new LocalException(invalidInteger);
	}

	public default Boolean isValidString(String s) throws LocalException {
		if (s != null)
			return true;
		throw new LocalException(invalidString);
	}

	public default Boolean isValidDouble(Double d) throws LocalException {
		if (d != null & d > 0.0)
			return true;
		throw new LocalException(invalidDouble);
	}
	
	public default Boolean isValidList(List<Integer> l) throws LocalException {
		if (l != null)
			return true;
		throw new LocalException(invalidList);
	}

}
