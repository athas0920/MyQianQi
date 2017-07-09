package com.suning.snrf.fragment.exception;

public class ViewException extends SuningException {
	
	private static final long serialVersionUID = 1L;
	private String strMsg = null;
	public ViewException(String strExce) {
		strMsg = strExce;
	}

	public void printStackTrace() {
		if(strMsg!=null)
			System.err.println(strMsg);

		super.printStackTrace();
	}
}
