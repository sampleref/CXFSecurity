package com.security.sts.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class StsPasswordCallbackHandler implements CallbackHandler{
	
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
                	initializeX509Passwords();
                    final String pass = (String) x509Passwords.get(pc.getIdentifier());
                    if (pass != null) {
                        pc.setPassword(pass);
                        return;
                    }
                } else if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
                	initializeUTPasswords();
                	final String pass = (String) utPasswords.get(pc.getIdentifier());
                    if (pass != null) {
                        pc.setPassword(pass);
                        return;
                    }
                }
            }
        }
		
	}
	
	private void initializeX509Passwords(){
		x509Passwords = new HashMap<String, String>();
		x509Passwords.put("stskeyalias", "stskeypassword");
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
