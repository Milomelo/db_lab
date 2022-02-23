package chattingcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class MyServerSocket {

	ServerSocket serverSocket;
	List<고객전담스레드> 고객리스트;

	public MyServerSocket() {
		try {
			serverSocket =new ServerSocket(2000);
			고객리스트 = new Vector<>();

			while (true) {			
                Socket socket = serverSocket.accept(); // main 스레드
				System.out.println("클라이언트가 연결됨");
				고객전담스레드 t = new 고객전담스레드(socket);
				고객리스트.add(t);
				System.out.println("고객리스트 크기:" + 고객리스트.size());
				new Thread(t).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class 고객전담스레드 implements Runnable {
		String username;
		Socket socket;
		BufferedReader reader;
		BufferedWriter writer;
		boolean islogin;

		public 고객전담스레드(Socket socket) {
			this.socket = socket;
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void chatPublic(String msg) {

			try {
				for (고객전담스레드 t : 고객리스트) {
					if (t != this) {
						t.writer.write(t.socket.getRemoteSocketAddress() + username + "+" + msg + "\n");
						writer.flush();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void chatPrivate(String receiver, String msg) {
			try {
				for (고객전담스레드 t : 고객리스트) {
					if (t.username.equals(receiver)) {
						t.writer.write(t.socket.getRemoteSocketAddress() + "[귓속말]" + username + ":" + msg + "\n");
						writer.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void t1p(String inputData) {
			String[] token = inputData.split(":");
			String protocol = token[0];
			if (protocol.equals("ALL")) {
				String msg = token[1];
				chatPublic(msg);
			} else if (protocol.equals("CHAT")) {
				String receiver = token[1];
				String msg = token[2];
				chatPrivate(receiver, msg);
			} else {
				System.err.println("프로토콜 없음");
			}

		}

		@Override
		public void run() {
			try {
				username = reader.readLine();
				islogin = true;
			} catch (Exception e) {
				islogin = false;
				System.out.println("username을 받지 못했습니다");
			}
			while (islogin) {
				try {
					String inputData = reader.readLine();

				} catch (Exception e) {
					try {
						System.out.println("통신실패" + e.getMessage());
						islogin = false;
						reader.close();
						writer.close();
						socket.close();

					} catch (Exception e2) {
						System.out.println("연결해제 프로세스 실패" + e2.getMessage());
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		new MyServerSocket();
	}
}
