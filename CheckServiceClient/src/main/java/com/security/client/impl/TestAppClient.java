package com.security.client.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.securityintegration.checkservice.servicelayer.ServiceException;

public class TestAppClient {
	
public static void main(String[] args) throws ServiceException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig/checkserviceclient.config.xml");
 
		CheckServiceTestClient client = (CheckServiceTestClient) context.getBean("checkServiceClient");
		
		System.out.println(client.getNameOfEmployeeById(949009509));
	}
}
