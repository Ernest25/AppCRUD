package service;

import exception.CheckException;

public abstract class Service {
	
	
	protected void checkId(final String id) throws CheckException {
    	if ( id == null || id.equals("") )
    		throw new CheckException("Id should not be null or empty");    	
    }

}
