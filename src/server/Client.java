package server;

import java.io.IOException;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Logger;

import client.commons.Message;
import client.commons.Result;


public class Client implements Sendable {
	private Socket socket;
	private String name;
	User user = null;
	private boolean clientReachable = true;

	private Instant lastUsage;
	private String token = null;
	private static Logger logger = Logger.getLogger("");
	private static final ArrayList<Client> clients = new ArrayList<>();
	/**
	 * Create a new client object, communicating over the given socket. Immediately
	 * start a thread to receive messages from the client.
	 */
	
	// ID
		private static int clientCounter = 0;
		private int clientID;
	
	public Client(Socket socket) {
		this.socket = socket;
		
		this.setLastUsage(Instant.now());
		
		this.clientID = clientCounter;
		clientCounter++;

		// thread
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("The " + getClientIDAsText() + " has connected" );
				
				try {
					while (Client.this.socket != null) {
						Message msg = Message.receive(socket);

						
						if (msg != null)
							msg.process(Client.this);
						else { // if msg invalid or socket not working
							Client.this.send(new MessageError());
						}

						setLastUsage(Instant.now());
					}
				} catch (Exception e) {
					logger.info("Client " + Client.this.getName() + " disconnected");
				} finally {
					// When the client is no longer reachable, remove authentication and User
					token = null;
					user = null;
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		logger.info("New client created: " + this.getName());
	
	}

	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			// Uninteresting
		}
		
	}
	
	public String toString() {
		return name + ": " + socket.toString();
	}

	
	/**
	 * Send a message to this client. In case of an exception, log the client out.
	 */
	@Override // from Sendable
	public void send(Message msg) {
		try {
			msg.send(socket);
		} catch (IOException e) {
			logger.warning("Client " + Client.this.getName() + " unreachable; logged out");
			this.token = null;
			clientReachable = false;
		}
	}

	public String getName() {
		String name = null;
		if (user != null) name = user.getUsername();
		return name;
	}

	public String getToken() {
		return token;
	}

	public Instant getLastUsage() {
		return lastUsage;
	}

	public void setLastUsage(Instant lastUsage) {
		this.lastUsage = lastUsage;
	}

	public void setUser(User user) {
		this.user = user;
		
	}

	public void setToken(String token) {
		this.token = token;
		
	}

	public static void add(Client client) {
		synchronized (clients) {
			clients.add(client);
		
		}
	}

	public User getUser() {
		return user;
	}
	public String getClientIDAsText() {
		return "Client ID #" + clientID;
	}

}
