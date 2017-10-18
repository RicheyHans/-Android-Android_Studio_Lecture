import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	// �� ���� ����
	// ���������� ���� ���� ���α׷������� request�� ������.
	public static void main(String[] args) {
		try {
			// 1. ������ ����(2000�� �̻��� ��Ʈ ��ȣ�� �������ش�). 
			// ������ �Ǿ��� ������ �ƴ�
			ServerSocket server = new ServerSocket(10004);
			// 2. ��û�� ����Ѵ�. server.accpet();�� ������ �����Ű�� �����̴�.
			Socket client = server.accept();	// ��ġ scanner�� nextó�� ��û�� ���� �� ���� 
												// ��, ���ٿ��� ���. �ؿ� ���� ������� �ʴ´�. 
												// ��û�� ���� client ������ Ŭ���̾�Ʈ�� �ּ�����(Ŭ���̾�Ʈ�� �ּ�)�� ����.
			
			// 3. ���ӵ� client�� stream�� �����Ѵ�.
			// Ŭ���̾�Ʈ �ּ� ������ ������ �� �������� ��Ʈ���� �����ϰ�
			InputStreamReader isr = new InputStreamReader(client.getInputStream());				// output��Ʈ���� Ŭ���̾�Ʈ�� �����°����̹Ƿ�, ��ǲ ��Ʈ�� ���
			BufferedReader br = new BufferedReader(isr);
			
			String temp = "";
			StringBuilder sb = new StringBuilder();
			// 4. �� �پ� �о ����
			while((temp=br.readLine()) != null) {
				sb.append(temp).append("\n");
			}
			
			// 5. ���� ���� ���
			System.out.println(sb.toString());
			
			br.close();
			isr.close();
			client.close();
			server.close();
			
		} catch(Exception e) { e.printStackTrace();}
	}

}
