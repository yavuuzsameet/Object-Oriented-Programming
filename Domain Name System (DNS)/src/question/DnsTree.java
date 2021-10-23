
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;
import java.util.*;

/**
 * 
 * @author Yavuz
 *
 */
class DnsTree {
	
	/**
	 * root is the root node of this tree.
	 */
	DnsNode root;
	
	/**
	 * constructs a DnsTree.
	 */
	DnsTree(){
		root = new DnsNode();
	}
	
	/**
	 * calls insert method to add given domainName with ipAddress to DnsTree.
	 * @param domainName: to be inserted to tree.
	 * @param ipAddress: to be inserted to tree.
	 */
	void insertRecord(String domainName, String ipAddress) { 
		domainName = "." + domainName;
		insert(root, domainName, ipAddress);
	}
	/**
	 * adds given domainName and ipAddress to DnsTree.
	 * @param root: is the current node in action.
	 * @param domainName: to be inserted to tree.
	 * @param ipAddress: to be inserted to tree.
	 */
	private void insert(DnsNode root, String domainName, String ipAddress) {
		/**
		 * these 3 lines of command below reverses given domain name and separates sub domain as wanted.
		 */
		String ters = mirror(domainName);
		String subDomain = mirror(ters.substring(0, ters.indexOf('.')));
		domainName = domainName.substring(0, domainName.length()-subDomain.length()-1);
		
		DnsNode sub = null;
		
		/**
		 * if current node has no child with given name, make a new node and put it to current node's childNodeList,
		 * else get wanted node from childNodeList.
		 */
		if(!root.childNodeList.containsKey(subDomain)) {
			sub = newNode();
			root.childNodeList.put(subDomain, sub);
		}
		else {
			sub = root.childNodeList.get(subDomain);
		}
		
		/**
		 * when there is no more available domain, insert ipAddress and make the node valid.
		 */
		if(domainName.length() == 0) {
			root.childNodeList.get(subDomain).ipAddresses.add(ipAddress);
			root.childNodeList.get(subDomain).ipAddressQ.offer(ipAddress);
			root.childNodeList.get(subDomain).validDomain = true;
			return;
		}
		
		/**
		 * calls for recursive operation.
		 */
		insert(sub, domainName, ipAddress);
	}
	
	/**
	 * creates a DnsNode.
	 * @return DnsNode created.
	 */
	DnsNode newNode() {
		return new DnsNode();
	}
	
	/**
	 * calls remove method to remove given domainName from tree.
	 * @param domainName: to be removed from tree.
	 * @return true if removed, false otherwise.
	 */
	boolean removeRecord(String domainName) {
		return remove(root, "."+domainName);
	}
	/**
	 * removes given domainName from tree.
	 * @param root: is the current node in action.
	 * @param domainName: to be removed from tree.
	 * @return true if removed, false otherwise.
	 */
	private boolean remove(DnsNode root, String domainName) {
		/**
		 * these 3 lines of command below reverses given domain name and separates sub domain as wanted.
		 */
		String ters = mirror(domainName);
		String subDomain = mirror(ters.substring(0, ters.indexOf('.')));
		domainName = domainName.substring(0, domainName.length()-subDomain.length()-1);
		
		/**
		 * if current node has a child with given subDomain, continue to proceed,
		 * else return false.
		 */
		if(root.childNodeList.containsKey(subDomain)) {
			/**
			 * if reached end of the given domainName, continue to proceed.
			 */
			if(domainName.length() == 0) {
				/**
				 * if subDomain has no child, remove the node and return true,
				 * else; if subDomain is not valid return false,
				 * 		 else clear ipAddresses and make it non valid then return true.
				 */
				if(root.childNodeList.get(subDomain).childNodeList.isEmpty()) {
					root.childNodeList.remove(subDomain);
					return true;
				}
				else {
					if(root.childNodeList.get(subDomain).validDomain == false) {
						return false;
					}
					else {
						root.childNodeList.get(subDomain).validDomain = false;
						root.childNodeList.get(subDomain).ipAddresses.clear();
						root.childNodeList.get(subDomain).ipAddressQ.clear();
						return true;
					}
				}
			}
			/**
			 * calls for recursive operation.
			 */
			return remove(root.childNodeList.get(subDomain), domainName);
		}
		else return false;	
	}
	
	/**
	 * calls remove method to remove given domainName with ipAddress from tree.
	 * @param domainName: to be removed from tree.
	 * @param ipAddress: to be removed from tree.
	 * @return true if removed, false otherwise.
	 */
	boolean removeRecord(String domainName, String ipAddress) {
		return remove(root, "."+domainName, ipAddress);
	}
	/**
	 * removes given ipAddress from tree.
	 * @param root: is the current node in action.
	 * @param domainName: to be removed from tree.
	 * @param ipAddress: to be removed from tree.
	 * @return true if removed, false otherwise.
	 */
	private boolean remove(DnsNode root, String domainName, String ipAddress) {
		/**
		 * these 3 lines of command below reverses given domain name and separates sub domain as wanted.
		 */
		String ters = mirror(domainName);
		String subDomain = mirror(ters.substring(0, ters.indexOf('.')));
		domainName = domainName.substring(0, domainName.length()-subDomain.length()-1);
		
		/**
		 * if current node has a child with given subDomain, continue to proceed,
		 * else return false.
		 */
		if(root.childNodeList.containsKey(subDomain)) {
			/**
			 * if reached end of the given domainName, continue to proceed.
			 */
			if(domainName.length() == 0) {
				/**
				 * if subDomain has one ipAddress and that is equal to given, remove the node completely.
				 * if subDomain has more than one ipAddresses, remove just given ipAddress.
				 * if subDomain does not contain ipAddress return false.
				 */
				if(root.childNodeList.get(subDomain).ipAddresses.contains(ipAddress)) {
					if(root.childNodeList.get(subDomain).ipAddresses.size() == 1) {
						if(root.childNodeList.get(subDomain).childNodeList.isEmpty()) {
							root.childNodeList.remove(subDomain);
							return true;
						}
						else {
							root.childNodeList.get(subDomain).validDomain = false;
							root.childNodeList.get(subDomain).ipAddresses.clear();
							root.childNodeList.get(subDomain).ipAddressQ.clear();
							return true;
						}
					}
					else {
						root.childNodeList.get(subDomain).ipAddresses.remove(ipAddress);
						root.childNodeList.get(subDomain).ipAddressQ.remove(ipAddress);
						return true;
					}
				}
				else {
					return false;
				}
			}
			/**
			 * calls for recursive operation.
			 */
			return remove(root.childNodeList.get(subDomain), domainName, ipAddress);
		}
		else return false;
	}
	
	/**
	 * gives next ipAddress according to Round Robin Algorithm.
	 * @param domainName: to be processed.
	 * @return ipAddress coming up next.
	 */
	String queryDomain(String domainName) {
		return query(root, "."+domainName);
	}
	private String query(DnsNode root, String domainName) {
		String ters = mirror(domainName);
		String subDomain = mirror(ters.substring(0, ters.indexOf('.')));
		domainName = domainName.substring(0, domainName.length()-subDomain.length()-1);
		
		if(root.childNodeList.containsKey(subDomain)) {
			if(domainName.length() == 0) {
				Iterator<String> itr = root.childNodeList.get(subDomain).ipAddressQ.iterator();
				while(itr.hasNext()) {
					String isNull = itr.next();
					if(isNull == null) itr.remove();
				}
				String ip = root.childNodeList.get(subDomain).ipAddressQ.poll();
				root.childNodeList.get(subDomain).ipAddressQ.offer(ip);
				return ip;
			}
			return query(root.childNodeList.get(subDomain), domainName);
		}
		else return null;
	}
	
	/**
	 * 
	 * @return a newly created map whose keys are domainNames who has at least one ipAddress and values are those ipAddress or ipAddresses.
	 */
	Map<String, Set<String>> getAllRecords(){
		Map<String, Set<String>> domainsWithIp = new HashMap<String, Set<String>>();
		return getAllRecords(root, "", domainsWithIp);
	}
	private Map<String, Set<String>> getAllRecords(DnsNode root, String parent, Map<String, Set<String>> domainsWithIp){
		
		if(!root.childNodeList.isEmpty()) {
			
			Set<String> children = root.childNodeList.keySet();
			Iterator<String> itr = children.iterator();
			
			while(itr.hasNext()) {
				
				String key = itr.next();
				String fullDomain = "." + key + parent;
				
				if(!root.childNodeList.get(key).ipAddresses.isEmpty()) {
					domainsWithIp.put(fullDomain.substring(1), root.childNodeList.get(key).ipAddresses);
				}
				
				getAllRecords(root.childNodeList.get(key), fullDomain, domainsWithIp);
			}
			
		}
		return domainsWithIp;
	}
	
	/**
	 * 
	 * @param text: to be reversed.
	 * @return reversed string.
	 */
	private String mirror(String text) {
		Stack<Character> reverser = new Stack<Character>();
		for(int i=0; i<text.length(); i++) {
			reverser.push(text.charAt(i));
		}
		String ters = "";
		for(int i=0; i<text.length(); i++) {
			ters += reverser.pop();
		}
		return ters;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

