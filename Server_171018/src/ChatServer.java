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

class Server extends Thread {		// accpet가 지속적으로 동작하기 위해 
	ServerSocket server;
	public boolean runFlag = true;
	
	
	// 0. 서버 소켓 생성
	public Server(int port) {
		try{
		server = new ServerSocket(port);
		} catch(Exception e) {	}
	}
	
	public void run() {
		System.out.println("server is running...");
		// 1. 클라이언트 요청 대기. accept를 계속 받아주는 역할
		while(runFlag) {
			try {
			Socket client = server.accept();	// 여기서 대기. 아래쪽 코드는 접속 요청을 받기 전 까지는 실행되지 않는다.
			// 클라이언트의 동작을 여기서 처리해준다고 하면, 동시에 여러 클라이언트에서 요청이 들어오면 동시에 처리가 불가능하다.
			// 그러므로, 클라이언트의 요청은 다른 스레드로 넘겨준다.
			new ClientProcess(client).start();
			} catch(Exception e) {	}
		}
	}
	
}

// 클라이언트의 요청을 개별 thread로 처리하는 클래스. 동시에 10명이 들어오면 이 객체가 10개가 생성된다.
class ClientProcess extends Thread {
	Socket client;
	public ClientProcess(Socket socket) {
		this.client = client;
	}
	
	public void run() {
		try {
		InputStreamReader isr = new InputStreamReader(client.getInputStream());				// output스트림은 클라이언트로 보내는개념이므로, 인풋 스트림 사용
		BufferedReader br = new BufferedReader(isr);
		
		String msg = "";
		// StringBuilder sb = new StringBuilder();
		// 4. 한 줄씩 읽어서 저장
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