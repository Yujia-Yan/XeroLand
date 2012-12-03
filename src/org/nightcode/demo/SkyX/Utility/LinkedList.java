package org.nightcode.demo.SkyX.Utility;
/*this class implements a basic linkedList
 * 
 */
public class LinkedList {
	public class ListNode{
		Object value;
		ListNode next;
		ListNode prev;
		public ListNode(){
			
		}
		public ListNode(Object a){
			value=a;
		}
	}
//////////////////////////////////////////////////////////////
	ListNode head;
	ListNode tail;
	public LinkedList(){
		head=new ListNode();
		tail=head;
	}
	public boolean isEmpty(){
		return (tail==head);
	}
	public void addLast(Object a){
		ListNode tmp=new ListNode(a);
		tmp.prev=tail;
		tail.next=tmp;
		tail=tmp;
	}
	public void addFirst(Object a){
		ListNode tmp=new ListNode(a);
		tmp.next=head.next;
		tmp.prev=head;
		head.next=tmp;
	}
	public void removeLast(){
		if(!isEmpty()){
			tail=tail.prev;
			tail.next=null;
		}
	}
	public void removeFirst(){
		if(!isEmpty()){
			head.next=head.next.next;
			if(head.next==null)tail=head;
		}
		
	}
	public void clear(){
		head.next=null;
		tail=head;
	}
	
}
