package chattingcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClientSocket {
	String username;
	Socket socket;

	Scanner sc;
	BufferedWriter writer;
	BufferedReader reader;

	public MyClientSocket() {
		try {
			socket = new Socket("192.168.0.132", 2100);
			sc = new Scanner(System.in);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new Thread().start();
			

			System.out.println("아이디를 입력하세요");
			username = sc.nextLine();
			writer.write(username + "\n");
			writer.flush();
			System.out.println(username + "이 서버로 전송되었습니다");

			while (true) {
				String keyboardInputData = sc.nextLine();
				writer.write(keyboardInputData + "\n");
				writer.flush();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	class 읽기전담스레드 implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					String inputData = reader.readLine();
					System.out.println("받은 메세지" + inputData);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		new MyClientSocket();
	}

}
