import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class Test3 {
	static Runtime r = Runtime.getRuntime();
	
	public static void main(String[] args) {

			//JVM이 현재 시스템에 요구 가능한 최대 메모리량, 이 값을 넘으면 OutOfMemory 오류가 발생 합니다.           
			System.out.println("MAX  : "+r.maxMemory());
			
			
			
			int testSize = (int) (r.maxMemory() * 0.60);
			//System.out.println("@@@==="+testSize);
			//byte[] a = new byte[testSize];
			//byte[] a = new byte[testSize];
	        WeakReference<byte[]> a = new WeakReference<byte[]>(new byte[testSize]);
	        System.out.println("@@@==="+a.get());
			//a = null;
			//a = new byte[testSize - 100000000];
			byte[] b = new byte[testSize];
			//byte[] a = new byte[testSize];
			
			
			System.out.println("@@@==="+a.get());
			
			 //JVM이 현재 시스템에 얻어 쓴 메모리의 총량			
			System.out.println("TOTAL  : "+r.totalMemory() );
			
			//JVM이 현재 시스템에 청구하여 사용중인 최대 메모리(total)중에서 사용 가능한 메모리
			System.out.println("FREE : "+r.freeMemory() );
			
			r.availableProcessors();

	}

}
