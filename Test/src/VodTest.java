
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import com.skb.auth.local.*;
public class VodTest {
	public static void main(String[] args) {
		String corporatorCode = "cjenm";
		String clipID = "SC_10106";
		String mediaUrl = "/CLIP/SC/B120169273/B120169273_EPI0001_01_t35.mp4";
		String regDate = "20150206175325";
		String sharedKey = "smccjenm";
		try {
		// SKB PIP URI Builder
		SkbPipUriBuilder uriBuilder
		= SkbPipUriBuilder.createInstance(
		corporatorCode, clipID, mediaUrl,
		regDate, sharedKey);
		// Single Result
		URI singleUri = uriBuilder.build();
		System.out.println(singleUri);
		// MultipleCollection<URI>
		Collection<URI> fullUris = uriBuilder.buildAll();
		for (URI fullUri : fullUris) {
			//System.out.println(fullUri);
			}
			} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			} catch (URISyntaxException use) {
			use.printStackTrace();
			}
	}
}
