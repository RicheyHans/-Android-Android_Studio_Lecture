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
	// �� �������� ������ �о ���������� ���۱���
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
			// 1. Ŭ���̾�Ʈ ���� ���
			Socket client = server.accept();
			// 2. ��û�� ���� ó���� ���ο� thread���� ���ش�(Ŭ���� ������ �ʰ�)
			new Thread() {
				public void run() {
					try {
					// 3. ��Ʈ���� �����Ѵ�
					InputStreamReader isr = new InputStreamReader(client.getInputStream());	// �������� ���� ��û�� �޴´�
					BufferedReader br = new BufferedReader(isr);
					// 4. �� ������(Ŭ���̾�Ʈ)���� ��û�� �ּҷ� �� ������ ��ɾ(���...) ���ƿ�
					String line = br.readLine();
					System.out.println("line="+line);
					// 5. ��û�� ��ɾ��� ù �ٸ�(�޼��� �κ�) parsing �ؼ� ������ �����Ѵ�
					// Method[]�����ڿ�(���������������ּ�)[]���������ǹ���
					String[] cmd = line.split(" ");
					// ���� �ڿ��� hello�� ���� �ƴѰ��� ����
					if("/hello".equals(cmd[1])) {
						String msg = "<h1>Hello!!!!!!!!</h1>";
						OutputStream os = client.getOutputStream();
						// ȭ�鿡 ������ �ʴ� ��Ÿ ����
						os.write("HTTP/1.0 200 OK \r\n".getBytes());
						os.write("Content-Type: text/html \r\n".getBytes());
						os.write(("Content-Length: "+msg.getBytes().length+"\r\n").getBytes());
						// ����� �ٵ� �����ڸ� ����
						os.write("\r\n".getBytes());
						// ���� ���޵Ǵ� ������
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