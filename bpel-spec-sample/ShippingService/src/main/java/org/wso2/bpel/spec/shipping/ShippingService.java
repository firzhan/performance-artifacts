package org.wso2.bpel.spec.shipping;

import java.io.ByteArrayInputStream;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.ServiceClient;

public class ShippingService {
	private static final String EPR = "http://localhost:9764/services/ShippingCallbackService";

	public void selectShipper(final String shipperId) {
 System.out.println("selectShipper invoked " + shipperId);
		try {
					// Create a service client
					ServiceClient client = new ServiceClient();

					// Set the endpoint address
					client.getOptions().setTo(new EndpointReference(EPR));
					client.getOptions().setAction("http://www.example.org/ShippingCallback/sendShippingPrice");
					// Make the request and get the response
					client.sendRobust(getPayload(shipperId));

 System.out.println("selectShipper SUCCESSSSSSSSSSSSSSSSSSSSSSS" + shipperId);
				} catch (AxisFault e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XMLStreamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

   System.out.println("selectShipper invoked done exit" + shipperId);
		
	}

	private static OMElement getPayload(String shipperId)
			throws XMLStreamException {
		String payload = "<p:ShippingMessage xmlns:p=\"http://www.example.org/ShippingCallback/\"><shipperId>"
				+ shipperId
				+ "</shipperId><amount>123</amount></p:ShippingMessage>";

		return new StAXOMBuilder(new ByteArrayInputStream(payload.getBytes()))
				.getDocumentElement();
	}
}
