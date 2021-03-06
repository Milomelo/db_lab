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
	List<???????㽺????> ????????Ʈ;

	public MyServerSocket() {
		try {
			serverSocket =new ServerSocket(2000);
			????????Ʈ = new Vector<>();

			while (true) {			
                Socket socket = serverSocket.accept(); // main ??????
				System.out.println("Ŭ???̾?Ʈ?? ??????");
				???????㽺???? t = new ???????㽺????(socket);
				????????Ʈ.add(t);
				System.out.println("????????Ʈ ũ??:" + ????????Ʈ.size());
				new Thread(t).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ???????㽺???? implements Runnable {
		String username;
		Socket socket;
		BufferedReader reader;
		BufferedWriter writer;
		boolean islogin;

		public ???????㽺????(Socket socket) {
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
				for (???????㽺???? t : ????????Ʈ) {
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
				for (???????㽺???? t : ????????Ʈ) {
					if (t.username.equals(receiver)) {
						t.writer.write(t.socket.getRemoteSocketAddress() + "[?ӼӸ?]" + username + ":" + msg + "\n");
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
				System.err.println("???????? ????");
			}

		}

		@Override
		public void run() {
			try {
				username = reader.readLine();
				islogin = true;
			} catch (Exception e) {
				islogin = false;
				System.out.println("username?? ???? ???߽??ϴ?");
			}
			while (islogin) {
				try {
					String inputData = reader.readLine();

				} catch (Exception e) {
					try {
						System.out.println("???Ž???" + e.getMessage());
						islogin = false;
						reader.close();
						writer.close();
						socket.close();

					} catch (Exception e2) {
						System.out.println("???????? ???μ??? ????" + e2.getMessage());
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		new MyServerSocket();
	}
}
