package org.wso2.bpel.spec.computeprice;

import java.io.ByteArrayInputStream;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.ServiceClient;

public class ComputePriceService{
	private static final String EPR = "http://127.0.0.1:9764/services/ComputePriceCallbackService";
	public void computePrice(final String orderId){

        System.out.println("computePrice invoked " + orderId);
		try {
					//Create a service client
					ServiceClient client = new ServiceClient();

					//Set the endpoint address
					client.getOptions().setTo(new EndpointReference(EPR));
					client.getOptions().setAction("http://www.example.org/InvoiceCallback/computePriceCallback");
					//Make the request and get the response
					client.sendRobust(getPayload(orderId));
				} catch (AxisFault e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XMLStreamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		System.out.println("computePrice invoked done" + orderId);
	}
	
    private static OMElement getPayload(String orderId) throws XMLStreamException {
        String payload = "<p:computePriceCallback xmlns:p=\"http://www.example.org/InvoiceCallback/\"><orderId>"+ orderId +
        "</orderId><price>1000</price></p:computePriceCallback>";

        return new StAXOMBuilder(new ByteArrayInputStream(payload.getBytes())).getDocumentElement();
    }
}
