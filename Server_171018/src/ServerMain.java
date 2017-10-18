import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	// 웹 서버 생성
	// 브라우저에서 내가 만든 프로그램쪽으로 request를 날린다.
	public static void main(String[] args) {
		try {
			// 1. 서버를 생성(2000번 이상의 포트 번호를 지정해준다). 
			// 생성만 되었지 동작은 아님
			ServerSocket server = new ServerSocket(10004);
			// 2. 요청을 대기한다. server.accpet();가 서버를 실행시키는 라인이다.
			Socket client = server.accept();	// 마치 scanner의 next처럼 요청이 있을 때 까지 
												// 딱, 이줄에서 대기. 밑에 줄이 실행되지 않는다. 
												// 요청이 오면 client 변수에 클라이언트의 주소정보(클라이언트의 주소)가 담긴다.
			
			// 3. 접속된 client와 stream을 생성한다.
			// 클라이언트 주소 정보가 있으니 두 라인으로 스트림을 생성하고
			InputStreamReader isr = new InputStreamReader(client.getInputStream());				// output스트림은 클라이언트로 보내는개념이므로, 인풋 스트림 사용
			BufferedReader br = new BufferedReader(isr);
			
			String temp = "";
			StringBuilder sb = new StringBuilder();
			// 4. 한 줄씩 읽어서 저장
			while((temp=br.readLine()) != null) {
				sb.append(temp).append("\n");
			}
			
			// 5. 최종 내용 출력
			System.out.println(sb.toString());
			
			br.close();
			isr.close();
			client.close();
			server.close();
			
		} catch(Exception e) { e.printStackTrace();}
	}

}
