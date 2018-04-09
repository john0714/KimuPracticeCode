package sendmail;

import java.util.Calendar;
import java.util.Timer;

public class SendMailTest {
	public static void main(String args[]) throws Exception {
		try {
			Timer time = new Timer();
			// 정해진 시간에 주기적인 작업하기(밤12시)
			Calendar date = Calendar.getInstance();
			date.set(Calendar.HOUR_OF_DAY, 24);
			date.set(Calendar.MINUTE, 00);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);
			
			//time.scheduleAtFixedRate(new SendMailTestContents(), date.getTime(), 1000);//특정 시간이후 1초마다 메일 발송
			//time.scheduleAtFixedRate(new SendMailTestContents(), 1000, 24*60*60*1000);//1초 후에 24시간마다 메일발송
			time.scheduleAtFixedRate(new SendMailTestContents(), 1000, 1*1*10*1000);//1초 후, 10초마다 메일발송
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
