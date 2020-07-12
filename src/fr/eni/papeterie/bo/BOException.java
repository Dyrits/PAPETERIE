package fr.eni.papeterie.bo;

public class BOException extends Exception {
	//Constructeurs
	public BOException() {
		super();
	}

	public BOException(String message) {
		super(message);
	}

	public BOException(String message, Throwable exception) {
		super(message, exception);
	}

	//Méthodes
	@Override
	public String getMessage() {
		return "BO - " + super.getMessage();
	}
}
