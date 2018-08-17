package erros;

public class RecursoInvalidoException extends Exception{
	/**
	 * Gerado automaticamente
	 */
	private static final long serialVersionUID = -6217486544562018808L;

	public RecursoInvalidoException(){
		super("Recurso requisitado não existe ou apresenta problemas.");		
	}

}
