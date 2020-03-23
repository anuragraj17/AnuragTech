package com.anuragtech.HandleException;

public class RecordNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6460813946203165559L;

	public RecordNotFoundException(String id){
		super("Record not found " + id);
	}

}
