package example.remote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import suncertify.util.ApplicationConstants;
import suncertify.util.PropertyManager;

public class CalculatorClient {

	public static void main(String[] args) {
		try {
			PropertyManager properties = PropertyManager.getInstance();
			int port = Integer
					.parseInt(properties
							.getProperty(ApplicationConstants.KEY_PROPERTY_NETWORK_PORT));
			String host = properties
					.getProperty(ApplicationConstants.KEY_PROPERTY_NETWORK_HOST);

			String name = "Calculator";
			Registry registry = LocateRegistry.getRegistry(host, port);
			Calculator c = (Calculator) registry.lookup(name);

			System.out.println(c.sub(4, 3));
			System.out.println(c.add(4, 5));
			System.out.println(c.mul(3, 6));
			System.out.println(c.div(9, 3));
		} catch (RemoteException re) {
			System.out.println();
			System.out.println("RemoteException");
			System.out.println(re);
		} catch (NotBoundException nbe) {
			System.out.println();
			System.out.println("NotBoundException");
			System.out.println(nbe);
		} catch (java.lang.ArithmeticException ae) {
			System.out.println();
			System.out.println("java.lang.ArithmeticException");
			System.out.println(ae);
		}
	}
}