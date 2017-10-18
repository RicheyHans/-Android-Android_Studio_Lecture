import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		// 특정 ip와 port를 가진 서버에 접속해서, 메시지를 전송하는 프로그램
		Client client = new Client("192.168.1.120", 10004);
		client.start();
	}

}

class Client extends Thread {	// 강사님이 채팅 서버를 실행 시켜 놓으면 내가 채팅 클라이언트로 메시지 전달
	public boolean runFlag = true;
	String ip;
	int port;
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void run() {
		try {
		// 1. 서버와 접속
		Socket socket = new Socket(ip, port);
		// 소켓과 서버간의 스트림을 열어준다.
		OutputStream os = socket.getOutputStream();
		// 2. 입력 받을 스캐너 생성
		Scanner scanner = new Scanner(System.in);
		while(runFlag) {
			String msg = scanner.nextLine();
			// 3. 내가 입력한 값이 exit이면 종료. 아니면 메시지를 서버로 전송
			if("exit".equals(msg)) {
				runFlag = false;
			} 
				
			msg = msg + "\r\n";
			os.write(msg.getBytes());		// 스래너의 라인은 엔터를 삭제해버림
			os.flush();
			
		}
		os.close();
		socket.close();
		} catch(Exception e) { }	
	}
}