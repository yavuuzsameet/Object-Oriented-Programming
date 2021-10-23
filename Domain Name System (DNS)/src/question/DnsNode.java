
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;
import java.util.*;
 /**
  * 
  * @author Yavuz
  *
  */
class DnsNode {
	
	Map<String, DnsNode> childNodeList;
	boolean validDomain;
	Set<String> ipAddresses;
	Queue<String> ipAddressQ;
	
	/**
	 * constructs a DnsNode.
	 */
	DnsNode(){
		childNodeList = new HashMap<String, DnsNode>();
		ipAddresses = new HashSet<String>();
		validDomain = false;
		ipAddressQ = new LinkedList<String>();
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

