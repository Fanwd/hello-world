package com.demo.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试List
 * @author fanweidong
 *
 */
public class TestList<E> {
	
	private static Logger LOG = LoggerFactory.getLogger(TestList.class);
	
	private Node<E> first;
	private Node<E> last;
	private int count;
	
	public void add(E obj){
		if(last == null){
			count = 0;
			first = new Node<E>(null, obj, null);
			last = first;
			count++;
		}else{
			Node<E> node = new Node<>(last, obj, null);
			last = node;
			count++;
		}
	}
	
	public void remove(int index){
		if(count <= index){
			LOG.error("OUT OF INDEX");
			return;
		}
		if(count == 1){
			first = null;
			last = null;
			count = 0;
			return ;
		}
		Node<E> node = first;
		for(int i = 0; i < index; i++){
			node = node.next;
		}
		if(node.pre != null){
			if(node.next == null){
				last = node.pre;
			}
			node.pre.next = node.next;
		}
		if(node.next != null){
			if(node.pre == null){
				first = node.next;
			}
			node.next.pre = node.pre;
		}
		count--;
	}
	
	public int size(){
		return count;
	}
	
	public E get(int index){
		if(count <= index){
			LOG.error("OUT OF INDEX");
			return null;
		}
		Node<E> node = first;
		for(int i = 0; i < index; i++){
			node = node.next;
		}
		return node.item;
	}
	
	private class Node<N> {
		N item;
		Node<N> pre;
		Node<N> next;
		Node(Node<N> p, N ele, Node<N> n){
			if(p != null){
				p.next = this;
			}
			this.pre = p;
			this.item = ele;
			this.next = n;
			if(n != null){
				n.pre = this;
			}
		}
	}
	
	public static void main(String[] args){
		TestList<String> list = new TestList<>();
		list.add("123");
		list.add("fwd");
		list.add("gxm");
		list.add("end");
		LOG.info("size:"+list.size());
		for(int i = 0; i < list.size(); i++){
			String val = list.get(i);
			LOG.info(i+":"+val);
		}
		list.remove(0);
		LOG.info("size:"+list.size());
		for(int i = 0; i < list.size(); i++){
			String val = list.get(i);
			LOG.info(i+":"+val);
		}
		list.remove(2);
		LOG.info("size:"+list.size());
		for(int i = 0; i < list.size(); i++){
			String val = list.get(i);
			LOG.info(i+":"+val);
		}
		list.remove(0);
		LOG.info("size:"+list.size());
		for(int i = 0; i < list.size(); i++){
			String val = list.get(i);
			LOG.info(i+":"+val);
		}
		list.remove(0);
		LOG.info("size:"+list.size());
		for(int i = 0; i < list.size(); i++){
			String val = list.get(i);
			LOG.info(i+":"+val);
		}
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		LOG.info("size:"+list.size());
		for(int i = 0; i < list.size(); i++){
			String val = list.get(i);
			LOG.info(i+":"+val);
		}
	}

}
