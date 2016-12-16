import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class PostTest {

	public static void main(String[] args) {

		try {
			// NW+DNiwsHcRkn3S/iGZviQ==
			
			// getGoods
			//String param = "companyCode" + "=" + URLEncoder.encode("NW+DNiwsHcRkn3S/iGZviQ==", "UTF-8");
			//param += "&" + URLEncoder.encode("catId", "UTF-8") + "=" + URLEncoder.encode("C330000000", "UTF-8");
			
			// getStatus
			String param = "companyCode" + "=" + URLEncoder.encode("NW+DNiwsHcRkn3S/iGZviQ==", "UTF-8");
			param += "&" + URLEncoder.encode("pOid", "UTF-8") + "=" + URLEncoder.encode("2016777314", "UTF-8");
			param += "&" + URLEncoder.encode("pOid", "UTF-8") + "=" + URLEncoder.encode("1916777314", "UTF-8");
			param += "&" + URLEncoder.encode("pOid", "UTF-8") + "=" + URLEncoder.encode("1816777314", "UTF-8");
			param += "&" + URLEncoder.encode("pOid", "UTF-8") + "=" + URLEncoder.encode("1716777314", "UTF-8");
			/*
			param += "&" + URLEncoder.encode("goodsId", "UTF-8") + "=" + URLEncoder.encode("00000000000000000263", "UTF-8");
			param += "&" + URLEncoder.encode("goodsCnt", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
			param += "&" + URLEncoder.encode("goodsPrice", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8");
			param += "&" + URLEncoder.encode("pOid", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8");
			param += "&" + URLEncoder.encode("snsCode", "UTF-8") + "=" + URLEncoder.encode("0052", "UTF-8");
				*/		
			// pOid : 30자리 포락자체 주문번호
			//String param = "companyCode=" + URLEncoder.encode("POC0000208", "UTF-8");
			//param += "&" + URLEncoder.encode("pOid", "UTF-8") + "=" + URLEncoder.encode("00000000000000000130", "UTF-8");
			
			//URL url = new URL("http://isapi.cootoo.co.kr/b2b/1.0/getGoods2.json");
			//URL url = new URL("http://iapi.cootoo.co.kr/b2b2/1.0/getGoods.json");
			//URL url = new URL("http://isapi.cootoo.co.kr/b2b/1.0/getCouponStatus.json");
			//URL url = new URL("http://isapi.cootoo.co.kr/b2b/1.0/payCoupon.json");
			//URL url = new URL("http://isapi.cootoo.co.kr/b2b/1.0/payCouponWithImageCreation.json");
			URL url = new URL("http://isapi.cootoo.co.kr/b2b/1.0/getCouponStatusForMulti.json");
			
			
			System.out.println("@@@param==="+param);
			URLConnection conn = url.openConnection();
			
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			DataOutputStream out = null;
			
			try {
				out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(param);
				out.flush();
			} finally {
				if (out != null) out.close();
			}
			
			InputStream is = conn.getInputStream();
			Scanner scan = new Scanner(is);
			
			int line = 1;
			while (scan.hasNext()) {
				String str = scan.nextLine();
				System.out.println((line++) + ":" + str);
			}
			scan.close();
			
		} catch (MalformedURLException e) {
			System.out.println("The URL address is incorrect.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("It can't connect to the web page.");
			e.printStackTrace();
		}		
		
	}

}
