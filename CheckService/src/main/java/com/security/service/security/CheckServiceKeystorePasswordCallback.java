package com.security.service.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class CheckServiceKeystorePasswordCallback implements CallbackHandler{
	
	private Map<String, String> passwords = new HashMap<String, String>();

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (int index = 0; index < callbacks.length; index++) {
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[index];
            int usage = pc.getUsage();
            if (usage == WSPasswordCallback.DECRYPT || usage == WSPasswordCallback.SIGNATURE) {
                String pass = (String) passwords.get(pc.getIdentifier());
                if (pass != null) {
                    pc.setPassword(pass);
                    return;
                }
            }
        }
	}
	
	/**
     * @return the passwords
     */
    public Map<String, String> getPasswords()
    {
        return passwords;
    }

    /**
     * @param passwords the passwords to set
     */
    public void setPasswords(Map<String, String> passwords)
    {
        this.passwords = passwords;
    }

}
