
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;
import java.util.*;

/**
 * 
 * @author Yavuz
 *
 */
class Client {
	
	/**
	 * root is the DnsTree which this client will use.
	 * ipAddress is the ipAddress of this client.
	 * cacheList is the list of CachedContent.
	 */
	DnsTree root;
	String ipAddress;
	CachedContent[] cacheList;
	
	/**
	 * constructs a client with a cacheList sized 10.
	 * @param ipAddress: is the ipAddress of this client.
	 * @param root: is the DnsTree which this client will use.
	 */
	Client(String ipAddress, DnsTree root){
		this.ipAddress = ipAddress;
		this.root = root;
		cacheList = new CachedContent[10];
	}
	
	/**
	 * gives ipAddress of given domainName.
	 * @param domainName: is the domainName given.
	 * @return one of the ipAddresses of given domainName.
	 */
	String sendRequest(String domainName) {
		/**
		 * if in the cacheList, call immediately
		 * else call queryDomain for Round Robin Algorithm.
		 */
		for(CachedContent c : cacheList) {
			if(c != null) {
				if(domainName.equals(c.domainName)) {
					c.hitNo++;
					return c.ipAddress;
				}
			}
		}
		
		String ip = root.queryDomain(domainName);
		if(ip != null) this.addToCache(domainName, ip);
		return  ip;
	}
	
	/**
	 * adds given ipAddress to cacheList.
	 * @param domainName: to be added.
	 * @param ipAddress: to be added.
	 */
	void addToCache(String domainName, String ipAddress) {
		boolean check = true;
		for(int i=0; i<10; i++) {
			if(cacheList[i] != null) {
				if(cacheList[i].equals(domainName, ipAddress)){
					check = false;
				}
			}
		}
		
		/**
		 * if size reaches 10, removes least used content.
		 */
		boolean tf = true;
		for(int i=0; i<10; i++) {
			if(cacheList[i] == null) {
				tf = false;
			}
		}
		if(tf) {
			removeCachedContent();
		}
		
		if(check) {
			for(int i=0; i<10; i++) {
				if(cacheList[i] == null) {
					cacheList[i] = new CachedContent(domainName, ipAddress, 0);
					return;
				}
			}
		}
	}
	
	/**
	 * removes content from cacheList.
	 */
	void removeCachedContent() {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i=0; i<10; i++) {
			if(cacheList[i].hitNo < min) {
				index = i;
				min = cacheList[i].hitNo;
			}
		}
		if(index != -1) {
			cacheList[index] = null;
		}
		
	}
	
	/*
	 * inner class.
	 */
	class CachedContent {
		
		/**
		 * domainName and ipAddress are to be added.
		 * hitNo shows how many time it is used.
		 */
		String domainName;
		String ipAddress;
		int hitNo;
		
		/**
		 * constructs a cached content.
		 */
		CachedContent(String name, String ip, int hit){
			domainName = name;
			ipAddress = ip;
			hitNo = hit;
		}
		
		/**
		 * checks if two contents are equal.
		 */
		boolean equals(String name, String ip) {
			return (ipAddress.equals(ip) && domainName.equals(name));
		}
		
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

