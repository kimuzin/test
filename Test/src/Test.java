import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import org.apache.jasper.tagplugins.jstl.core.Url;



import com.sun.net.httpserver.HttpServer;



import sun.net.www.http.HttpClient;



public class Test {

	
	static String date = "2014-01-18";
	static String dateType1 = "'" + date + "'";  ; //"'2014-01-14'";
	static String dateType2 = date.replaceAll("-", ""); //"20140114";
	
	
	public static void testMethod2(Map<String, Object> testParam){
		String version = (String)testParam.get("version");
		System.out.println("@@@@@@@@@@==="+version);

		
	}
	
	public static void testMethod(Map<String, Object> testParam){
		testMethod2(testParam);
	}
	public static String test(){
		return "Y";
	}
	public static void main(String[] args) throws Exception {
		
		try {
			
			String url = "http://www.cjmall.com/joinmall/gate.jsp?gate_code=3262&wacode=000600011289&url=http://www.cjmall.com/prd/front/search/search_main.jsp?srch_gb=&srch_value=%BC%BC%BD%C7%B8%AE%BE%C6&tab_num=1&refer=&srch_del=&isRecentWord=1&srch_disp_value=%BC%BC%BD%C7%B8%AE%BE%C6&BSCPN=NSM";
			
			

			   //String newPath = url.substring( 0, url.indexOf("tab_num")-1);
			   String tmp = "12312300tteteeeee";
			   int newPath= tmp.indexOf("eee")+2;
			   System.out.println("@@@==="+newPath);
			
			/*
			String tmp_end_dt = "20161015000000";
			String dp_end_dt = tmp_end_dt.substring(0, 8)+"235959";
			System.out.println("@@@@@@@@@@@@===="+dp_end_dt);

			String test = "tes=t입니다";
			System.out.println("### > "+test.indexOf("=")); 
			System.out.println("@@@ > "+test.substring(1)); 
			
			if(test.indexOf("=") == 0){
				System.out.println("#!@#@#!@#!@#");
			}
			
			
			String url = "http://isapi.cootoo.co.kr/b2b/1.0/getCouponStatusForMulti.json";
			String param = "companyCode" + "=" + URLEncoder.encode("NW+DNiwsHcRkn3S/iGZviQ==", "UTF-8");
			
			String pOid =  "1606291000014515";
			param += "&" + URLEncoder.encode("pOid", "UTF-8") + "=" + URLEncoder.encode(pOid, "UTF-8");
			String response = MyUtil.requestPost(url, null, param);
			System.out.println("@@@@@@+==="+response);
			return;
			*/
			//URL url = new URL("https://iapi.cootoo.co.kr/b2b2/1.0/getGood.json");
			//URLConnection conn = url.openConnection();
			//MyUtil.requestPost("", null, date);
			//String postData = "{'companyCode':'','catId':'','goodsId':'','page':'','list_per_page':'' }";
			/*
			String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("hset", "UTF-8");
			data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("VocPointTargetInfo:POINT_LOCKER", "UTF-8");
			data += "&" + URLEncoder.encode("field", "UTF-8") + "=" + URLEncoder.encode("query", "UTF-8");
			data += "&" + URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode("SELECT CLIENT_ID, CJ_ONE_POINT_REQUESTED FROM [POINT_DB].[dbo].[T_PNT_API_CJ_ONE_POINT_REQUEST_HIST] with (nolock) where service_id = 'POINT_LOCKER' and result_code='400' and seq > 125471589 and campaign_id != 'E_256E4F9C-94F2-46EB-8CA6-9AA7C27D95D4' ORDER BY seq desc", "UTF-8");
			*/
//			String response = MyUtil.requestPost("http://isapi.cootoo.co.kr/b2b/1.0/getGoods.json", null, data);
//			String response = MyUtil.requestPost("http://point.pointbank.interest.me/point_api/select_redis_info.do", null, data);			
//			String value = "SELECT CLIENT_ID, CJ_ONE_POINT_REQUESTED FROM [POINT_DB].[dbo].[T_PNT_API_CJ_ONE_POINT_REQUEST_HIST] with (nolock) where service_id = 'POINT_LOCKER' and result_code='400' and seq > 125471589 and campaign_id != 'E_256E4F9C-94F2-46EB-8CA6-9AA7C27D95D4' ORDER BY seq desc";
			
			//String url = URLEncoder.encode("http://point.pointbank.interest.me/point_api/select_redis_info.do?type=hset&key=VocPointTargetInfo:POINT_LOCKER&field=query&value=SELECT CLIENT_ID, CJ_ONE_POINT_REQUESTED FROM [POINT_DB].[dbo].[T_PNT_API_CJ_ONE_POINT_REQUEST_HIST] with (nolock) where service_id = 'POINT_LOCKER' and result_code='400' and seq > 125471589 and campaign_id != 'E_256E4F9C-94F2-46EB-8CA6-9AA7C27D95D4' ORDER BY seq desc", "UTF-8");
			//System.out.println("@@@@===="+url);
			//String response = MyUtil.requestGet(url, null);	
			
			//String response = MyUtil.requestPost("http://www.naver.com", null, postData);
			//JsonElement jsonElement = jsonParser.parse(response);
//			System.out.println(response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String overlap = test();
		//System.out.println("@@@===="+overlap);
		
		/*
		Map<String, Object> testParam = new HashMap<String, Object>();
		
		testParam.put("version", "1");
		
		testMethod(testParam);
		*/
		
		/*
		Map<String, Object> igawParams = new HashMap<String, Object>();
		igawParams.put("usn", "29351785%2c1&");
		String tmp = (String) igawParams.get("usn");
		System.out.println("@@@tmp==="+tmp);
		String mcode = "";
		String version = "";
		String tmps [] = tmp.split(",");
		if(tmps.length > 1){
			igawParams.remove("usn");
			
			 mcode = tmp.split(",")[0];
			 version = tmp.split(",")[1];
			 
			 igawParams.put("usn", mcode);
			 igawParams.put("version", version);
		}					
		
		System.out.println("# IGAW CALLBACK # :"+igawParams);		
		*/
		
	/*
		Map<String, Object> tnkParams = new HashMap<String, Object>();
		String test = (String) tnkParams.get("test");
		
		String test2 = tnkParams.get("test")+",1";
		System.out.println("@@=="+test2);
		
		if("1".equals(test)){
			System.out.println("@@@@");
		}
		tnkParams.put("md_user_nm", "");
		String tmp = (String) tnkParams.get("md_user_nm");
		System.out.println("###==="+tmp);

		String mcode = "";
		String version = "";
		String tmps [] = tmp.split(",");
		if(tmps.length > 1){
			tnkParams.remove("md_user_nm");
			
			 mcode = tmp.split(",")[0];
			 version = tmp.split(",")[1];
			 
			 tnkParams.put("md_user_nm", mcode);
			 tnkParams.put("version", version);
		}
		
		System.out.println("@@@@##=="+tnkParams.get("md_user_nm"));
		System.out.println("@@@@=="+mcode);
		System.out.println("@@@@=="+version);
	
		*/
  /*
		date = "2015-10-02";
		dateType1 = "'" + date + "'";
		dateType2 = date.replaceAll("-", "");		
		
		//query();
		Long nonmoney_point = 560L;
		Long nonmoney_point_newly_added = 30L;
		Long sum_nonmoney_point = nonmoney_point + nonmoney_point_newly_added;
		
		String auto_add_check = "1";
		Long auto_add_standard_point = 50L;
				
		if(auto_add_check.equals("1") && sum_nonmoney_point >= auto_add_standard_point){
			System.out.println("@@@@");
		}
	*/	
	}
	
	
	
	
	public static void query(){
		
		String sql = "SELECT B.DAY, A.newbie_gauge_CNT, B.clientID_CNT, C.action_CNT, E.moneyPoint_SUM, F.AD_client_CNT,  G.money_client_CNT, H.newbie_cjone_CNT, I.AD_moneyPoint_SUM, J.GIFT_moneyPoint_SUM, K.SUGGEST_moneyPoint_SUM FROM (" +
				"    SELECT CONVERT(VARCHAR(40), reg_date, 11) as DAY, COUNT(*) as newbie_gauge_CNT" +
				"    FROM [POINT_DB].[dbo].[T_PNT_API_NONMONEY_POINT_CLIENT_ADD_PERACTION_HIST] with   (nolock)" +
				"    where " +
				"   service_id='POINT_LOCKER' " +
				"   and reg_date>=" + dateType1  +
				"    and action='NEWBIE'" +
				"    group by CONVERT(VARCHAR(40), reg_date, 11)" +
				"  ) A FULL OUTER JOIN (" +
				"  " +
				"    SELECT CONVERT(VARCHAR(40), reg_date, 11) as DAY, COUNT(distinct client_id) clientID_CNT" +
				"    FROM [POINT_DB].[dbo].[T_PNT_API_NONMONEY_POINT_CLIENT_ADD_PERACTION_HIST]  with  (nolock)" +
				"    where " +
				"   service_id='POINT_LOCKER' " +
				"   and  reg_date>=" + dateType1 + 
				"    group by CONVERT(VARCHAR(40), reg_date, 11)" +
				"  ) B " +
				" ON B.DAY = A.DAY" +
				" " +
				" FULL OUTER JOIN" +
				"  (SELECT CONVERT(VARCHAR(40), reg_date, 11) as DAY, COUNT(*) action_CNT" +
				"    FROM [POINT_DB].[dbo].[T_PNT_API_NONMONEY_POINT_CLIENT_ADD_PERACTION_HIST]  with (nolock)" +
				"    where " +
				"   service_id='POINT_LOCKER' " +
				"  and  reg_date>=" + dateType1 + 
				"    group by CONVERT(VARCHAR(40), reg_date, 11)" +
				" ) C" +
				"  ON B.DAY = C.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" (SELECT CONVERT(VARCHAR(40), add_date, 11) as DAY, sum(sum_add_point) sumOfMoneyPoint" +
				"  FROM [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_CAMPAIGN_ALLDAY_HIST] with (nolock)" +
				"  where service_id='POINT_LOCKER' " +
				"  and add_date>=" + dateType1 + 
				"  group by CONVERT(VARCHAR(40), add_date, 11)" +
				" ) D" +
				" ON B.DAY=D.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" ( select substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2) as DAY, SUM(sum_add_point) as moneyPoint_SUM" +
				"  from [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_CAMPAIGN_ALLDAY_HIST] with (nolock)" +
				"  where service_id='POINT_LOCKER' and date_as_str>=" + dateType2 + 
				"  group by substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2)" +
				" ) E" +
				" on B.DAY = E.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" (select CONVERT(VARCHAR(40), reg_date, 11) as DAY, COUNT(distinct client_id) AD_client_CNT" +
				"  from [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_ADD_PERACTION_HIST] with (nolock)" +
				"  where service_id='POINT_LOCKER'   and reg_date>=" + dateType1 + 
				"  and action='CLICK' " +
				"  and (campaign_id like 'A_%' or campaign_id like 'T_%')" +
				"  group by CONVERT(VARCHAR(40), reg_date, 11)" +
				" ) F" +
				" on B.DAY = F.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" (select CONVERT(VARCHAR(40), reg_date, 11) as DAY, COUNT(distinct client_id) money_client_CNT" +
				"  from [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_ADD_PERACTION_HIST] with (nolock)" +
				"  where service_id='POINT_LOCKER'   and reg_date>=" + dateType1 + 
				"  group by CONVERT(VARCHAR(40), reg_date, 11)" +
				" ) G" +
				" on B.DAY = G.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" (SELECT CONVERT(VARCHAR(40), reg_date, 11) as DAY, COUNT(*) as newbie_cjone_CNT" +
				"    FROM [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_ADD_PERACTION_HIST] with   (nolock)" +
				"    where " +
				"   service_id='POINT_LOCKER' " +
				"    and reg_date>=" + dateType1 + 
				"    and action='NEWBIE'" +
				"    group by CONVERT(VARCHAR(40), reg_date, 11)" +
				"  ) H" +
				" on B.DAY = H.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" (select substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2) as DAY, SUM(sum_add_point) as AD_moneyPoint_SUM" +
				"  from [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_CAMPAIGN_ALLDAY_HIST] with (nolock)" +
				"  where service_id='POINT_LOCKER' and date_as_str>=" + dateType2 + 
				"  and (campaign_id like 'A_%' or campaign_id like 'T_%')" +
				"  group by substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2)" +
				"  ) I" +
				" on B.DAY = I.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" (select substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2) as DAY, SUM(sum_add_point) as GIFT_moneyPoint_SUM" +
				"  from [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_CAMPAIGN_ALLDAY_HIST] with (nolock)" +
				"  where service_id='POINT_LOCKER' and date_as_str>=" + dateType2 + 
				"  and campaign_id like 'G_%'" +
				"  group by substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2)" +
				"  ) J" +
				" on B.DAY = J.DAY" +
				" " +
				" FULL OUTER JOIN" +
				" (select substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2) as DAY, SUM(sum_add_point) as SUGGEST_moneyPoint_SUM" +
				"  from [POINT_DB].[dbo].[T_PNT_API_MONEY_POINT_CLIENT_CAMPAIGN_ALLDAY_HIST] with (nolock)" +
				"  where service_id='POINT_LOCKER' and date_as_str>=" + dateType2 + 
				"  and campaign_id like 'S_%'" +
				"  group by substring(date_as_str, 3, 2)+ '/' + substring(date_as_str, 5, 2)+ '/'  + substring(date_as_str, 7, 2)" +
				"  ) K" +
				" on B.DAY = K.DAY" +
				" order by DAY asc";
		
		System.out.println("쿼리="+sql);
	} 
	
	private static boolean getVal(long nonmoney_point, long point2beAdded){
		if(nonmoney_point+point2beAdded >= 100){
			return true;
		}
		return false;
	}			
	
	private static long getIterNum4Gift(long nonmoney_point, long point2beAdded){
		long before = nonmoney_point/100L;
		long after = (nonmoney_point + point2beAdded)/100L;
		return after - before;
	}		
	
	private static void getDay(){
    	Calendar calendar = Calendar.getInstance();
    	Calendar afterMonth36 = Calendar.getInstance();
    	afterMonth36.setTime(new Date());
    	afterMonth36.add(Calendar.MONTH, 36);
    	
    	SimpleDateFormat yymmdd = new SimpleDateFormat("yyMMdd");
    	// 캠페인
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
    	SimpleDateFormat end_dateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59.000");
    	// 컨텐츠
    	SimpleDateFormat c_dateFormat = new SimpleDateFormat("yyyyMMdd000000");
    	SimpleDateFormat c_end_dateFormat = new SimpleDateFormat("yyyyMMdd235959");		
    	
	    String end_date = end_dateFormat.format(afterMonth36.getTime()).toString();
	    String c_start_date = c_dateFormat.format(calendar.getTime()).toString();
	    String c_end_date = c_end_dateFormat.format(afterMonth36.getTime()).toString();		   
	    System.out.println("@@@==="+end_date);
	    System.out.println("@@@##==="+c_end_date);	  
	}	
	

}
