import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server(10004);
		server.start();
	}

}

class Server extends Thread {		// accpet�� ���������� �����ϱ� ���� 
	ServerSocket server;
	public boolean runFlag = true;
	
	
	// 0. ���� ���� ����
	public Server(int port) {
		try{
		server = new ServerSocket(port);
		} catch(Exception e) {	}
	}
	
	public void run() {
		System.out.println("server is running...");
		// 1. Ŭ���̾�Ʈ ��û ���. accept�� ��� �޾��ִ� ����
		while(runFlag) {
			try {
			Socket client = server.accept();	// ���⼭ ���. �Ʒ��� �ڵ�� ���� ��û�� �ޱ� �� ������ ������� �ʴ´�.
			// Ŭ���̾�Ʈ�� ������ ���⼭ ó�����شٰ� �ϸ�, ���ÿ� ���� Ŭ���̾�Ʈ���� ��û�� ������ ���ÿ� ó���� �Ұ����ϴ�.
			// �׷��Ƿ�, Ŭ���̾�Ʈ�� ��û�� �ٸ� ������� �Ѱ��ش�.
			new ClientProcess(client).start();
			} catch(Exception e) {	}
		}
	}
	
}

// Ŭ���̾�Ʈ�� ��û�� ���� thread�� ó���ϴ� Ŭ����. ���ÿ� 10���� ������ �� ��ü�� 10���� �����ȴ�.
class ClientProcess extends Thread {
	Socket client;
	public ClientProcess(Socket socket) {
		this.client = client;
	}
	
	public void run() {
		try {
		InputStreamReader isr = new InputStreamReader(client.getInputStream());				// output��Ʈ���� Ŭ���̾�Ʈ�� �����°����̹Ƿ�, ��ǲ ��Ʈ�� ���
		BufferedReader br = new BufferedReader(isr);
		
		String msg = "";
		// StringBuilder sb = new StringBuilder();
		// 4. �� �پ� �о ����
		while(!"exit".equals(msg=br.readLine())) {
			if(msg == null) {
				break;
			}
			System.out.println(client.getInetAddress()+":"+msg.toString());
		}
		
		br.close();
		isr.close();
		client.close();
		} catch(Exception e) { }
	}
}