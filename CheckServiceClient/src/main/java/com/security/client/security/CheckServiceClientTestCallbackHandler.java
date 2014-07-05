package com.security.client.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;

public class CheckServiceClientTestCallbackHandler implements CallbackHandler{
	
	private final org.apache.log4j.Logger logger = Logger.getLogger(CheckServiceClientTestCallbackHandler.class);
	private Map<String, String> x509Passwords = new HashMap<String, String>();
	private Map<String, String> utPasswords = new HashMap<String, String>();
	
	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof WSPasswordCallback) {
                WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
                if (pc.getUsage() == WSPasswordCallback.DECRYPT || 
                    pc.getUsage() == WSPasswordCallback.SIGNATURE) {
                    final String pass = (String) x509Passwords.get(pc.getIdentifier());
                    logger.debug("DECRYPT/SIGNATURE CALLBACK FOR: "+pc.getIdentifier());
                    if (pass != null) {
                        pc.setPassword(pass);
                        return;
                    }
                } else if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
                	initializeUTPasswords();
                	final String pass = (String) utPasswords.get(pc.getIdentifier());
                	logger.debug("USERNAME_TOKEN CALLBACK FOR: "+pc.getIdentifier());
                    if (pass != null) {
                        pc.setPassword(pass);
                        return;
                    }
                }
            }
        }
		
	}
	
	private void initializeUTPasswords(){
		utPasswords = new HashMap<String, String>();
		utPasswords.put("alice", "clarinet");
	}
	
	/**
     * @return the passwords
     */
    public Map<String, String> getPasswords()
    {
        return x509Passwords;
    }

    /**
     * @param passwords the passwords to set
     */
    public void setPasswords(Map<String, String> passwords)
    {
        this.x509Passwords = passwords;
    }

}
