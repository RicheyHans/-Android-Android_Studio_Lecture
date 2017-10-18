import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	// 웹 브라우저의 파일을 읽어서 웹브라우저에 전송까지
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebServer server = new WebServer(8089);
		server.start();
	}

}

class WebServer extends Thread {
	ServerSocket server;
	public boolean runFlag = true;
	String root = "";
	
	public WebServer(int port) {
		try {
		server = new ServerSocket(port);
		} catch(Exception e) { e.printStackTrace();	}

	} 	
	public void run() {
		while(runFlag) {
			try {
			// 1. 클라이언트 연결 대기
			Socket client = server.accept();
			// 2. 요청에 대한 처리를 새로운 thread에서 해준다(클래스 만들지 않고)
			new Thread() {
				public void run() {
					try {
					// 3. 스트림을 연결한다
					InputStreamReader isr = new InputStreamReader(client.getInputStream());	// 브라우저가 날린 요청을 받는다
					BufferedReader br = new BufferedReader(isr);
					// 4. 웹 브라우저(클라이언트)에서 요청한 주소로 줄 단위의 명령어가(헤더...) 날아옴
					String line = br.readLine();
					System.out.println("line="+line);
					// 5. 요청된 명령어의 첫 줄만(메서드 부분) parsing 해서 동작을 결정한다
					// Method[]로컬자원(도메인을제외한주소)[]프로토콜의버전
					String[] cmd = line.split(" ");
					// 로컬 자원이 hello일 경우아 아닌경우로 구분
					if("/hello".equals(cmd[1])) {
						String msg = "<h1>Hello!!!!!!!!</h1>";
						OutputStream os = client.getOutputStream();
						// 화면에 보이지 않는 메타 정보
						os.write("HTTP/1.0 200 OK \r\n".getBytes());
						os.write("Content-Type: text/html \r\n".getBytes());
						os.write(("Content-Length: "+msg.getBytes().length+"\r\n").getBytes());
						// 헤더와 바디 구분자를 전송
						os.write("\r\n".getBytes());
						// 실제 전달되는 데이터
						os.write(msg.getBytes());
						os.flush();
					} else {
						String root = "C:\\Temp";
						String fileName = "text.txt";
						File file = new File(root, fileName);
						
						BufferedReader fileBr = null;
						try {
							fileBr = new BufferedReader(new FileReader(file));
							String fileLine;
							while( (fileLine=fileBr.readLine()) != null ) {
								System.out.println(fileLine);
							}
						} catch(Exception e) { e.printStackTrace();	}
					}
				} catch(Exception e) { e.printStackTrace(); }
				}
			}.start();
			} catch(Exception e) { e.printStackTrace(); }
		}
	}
}