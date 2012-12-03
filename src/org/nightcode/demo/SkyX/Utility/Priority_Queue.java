package org.nightcode.demo.SkyX.Utility;

import java.util.ArrayList;

public class Priority_Queue{
	private int capcity;
	private int size;
	private Comparable[] heap;
	public Priority_Queue(int capcity){
		this.capcity=capcity;
		heap=new Comparable[this.capcity];
		size=0;
	}
	public Object Top(){
		if(size==0) return null;
		else return heap[0];
	}
	public boolean isEmpty(){
		return (size==0);
	}
	public synchronized void removeLast()
	{
		if(!isEmpty()){
			size--;
		}
	}
	protected synchronized  void down_heap
						(int i)
	{
		do{
		int left=2*(i)+1;
		int right=left+1;
		int min=i;
		if(left<size&&heap[left].compareTo(heap[i])<0)
			min=left;
		if(right<size&&heap[right].compareTo(heap[min])<0)
			min=right;
			if(min!=i) {
				Comparable tmp=heap[i];
				heap[i]=heap[min];
				heap[min]=tmp;
				i=min;
			}
			else break;
		}while(true);
	}
	public synchronized void pop(){
		size--;
		heap[0]=heap[size];
		down_heap(0);
	}
	public int getSize()
	{
		return size;
	}
	public synchronized void Insert(Comparable a){
		if(size<capcity){
			
			int curPos=size;
			while(curPos>0&&a.compareTo(heap[(curPos-1)/2])<0)
			{
				//System.out.print(true);
				curPos=(curPos-1)/2;
			}
			//System.out.println(curPos);
			heap[size]=heap[curPos];
			heap[curPos]=a;
			size++;
		}
	}
	public ArrayList toArrayList(){
		ArrayList result=new ArrayList(size);
		for(int i=0;i<heap.length;i++){
			result.add(heap[i]);
		}
		return result;
	}
}
