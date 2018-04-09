package sendmail;

import java.util.Properties;

import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTestContents  extends TimerTask {

	int i = 0;
	
	public void run() {
		// TODO Auto-generated method stub
		// You can use TimeTask run() for JAR
		ConnectDB DB = new ConnectDB();
		ResultSet rs = null;

		String host = "";
		String pwtemp = "";
		String port= "";
        String recipient = "";    //메일을 발송할 이메일 주소를 기재해 줍니다.
        
		try {
			//SetMailInfo SMI = new SetMailInfo();
			//SMI.setMailData();
			PreparedStatement Mailinfo = DB.connect();
			Mailinfo.setLong(1, 0);//Parameter index out of range ( 0 < 1 )(id == 0)
			rs = Mailinfo.executeQuery();
			
			while(rs.next()) {
				host = rs.getString("host");
				pwtemp = rs.getString("pw");
				port = rs.getString("port");
				recipient = rs.getString("recipient");
			}
			
			i++;
			final String username = "john0714";       //네이버 이메일 주소중 @ naver.com앞주소만 기재합니다.
			final String password = pwtemp;			//비번 입력(final)
				
		    // 메일 내용
		    String subject = "Send Mail Test Subject";
		    String body = "Send Mail Test Contents";
		        
		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", host);//host naver
		    props.put("mail.smtp.port", port);//port naver(465)
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.ssl.enable", "true");
		    props.put("mail.smtp.ssl.trust", host);//ssl check
		          
		    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		        }
		    });
		    session.setDebug(true); //for debug
		          
		    Message mimeMessage = new MimeMessage(session);//로그인한 세션을 통해 메일 전송(activation.jar必要)
		    mimeMessage.setFrom(new InternetAddress("john0714@naver.com"));//메일 보내는 사람
		    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));//메일 받을 사람
		    mimeMessage.setSubject(subject);
		    mimeMessage.setText(body);
		    Transport.send(mimeMessage);
			
		    System.out.println("Message Send Success, Count : "+i);

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException sqle){}
		}
	}
}
