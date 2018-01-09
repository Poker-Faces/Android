package SendMail;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 获取登录工程里socket返回的信息，并邮件通知相关人员
 * 
 * @author gds_lucky
 * 
 */
public class SendMail {

	private ServerSocket server;

	String smtp = "smtp.exmail.qq.com";// smtp服务器
	String from = "gongdesheng@sunnymum.com";// 邮件显示名称
	String to = "gongdesheng_lucky@126.com";// 收件人的邮件地址
	String copyto = "";// 抄送人邮件地址
	String subject = "sunny_mum-实时监测";// 邮件标题
	int port = 50104;

	String username = "gongdesheng@sunnymum.com";// 发件人真实的账户名
	String password = "Gds1234";// 发件人密码

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
		server = new ServerSocket(port);// port为sdk工程里监听的端口号，需要保持一致
		while (true) {
			Socket socket = server.accept();
			DataInputStream dos = new DataInputStream(socket.getInputStream());
			String content = dos.readUTF();// 获取socket返回的内容
			Mail.sendAndCc(smtp, from, to, copyto, subject, content, username,
					password);// 将异常结果发送邮件通知
		}
	}

}
