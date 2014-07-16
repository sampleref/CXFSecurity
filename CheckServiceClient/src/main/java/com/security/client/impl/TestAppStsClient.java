package com.security.client.impl;

import org.apache.cxf.ws.security.trust.STSClient;
import org.apache.ws.security.util.DOM2Writer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAppStsClient {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig/checkserviceclient.config.xml");
		 
		STSClient stsClient = (STSClient) context.getBean("checkservice.sts-client");
		//STSClient stsClient = (STSClient) context.getBean("nosecurity-stsclient");
		
		//System.out.println(stsClient.requestSecurityToken().toString());
		System.out.println(DOM2Writer.nodeToString(stsClient.requestSecurityToken("http://localhost:8080/CheckService/services/SecurityIntegrationService").getToken()));
	}
}
