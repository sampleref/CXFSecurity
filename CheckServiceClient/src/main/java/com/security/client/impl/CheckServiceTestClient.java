package com.security.client.impl;

import com.securityintegration.checkservice.servicelayer.SecurityIntegrationLayerPort;
import com.securityintegration.checkservice.servicelayer.ServiceException;
import com.securityintegration.checkservice.webservice.dom.jaxb.ObjectFactory;
import com.securityintegration.checkservice.webservice.dom.jaxb.TEmployeeByIdRequest;
import com.securityintegration.checkservice.webservice.dom.jaxb.TEmployeeByIdResponse;

public class CheckServiceTestClient {
	
	private static ObjectFactory factory = new ObjectFactory();
	
	private SecurityIntegrationLayerPort integrationLayerPort;

	public SecurityIntegrationLayerPort getIntegrationLayerPort() {
		return integrationLayerPort;
	}

	public void setIntegrationLayerPort(
			SecurityIntegrationLayerPort integrationLayerPort) {
		this.integrationLayerPort = integrationLayerPort;
	}
	
	public String getNameOfEmployeeById(int id) throws ServiceException{
		TEmployeeByIdRequest byIdRequest = factory.createTEmployeeByIdRequest();
		byIdRequest.setEmployeeId(id);
		//byIdRequest.setOtherinfo(new DataHandler("textstring", "text")); //Usually used for File uploads with filestream as source
		TEmployeeByIdResponse byIdResponse = integrationLayerPort.getEmployeeById(byIdRequest);
		return byIdResponse.getEmployee().getName() + " with Id: " + byIdResponse.getEmployee().getId();
	}
	
}
