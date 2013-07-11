package org.fedorahosted.beaker.model;

public class NoBeakerClientException extends RuntimeException {

    public NoBeakerClientException() {
        super("Beaker client is not set");
    }
    
    private static final long serialVersionUID = 1L;

}
