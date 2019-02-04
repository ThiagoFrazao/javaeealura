package lojas.utils;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig=
{@ActivationConfigProperty(propertyName="destinationLookup",propertyValue="java:/jms/topics/CarrinhoComprasTopico"),
 @ActivationConfigProperty(propertyName="destinationType",propertyValue="javax.jms.Topic")})
public class JmsUtils implements MessageListener {
	
	
	@Inject
	private JMSContext jmsContext;	
	@Resource(name="java:/jms/topics/CarrinhoComprasTopico")
	private Destination destination;		

	@Override
	public void onMessage(Message message) {		
		try {
			TextMessage txtMsg = (TextMessage) message;
			JMSProducer producer = jmsContext.createProducer();		
			producer.send(destination, txtMsg.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
