package SendMail;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��ȡ��¼������socket���ص���Ϣ�����ʼ�֪ͨ�����Ա
 * 
 * @author gds_lucky
 * 
 */
public class SendMail {

	private ServerSocket server;

	String smtp = "smtp.exmail.qq.com";// smtp������
	String from = "gongdesheng@sunnymum.com";// �ʼ���ʾ����
	String to = "gongdesheng_lucky@126.com";// �ռ��˵��ʼ���ַ
	String copyto = "";// �������ʼ���ַ
	String subject = "sunny_mum-ʵʱ���";// �ʼ�����
	int port = 50104;

	String username = "gongdesheng@sunnymum.com";// ��������ʵ���˻���
	String password = "Gds1234";// ����������

	public static void main(String[] args) {

		new Thread() {
			public void run() {
				try {
					new SendMail().startService();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	private void startService() throws IOException {
		server = new ServerSocket(port);// portΪsdk����������Ķ˿ںţ���Ҫ����һ��
		while (true) {
			Socket socket = server.accept();
			DataInputStream dos = new DataInputStream(socket.getInputStream());
			String content = dos.readUTF();// ��ȡsocket���ص�����
			Mail.sendAndCc(smtp, from, to, copyto, subject, content, username,
					password);// ���쳣��������ʼ�֪ͨ
		}
	}

}
