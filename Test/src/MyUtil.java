
import java.io.*;

import java.lang.management.ManagementFactory;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.*;
import java.util.*;

//import org.mozilla.intl.chardet.HtmlCharsetDetector;
//import org.mozilla.intl.chardet.nsDetector;
//import org.mozilla.intl.chardet.nsICharsetDetectionObserver;


public final class MyUtil {
	/*public static void main(String[] args){
		String path = "a.txt";
		System.err.println(getDirectoryPartPath(path, "/"));
		System.err.println(getFileNamePartPath(path, "/"));
		System.err.println();
		
		path = "dir/file";
		System.err.println(getDirectoryPartPath(path, "/"));
		System.err.println(getFileNamePartPath(path, "/"));
		System.err.println();
		
		path = "/dir1/dir2/";
		System.err.println(getDirectoryPartPath(path, "/"));
		System.err.println(getFileNamePartPath(path, "/"));
		System.err.println();
		
		path = "dir2/dir4/file4.txt";
		System.err.println(getDirectoryPartPath(path, "/"));
		System.err.println(getFileNamePartPath(path, "/"));
		System.err.println();
	}*/
	/*public static void main(String[] args) throws Exception{
		String urlstr = "http://localhost:8080/echo.start?abc=a";
		String postData = " {   \"medialist\" : [ {     \"callback_start\" : \"http://global.mnet.com/onair/cms_clip_callback.m?sflg=N&mediaID=392800\",     \"mediagubun\" : \"2909\",     \"mediaid\" : \"392800\",     \"callback_end\" : \"http://global.mnet.com/onair/cms_clip_callback.m?sflg=Y&mediaID=392800\"   }, {     \"callback_start\" : \"http://global.mnet.com/onair/cms_clip_callback.m?sflg=N&mediaID=236994\",     \"mediagubun\" : \"2907\",     \"mediaid\" : \"236994\",     \"callback_end\" : \"http://global.mnet.com/onair/cms_clip_callback.m?sflg=Y&mediaID=236994\"   } ],   \"clipgubun\" : \"MV\",   \"vod_cut_range\" : \"10,20,30,40\",   \"clipid\" : \"1234\",   \"src_mediaid\" : \"392800\" }  ";
		String response = requestPost(urlstr, null, postData);
		System.err.println(response);
	}*/
	
	/*public static void main(String[] args) throws Exception{
		if(args.length<2){
			System.err.println("need destFile, srcFile,...");
			System.exit(-1);
		}
		
		String dest = args[0];
		for(int i=1;i<args.length;i++){
			String src = args[i];
			copyFile(src, dest, true);
		}
	}*/
	
	public static void main(String[] args) throws Exception{
		//deleteDir("D:\\temp\\vodeditWork\\656b1fa8-46de-49fa-aaa8-b446051ed0e6");
		//deleteDir("D:\\temp\\vodeditWork");
		
		 System.err.println(MyUtil.getYesterday(-1));
		//restartApplication(null);
	}
	
	public static void restartApplication(Runnable runBeforeRestart) throws IOException {
	    try {
	        // java binary
	    	System.err.println("call restartApplication");
	        //String java = System.getProperty("java.home") + "/bin/java";
	    	String java = "java";
	        // vm arguments
	        List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
	        StringBuffer vmArgsOneLine = new StringBuffer();
	        for (String arg : vmArguments) {
	            // if it's the agent argument : we ignore it otherwise the
	            // address of the old application and the new one will be in conflict
	            if (!arg.contains("-agentlib")) {
	                vmArgsOneLine.append(arg);
	                vmArgsOneLine.append(" ");
	            }
	        }
	        // init the command to execute, add the vm args
	        final StringBuffer cmd = new StringBuffer("\"" + java + "\" " + vmArgsOneLine);
	        // program main and program arguments (be careful a sun property. might not be supported by all JVM) 
	        String[] mainCommand = System.getProperty("sun.java.command").split(" ");
	        // program main is a jar
	        if (mainCommand[0].endsWith(".jar")) {
	            // if it's a jar, add -jar mainJar
	            cmd.append("-jar " + new File(mainCommand[0]).getPath());
	        } else {
	            // else it's a .class, add the classpath and mainClass
	            cmd.append("-cp \"" + System.getProperty("java.class.path") + "\" " + mainCommand[0]);
	        }
	        // finally add program arguments
	        for (int i = 1; i < mainCommand.length; i++) {
	            cmd.append(" ");
	            cmd.append(mainCommand[i]);
	        }
	        // execute the command in a shutdown hook, to be sure that all the
	        // resources have been disposed before restarting the application
	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            @Override
	            public void run() {
	                try {
	                    Runtime.getRuntime().exec(cmd.toString());
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	        
	        if (runBeforeRestart != null) {
	        	runBeforeRestart.run();
	        }
	        // exit
	        System.exit(0);
	    } catch (Exception e) {
	        // something went wrong
	        throw new IOException("Error while trying to restart the application", e);
	    }
	}
	
	public static List<String> getCleanList(String src, String seperator) throws Exception{
		String[] arr = src.split(seperator);
		List<String> res = new ArrayList<String>();
		for(String item : arr){
			res.add(item.replace("(\r|\n}\t", " "));
		}
		return res;
	}
	
	
	/*public static String toStrMathVector(org.apache.mahout.math.Vector vec, boolean bDoVectorNormalization){
		org.apache.mahout.math.Vector v = null;
		if(bDoVectorNormalization)
			v = vec.normalize();
		else
			v = vec;
		
		DecimalFormat dFormat = new DecimalFormat("####.##");
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<v.size();i++){
			if(sb.length()>0)
				sb.append(",");
			sb.append(dFormat.format(v.get(i)));
		}
		return sb.toString();
	}
	
	// formatString = "##.##"
	public static String toStrMathVector(org.apache.mahout.math.Vector vec, String formatString, boolean bDoVectorNormalization, Integer windowRank) throws Exception{
		if(windowRank!=null && windowRank>vec.size())
			throw new Exception("windowRank>vec.size()");
		org.apache.mahout.math.Vector v = null;
		if(bDoVectorNormalization)
			v = vec.normalize();
		else
			v = vec;
		
		if(windowRank==null)
			windowRank =v.size();
		
		DecimalFormat dFormat = new DecimalFormat(formatString); // "#.##"
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<windowRank;i++){
			if(sb.length()>0){
				sb.append(",");
			}
			double val = v.get(i);
			String str = dFormat.format(val);
			sb.append(str);
		}
		return sb.toString();
	}*/
	
	public static List<String> loadFileAsArrayOfLine(String fileName) throws Exception{
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		List<String> res = new ArrayList<String>();
		String line = null;
		while((line = br.readLine())!=null){
			res.add(line);
		}
		return res;
	}
	
	public static List<String> loadFileAsArrayOfLine(String fileName, int startOfLine, int size) throws Exception{
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		List<String> res = new ArrayList<String>();
		String line = null;
		int currLine = 0;
		int endOfLine = startOfLine+size;
		
		while((line = br.readLine())!=null){
			if( (startOfLine <= currLine) && (currLine < endOfLine)){
				res.add(line);
			}else if(currLine < startOfLine){
				continue;
			}else if(endOfLine <= currLine){
				break;
			}else{
				throw new Exception("UNKNOWN CASE");
			}
			currLine++;
		}
		
		if(res.isEmpty())
			return null;
		else
			return res;
	}

	
	public static  List<Integer> split2ListOfInteger(String src, String seps) throws Exception{
		String[] arr = src.split(seps);
		List<Integer> res = new ArrayList<Integer>();
		for(String item : arr){
			res.add(Integer.parseInt(item));
		}
		return res;
	}
	
	public static  List<String> split2ListOfString(String src, String seps, boolean bTrimPerItem) throws Exception{
		String[] arr = src.split(seps);
		if(!bTrimPerItem)
			return Arrays.asList(arr);
		List<String> res = new ArrayList<String>();
		for(int i=0;i<arr.length;i++){
			res.add(arr[i].trim().replaceAll("\t", ""));
		}
		return res;
	}
	
	
	public static  Set<String> split2SetOfString(String src, String seps, boolean bTrimPerItem) throws Exception{
		List<String> list = split2ListOfString(src, seps, bTrimPerItem);
		Set<String> res = new HashSet<String>();
		res.addAll(list);
		return res;
	}
	
	
	public static <T> String join(List<T> list, String seps){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<list.size()-1;i++){
			sb.append(list.get(i)).append(seps);
		}
		sb.append(list.get(list.size()-1));
		return sb.toString();
	}
	
	public static class Str2Type{
		public static double[] getSmartDoubleArr(Object obj, String seps, boolean bDoTrim){
			if(obj==null)
				return null;
			
			if(obj instanceof String){
				String str = (String)obj;
				String[] arr = str.split(seps);
				double[] d = new double[arr.length];
				for(int i=0;i<arr.length;i++){
					d[i] = Double.parseDouble(arr[i]);
				}
				
				return d;
			}else{
				return null;
				//throw new Exception("UNDEF type "+ obj.getClass().getName());
			}
		}
		
		public static List<Integer> getSmartIntListVal(Object obj, String seps, boolean bDoTrim){
			if(obj==null)
				return null;
			if(obj instanceof List){
				return (List<Integer>) obj;
			}else if(obj instanceof String){
				String str = (String)obj;
				String[] arr = str.split(seps);
				List<Integer> res = new ArrayList<Integer>(); 
				if(bDoTrim){
					for(int i=0;i<arr.length;i++){
						arr[i] = arr[i].trim();
						res.add(Integer.parseInt(arr[i]));
					}
				}
				return res;
			}else{
				return null;
				//throw new Exception("UNDEF type "+ obj.getClass().getName());
			}
		}
		
		public static List<Double> getSmartDoubleListVal(Object obj, String seps, boolean bDoTrim){
			if(obj==null)
				return null;
			if(obj instanceof List){
				return (List<Double>) obj;
			}else if(obj instanceof String){
				String str = (String)obj;
				String[] arr = str.split(seps);
				List<Double> res = new ArrayList<Double>(); 
				if(bDoTrim){
					for(int i=0;i<arr.length;i++){
						arr[i] = arr[i].trim();
						res.add(Double.parseDouble(arr[i]));
					}
				}
				return res;
			}else{
				return null;
				//throw new Exception("UNDEF type "+ obj.getClass().getName());
			}
		}
		
		public static List<String> getSmartStrListVal(Object obj, String seps, boolean bDoTrim){
			if(obj==null)
				return null;
			if(obj instanceof List){
				return (List<String>) obj;
			}else if(obj instanceof String){
				String str = (String)obj;
				String[] arr = str.split(seps);
				if(bDoTrim){
					for(int i=0;i<arr.length;i++){
						arr[i] = arr[i].trim();
					}
				}
				return Arrays.asList(arr);
			}else{
				return null;
				//throw new Exception("UNDEF type "+ obj.getClass().getName());
			}
		}
		
		public static Integer getSmartIntValOrThrow(Object obj, String fieldName) throws Exception{
			if(obj==null)
				throw new Exception("NOTFOUND " + fieldName);
			if(obj instanceof String){
				try{
					return Integer.parseInt((String)obj);
				}catch(Exception e){
					throw new Exception("INVALID_INT_FORMAT " + fieldName);
				}
			}else if(obj instanceof Integer){
				return (Integer)obj;
			}else{
				throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
		public static Integer getSmartIntVal(Object obj, Integer defaultVal){
			if(obj==null)
				return defaultVal;
			if(obj instanceof String){
				try{
					return Integer.parseInt((String)obj);
				}catch(Exception e){
					return defaultVal;
				}
			}else if(obj instanceof Integer){
				return (Integer)obj;
			}else{
				return defaultVal;
				//throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
		public static Long getSmartLongVal(Object obj, Long defaultVal){
			if(obj==null)
				return defaultVal;
			if(obj instanceof String){
				try{
					return Long.parseLong((String)obj);
				}catch(Exception e){
					return defaultVal;
				}
			}else if(obj instanceof Long){
				return (Long)obj;
			}else if(obj instanceof Integer){
				return (Long)obj;
			}else{
				return defaultVal;
				//throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
		public static Long getSmartLongValOrThrow(Object obj, String fieldName) throws Exception{
			if(obj==null)
				throw new Exception("NOTFOUND " + fieldName);
			if(obj instanceof String){
				try{
					return Long.parseLong((String)obj);
				}catch(Exception e){
					throw new Exception("INVALID_LONG_FORMAT " + fieldName);
				}
			}else if(obj instanceof Integer){
				return (Long)obj;
			}else{
				throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
		public static Double getSmartDoubleVal(Object obj, Double defaultVal){
			if(obj==null)
				return defaultVal;
			if(obj instanceof String){
				try{
					return Double.parseDouble((String)obj);
				}catch(Exception e){
					return defaultVal;
				}
			}else if(obj instanceof Double){
				return (Double)obj;
			}else{
				return defaultVal;
				//throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
		public static Boolean getSmartBooleanVal(Object obj, Boolean defaultVal){
			if(obj==null)
				return defaultVal;
			if(obj instanceof String){
				try{
					return Boolean.parseBoolean((String)obj);
				}catch(Exception e){
					return defaultVal;
				}
			}else if(obj instanceof Boolean){
				return (Boolean)obj;
			}else{
				return defaultVal;
				//throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
		public static String getSmartStrVal(Object obj, String defaultVal){
			if(obj==null)
				return defaultVal;
			if(obj instanceof String){
				try{
					return (String)obj;
				}catch(Exception e){
					return defaultVal;
				}
			}else{
				return defaultVal;
				//throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
		public static String getSmartStrValOrThrow(Object obj, String fieldName) throws Exception{
			if(obj==null)
				throw new Exception("NOTFOUND " + fieldName);
			if(obj instanceof String){
				try{
					return (String)obj;
				}catch(Exception e){
					throw new Exception("INVALID_STR_FORMAT " + fieldName);
				}
			}else{
				throw new Exception("UNDEF type " + obj.getClass().getName());
			}
		}
		
	}
	
	
	// 두 리스트를 합병한다. 각 리스트에서 하나씩 섞어, maxNum까지만 얻는다. 중복 제거 
	public static List<String> mixList(List<String> a, List<String> b, Integer maxNum) throws Exception{
		Set<String> dic = new HashSet<String>();
		int sizeA = a.size();
		int sizeB = b.size();
		int idxA=0;
		int idxB=0;
		
		while(dic.size()<maxNum){
			if(idxA>=sizeA && idxB>=sizeB)
				break;
			if(idxA<sizeA){
				dic.add(a.get(idxA));
				idxA++;
			}
			if(idxB<sizeB){
				dic.add(b.get(idxB));
				idxB++;
			}
		}
		
		return Arrays.asList(dic.toArray(new String[0]));

	}
	
	public static void deleteDir(String path) throws Exception{
		File dir = new File(path);
		String[] files = dir.list();
		for(String file : files){
			File f = new File(path+File.separatorChar + file);
			f.delete();
		}
		dir.delete();
	}
	
	public static void copyFile(String srcFile, String destFile, boolean append) throws Exception{
		int bufsize = 1024;
		byte[] buf = new byte[bufsize];
		
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(destFile, append);
		int len = -1;
		while( (len = fis.read(buf)) > 0 ){
			fos.write(buf, 0, len);
			//buf = new byte[bufsize];
		}
		fis.close();
		fos.close();
	}
	
	
	public static long getFileSize(String path){
		try{
			File f = new File(path);
			return f.length();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static String requestPost(String urlstr, Map<String, String> requestHeaders, String postData) throws Exception{
		URL url = new URL(urlstr);
		URLConnection urlconn =url.openConnection();
		
		if(requestHeaders!=null){
			Set<String> keys = requestHeaders.keySet();
			for(String key : keys){
				urlconn.setRequestProperty(key, requestHeaders.get(key));
			}
		}
		
		urlconn.setDoInput(true);
		urlconn.setUseCaches(false);
		urlconn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(urlconn.getOutputStream());
		wr.write(postData);
		wr.flush();
		wr.close();
		
		InputStream is = urlconn.getInputStream();
		String responseStr = MyUtil.is2String(is, "UTF-8");
		is.close();
		return responseStr;
	}
	
	public static String requestGetWithRetry(String urlstr, Map<String, String> requestHeaders, String encodingType, int retryNum, long sleepTime) throws Exception{
		try{
			return requestGet(urlstr, requestHeaders, encodingType);
		}catch(Exception e){
			if(retryNum<0){
				throw new Exception("retryNum expired");
			}else{
				Thread.sleep(sleepTime);
				retryNum--;
				return requestGetWithRetry(urlstr, requestHeaders, encodingType, retryNum, sleepTime);
			}
		}
	}
	
	public static String requestGetWithRetry(String urlstr, String encodingType, int retryNum, long sleepTime) throws Exception{
		try{
			return requestGet(urlstr, encodingType);
		}catch(Exception e){
			if(retryNum<0){
				throw new Exception("retryNum expired");
			}else{
				Thread.sleep(sleepTime);
				retryNum--;
				return requestGetWithRetry(urlstr, encodingType, retryNum, sleepTime);
			}
		}
	}
	
	public static String requestGet(String urlstr, Map<String, String> requestHeaders, String encodingType) throws Exception{
		URL url = new URL(urlstr);
		URLConnection urlconn =url.openConnection();
		
		if(requestHeaders!=null){
			Set<String> keys = requestHeaders.keySet();
			for(String key : keys){
				urlconn.setRequestProperty(key, requestHeaders.get(key));
			}
		}
		
		InputStream is = urlconn.getInputStream();
		if(encodingType==null)
			encodingType = System.getProperty("file.encoding");
		String responseStr = MyUtil.is2String(is, encodingType);
		return responseStr;
	}
	
	public static String requestGet(String urlstr, String encodingType) throws Exception{
		URL url = new URL(urlstr);
		URLConnection urlconn =url.openConnection();
		InputStream is = urlconn.getInputStream();
		if(encodingType==null)
			encodingType = System.getProperty("file.encoding");
		String responseStr = MyUtil.is2String(is, encodingType);
		return responseStr;
	}
	
	// VOC용
	public static void vocRequestGet(String urlstr, String encodingType) throws Exception{
		URL url = new URL(urlstr);
		URLConnection urlconn =url.openConnection();
		InputStream is = urlconn.getInputStream();
		if(encodingType==null)
			encodingType = System.getProperty("file.encoding");
		MyUtil.is2String(is, encodingType);		
	}	
	
	public static String requestGetWithTimeout(String urlstr, String encodingType, Integer timeoutMiliSecond) throws Exception{
		URL url = new URL(urlstr);
		URLConnection urlconn =url.openConnection();
		if(timeoutMiliSecond!=null){
			urlconn.setConnectTimeout(timeoutMiliSecond);
			urlconn.setReadTimeout(timeoutMiliSecond);
		}
		InputStream is = urlconn.getInputStream();
		if(encodingType==null)
			encodingType = System.getProperty("file.encoding");
		String responseStr = MyUtil.is2String(is, encodingType);
		return responseStr;
	}
	
	public static void saveText2File(String text, String saveFile) throws Exception{
		File f = new File(saveFile);
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(text.getBytes());
		fos.close();
	}
	
	public static void saveText2File(String text, String saveFile, String charsetName) throws Exception{
		File f = new File(saveFile);
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(text.getBytes(charsetName));
		fos.close();
	}
	
	public static String getDirectoryPartPath(String fullPath, String seps){
		try{
			int idx = fullPath.lastIndexOf(seps); // a.txt 		dir/file		/dir1/dir2/		dir2/dir4/file4.txt
			if(idx==-1)
				return seps;
			return fullPath.substring(0, idx+1);
		}catch(Exception e){
			return seps;
		}
	}
	
	public static String getFileNamePartPath(String fullPath, String seps){
		try{
			int idx = fullPath.lastIndexOf(seps);
			return fullPath.substring(idx+1);
		}catch(Exception e){
			return "";
		}
	}
	
	
	// diffDay ; 어제 => -1
	public static String getYesterday(int diffDay){
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, diffDay);
		//cal.add(Calendar.DATE, -1);
		cal.add(Calendar.DATE, diffDay);
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String res = String.format("%04d-%02d-%02d", year, month, day);
		return res;
//		sb.append(cal.get(Calendar.YEAR)).append("-");
//		sb.append(cal.get(Calendar.MONTH) + 1).append("-");
//		sb.append(cal.get(Calendar.DAY_OF_MONTH));
//		return sb.toString();
	}
	
	public static int getThisYear() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy"); //new SimpleDateFormat("HH:mm:ss");
		long now = System.currentTimeMillis();
		String yearStr =  format.format(now);
		return Integer.parseInt(yearStr);
	}
	
	public static String getCurrTime(String timeFormat){
		SimpleDateFormat format = null; //new SimpleDateFormat("HH:mm:ss");
		if(timeFormat==null)
			format = new SimpleDateFormat("HH:mm:ss");
		else
			format = new SimpleDateFormat(timeFormat);
		long now = System.currentTimeMillis();
		return format.format(now);
	}
	
	public static String concatDirName(String dirA, String dirB, String seps){
		if(dirA==null)
			return dirB;
		if(dirB==null)
			return dirA;
		
		if(seps==null)
			seps = File.separator;
		
		if(dirA.endsWith(seps) && dirB.startsWith(seps)) {
			return dirA + dirB.substring(1);
		}
		
		if(dirA.endsWith(seps) && !dirB.startsWith(seps)) {
			return dirA + dirB;
		}
		
		if(!dirA.endsWith(seps) && dirB.startsWith(seps)) {
			return dirA + dirB;
		}
		
		if(!dirA.endsWith(seps) && !dirB.startsWith(seps)) {
			return dirA + seps + dirB;
		}
		return null;
	}
	
	public static boolean mkDirIfNotExist(String targetDir){
		File f = new File(targetDir);
		if(!f.isDirectory())
			return f.mkdirs();
		return true;
	}
	
	
	
	public static String readyFully(InputStream is){
		try{
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while( (line = br.readLine())!=null){
			sb.append(line);
			sb.append("\n");
		}
		
		return sb.toString();
		}catch(Exception e){
			return null;
		}
	}
	
	public static String readyFully(InputStream is, String charset){
		try{
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is, charset);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while( (line = br.readLine())!=null){
			sb.append(line);
			sb.append("\n");
		}
		
		return sb.toString();
		}catch(Exception e){
			return null;
		}
	}
	
	/*
public static void copyFile(String srcFile, String destFile, boolean append) throws Exception{
		int bufsize = 1024;
		byte[] buf = new byte[bufsize];
		
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(destFile, append);

		while(fis.read(buf)!=-1){
			fos.write(buf);
			buf = new byte[bufsize];
		}
		fis.close();
		fos.close();
	} 
	 */
	
	public static void saveInputStream2BinaryFile(InputStream is, String binFileName) throws Exception{
		int bufsize = 1024;
		byte[] buf = new byte[bufsize];
		
		FileOutputStream fos = new FileOutputStream(binFileName, false);
		while(is.read(buf)!=-1){
			fos.write(buf);
			buf = new byte[bufsize];
		}
		fos.close();
	}
	
	public static String is2String(InputStream is, String encodingType){
		try{
			StringBuffer sb = new StringBuffer();
			InputStreamReader isr = new InputStreamReader(is, encodingType);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while( (line = br.readLine())!=null){
				sb.append(line);
				sb.append("\n");
			}
			
			return sb.toString();
			}catch(Exception e){
				return null;
			}
	}
	
	/*public static String convert(InputStream is, String encodingType){
		try{
		byte[] b = null;
		StringBuffer sb = new StringBuffer();
		
		int n;
		while(true){
			b = new byte[1024];
			n=is.read(b);
			sb.append(new String(b, encodingType));
			if(n==-1)
				break;
		}
		return sb.toString();
		}catch(Exception e){
			return null;
		}
	}*/
	
	//a=b&c=d
	public static Map<String, Integer> parseQueryAsSI(String urlQueryString) {
		String[] params = urlQueryString.split("&");  
		Map<String, Integer> map = new HashMap<String, Integer>();  
		for (String param : params){
			String[] pair = param.split("=");
			if(pair==null || pair.length==0){
			}else if(pair.length==1){
				String name = pair[0];  
				String value = null;  
				map.put(name, Integer.parseInt(value));
			}else if(pair.length==2){
				String name = pair[0];  
				String value = pair[1];  
				map.put(name, Integer.parseInt(value));
			}else{
				String name = pair[0];  
				String value = param.substring(pair[0].length());  
				map.put(name, Integer.parseInt(value));
			}
		}  
		return map;  
	}
	
	public static Map<String, String> parseQueryString(String urlQueryString) {
		if(urlQueryString==null || urlQueryString.isEmpty())
			return new HashMap<String, String>();
		try{
			String[] params = urlQueryString.split("&");  
			Map<String, String> map = new HashMap<String, String>();  
			for (String param : params){
				String[] pair = param.split("=");
				if(pair==null || pair.length==0){
				}else if(pair.length==1){
					String name = pair[0];  
					String value = null;  
					map.put(name, value);
				}else if(pair.length==2){
					String name = pair[0];  
					String value = pair[1];  
					map.put(name, value);
				}else{
					String name = pair[0];  
					String value = param.substring(pair[0].length());  
					map.put(name, value);
				}
			}  
			return map;
		}catch(Exception e){
			return new HashMap<String, String>();
		}
	}
	
	public static String toQueryString(Map<String, String> line){
		StringBuffer sb = new StringBuffer();
		Set<Map.Entry<String, String>> entryset = line.entrySet();
		Iterator<Map.Entry<String, String>> iter = entryset.iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if(sb.length()>0)
				sb.append("&").append(key).append("=").append(value);
			else
				sb.append(key).append("=").append(value);
		}
		return sb.toString();
	}
	
	
	public static String toQueryString(Map<String, String> line, String glueRecord, String glueField){
		StringBuffer sb = new StringBuffer();
		Set<Map.Entry<String, String>> entryset = line.entrySet();
		Iterator<Map.Entry<String, String>> iter = entryset.iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if(sb.length()>0)
				sb.append(glueRecord).append(key).append(glueField).append(value);
			else
				sb.append(key).append(glueField).append(value);
		}
		return sb.toString();
	}
	
	
	
	/*public static void parseQueryString(String urlQueryString, Map<String, String> map){
		String[] params = urlQueryString.split("&");  
		//Map<String, String> map = new HashMap<String, String>();  
		for (String param : params){  
			String name = param.split("=")[0];  
			String value = param.split("=")[1];  
			map.put(name, value);  
		}  
		//return map;  
	}*/
	
	
	public static String arrayJoinWithTail(String glue, List<String> arr, String tail){
		StringBuffer sb = new StringBuffer();
		for(String item : arr){
			if(sb.length()>0){
				sb.append(glue).append(item).append(tail);
			}else{
				sb.append(item).append(tail);
			}
		}
		return sb.toString();
	}
		
	public static String arrayJoin(String glue, Integer[] array) {
		String result = "";

		for (int i = 0; i < array.length; i++) {
			result += array[i];
			if (i < array.length - 1) result += glue;
		}
		return result;
	}
	
	public static String arrayJoin(String glue, String array[]) {
		String result = "";

		for (int i = 0; i < array.length; i++) {
			result += array[i];
			if (i < array.length - 1) result += glue;
		}
		return result;
	}
	
	public static String arrayJoin(String glue, List<String> list) {
		if(list==null)
			return null;
		
		String result = "";

		for (int i = 0; i < list.size(); i++) {
			result +=list.get(i);
			if (i < list.size() - 1) result += glue;
		}
		return result;
	}
	
	/*public static String arrayJoin(String glue, Set<String> array) {
		String[] arr = array.toArray(new String[0]);
		return arrayJoin(glue, arr);
	}*/
	
	public static String arrayJoin(String glue, Collection<String> array) {
		String[] arr = array.toArray(new String[0]);
		return arrayJoin(glue, arr);
	}
	
	public static String arrayJoin(String glue, String array[], int start, int end) {
		String result = "";
		
		if(end==-1)
			end = array.length; 
		 
		for (int i = start; i < end; i++) {
			result += array[i];
			if (i < end - 1) result += glue;
		}
		return result;
	}
	
	/*public static String detectEncoding(String uri) throws IOException {
		int lang = 0;
		byte[] buf = uri.getBytes();
        nsDetector det = new nsDetector(lang) ;
        det.Init(new nsICharsetDetectionObserver() {
        	public void Notify(String charset) {
        		HtmlCharsetDetector.found = true ;
        	}
        });
        det.DoIt(buf,buf.length, false);
    	det.DataEnd();
 
    	String[] probableCharsets = det.getProbableCharsets();
 
		for(String charset:probableCharsets) {
			System.err.println("CHARSET="+charset);
		}
 
		System.err.println("URI:"+uri);
		System.err.println("Re-encodedURI(UTF-8):"+URLEncoder.encode(URLDecoder.decode(uri,"UTF-8"),"UTF-8").replace("%2F", "/"));
		String charset;
		if(uri.equals(URLEncoder.encode(URLDecoder.decode(uri,"UTF-8"),"UTF-8").replace("%2F", "/"))) {
			 // UTF-8
			if(probableCharsets.length>0) {
				charset = probableCharsets[0];
			}
			else {
				charset = "UTF-8";
			}
		}
		else { // NOT UTF-8
			if(probableCharsets.length>0) {
				if("UTF-8".equals(probableCharsets[0])) {
					if(probableCharsets.length>1) {
						charset = probableCharsets[1];
					}
					else {
						charset = "ISO-8859-1";
					}
				}
				else {
					charset = probableCharsets[0];
				}
			}
			else {
				charset = "ISO-8859-1";
			}
		}
		return charset;
	}*/
	
	public static String iconv(String src, String srcEncodingType, String destEncodingType){
		byte[] buf = null;
		try{
			if(srcEncodingType==null)
				buf = src.getBytes();
			else
				buf = src.getBytes(srcEncodingType);
			
			return new String(buf, destEncodingType);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	 public static boolean isUTF8(String str){
		 try{
		 byte[] bytes=str.getBytes("ISO-8859-1");
		 return isUTF8(bytes,0,bytes.length);
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }
	}
    
	 public static boolean isUTF8(byte[] buf, int offset, int length) {
		 boolean yesItIs = false;
		 
		 for (int i=offset; i<offset+length; i++) {
			 if ((buf[i] & 0xC0) == 0xC0) {  
				 int nBytes;
				 for (nBytes=2; nBytes<8; nBytes++) {
					 int mask = 1 << (7-nBytes);
					 if ((buf[i] & mask) == 0) break;
				 }
             
				if(nBytes==2) return false;
		              
				// Check that the following bytes begin with 0b10xxxxxx
				for (int j=1; j<nBytes; j++) {
					if (i+j >= length || (buf[i+j] & 0xC0) != 0x80) return false;
				}
		         
				if(nBytes==3){
					char c = (char) (((buf[i] & 0x0f) << 12) + ((buf[i+1] & 0x3F) << 6) + (buf[i+2] & 0x3F));
					if(!(c >= 0x0800 && c <= 0xFFFF)){
						return false;
					}                           
				}
	                 
				yesItIs = true;
			 }
		 }
		 return yesItIs;
	}
	
	public static void testEncoding(String a1){
		if ( a1 != null ) {
			String charset[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii" , "MS949" ,"UTF-8", "Shift_JIS", "EUC-JP", "ISO-2022-JP", "ISO-2022-KR", "Big5", "x-euc-tw", "UTF-16BE"};
		     
			for(int k=0; k<charset.length ; k++){
				for(int l=0 ; l<charset.length ; l++){
					if(k==l){
						continue;
					}else{
						try{
							System.out.println(charset[k]+" : "+charset[l]+" :"+new String(a1.getBytes(charset[k]),charset[l])+"<br>");
						}catch(Exception e){e.printStackTrace();}
					}
				}
			}
		}
	}
	
	
	public static String guessEncoding(String src){
		String[] types = {"UTF-8", "iso-8859-1", "euc-kr", "MS949"};
		
		double maxRatio = 0.1;
		int maxIdx = -1;
		for(int i=0;i<types.length;i++){
			double ratio = guessingHangul(src, types[i]);
			if(ratio > maxRatio){
				maxRatio = ratio;
				maxIdx = i;
			}
		}
		return types[maxIdx];
	}
	
	private static double guessingHangul(String src, String encodingType){
		try{
			char ga = new String("��".getBytes(), "MS949").charAt(0);
			char hing = new String("��".getBytes(), "MS949").charAt(0);
			String srcUtf8 = new String(src.getBytes(encodingType), "MS949");
			int HangulCnt =0;
			int AsciiCnt = 0;
			int UnknownCnt = 0;
			for(int i=0;i<srcUtf8.length();i++){
				char ch = srcUtf8.charAt(i);
				if(ga <= ch && ch<= hing)
					HangulCnt++;
				//else if('!' <= ch && ch<= 'z')
				else if('A' <= ch && ch<= 'z')
					AsciiCnt++;
				else
					UnknownCnt++;
			}
			
			double ratio = (double)(HangulCnt+AsciiCnt) / (double)(HangulCnt+AsciiCnt+UnknownCnt);
			System.out.println(encodingType + " " + ratio + " = " + HangulCnt + " " + AsciiCnt + " " + UnknownCnt + " " + new String(srcUtf8.getBytes("MS949")));
			return ratio;
			
			}catch(Exception e){
				e.printStackTrace();
				return -1;
			}
	}
}
