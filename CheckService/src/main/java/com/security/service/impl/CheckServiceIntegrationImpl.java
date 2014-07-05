package com.security.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

import org.apache.log4j.Logger;

import com.securityintegration.checkservice.servicelayer.SecurityIntegrationLayerPort;
import com.securityintegration.checkservice.servicelayer.ServiceException;
import com.securityintegration.checkservice.webservice.dom.jaxb.ObjectFactory;
import com.securityintegration.checkservice.webservice.dom.jaxb.TEmployee;
import com.securityintegration.checkservice.webservice.dom.jaxb.TEmployeeByIdRequest;
import com.securityintegration.checkservice.webservice.dom.jaxb.TEmployeeByIdResponse;

public class CheckServiceIntegrationImpl implements SecurityIntegrationLayerPort{
	
	private final org.apache.log4j.Logger logger = Logger.getLogger(CheckServiceIntegrationImpl.class);
	private static ObjectFactory factory = new ObjectFactory();

	@Override
	@WebResult(name = "employeeById_Response", targetNamespace = "http://com/securityintegration/checkservice/", partName = "return")
	@WebMethod(action = "http://com/securityintegration/checkservice/servicelayer/getEmployeeById")
	public TEmployeeByIdResponse getEmployeeById(
			@WebParam(partName = "parameters", name = "employeeById_Request", targetNamespace = "http://com/securityintegration/checkservice/") TEmployeeByIdRequest parameters)
			throws ServiceException {
		logger.debug("Received employee by id request for: "+parameters.getEmployeeId());
		
		TEmployeeByIdResponse employeeByIdResponse = factory.createTEmployeeByIdResponse();
		TEmployee employee = factory.createTEmployee();
		employee.setId(parameters.getEmployeeId());
		employee.setName("FOUND -- USER_1");
		employeeByIdResponse.setEmployee(employee);
		logger.debug("Responded employee by id request for: "+parameters.getEmployeeId()+" with "+employee.getName());
		return employeeByIdResponse;
	}

}
