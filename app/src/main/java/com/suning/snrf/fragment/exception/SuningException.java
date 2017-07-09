package com.suning.snrf.fragment.exception;

public class SuningException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SuningException() {
		super();
	}

	public SuningException(String msg) {
		super(msg);
	}

	public SuningException(Throwable ex) {
		super(ex);
	}

	public SuningException(String msg,Throwable ex) {
		super(msg,ex);
	}

}
