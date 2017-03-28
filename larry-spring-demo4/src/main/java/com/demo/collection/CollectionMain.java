package com.demo.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionMain {
	
	private static String n;
	
	private static Logger LOG = LoggerFactory.getLogger(CollectionMain.class);

	public static void main(String[] args){
		//Set
		Set<String> st = new HashSet<>();
		st.add("Fwd");
		st.add("fwd");
		st.add("Gxm");
		st.add("gxm");
		Iterator<String> it = st.iterator();
		while(it.hasNext()){
			LOG.info("value:"+it.next());
		}
		Set<String> sortSet = new TreeSet<>(st);
		it = sortSet.iterator();
		while(it.hasNext()){
			LOG.info("sort:"+it.next());
		}
		//List
		List<String> list = new LinkedList<>();
		list.add("Fwd");
		list.add("Gxm");
		list.add("fwd");
		list.add("gxm");
		ListIterator<String> listIt = list.listIterator();
		while(listIt.hasNext()){
			LOG.info("list:"+listIt.next());
		}
		//随机排序
		Collections.shuffle(list);
		listIt = list.listIterator();
		while(listIt.hasNext()){
			LOG.info("shuffle:"+listIt.next());
		}
		//排序
		Collections.sort(list);
		listIt = list.listIterator();
		while(listIt.hasNext()){
			LOG.info("sort:"+listIt.next());
		}
		Collections.reverse(list);
		listIt = list.listIterator();
		while(listIt.hasNext()){
			LOG.info("reverse:"+listIt.next());
		}
		//Map
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "fwd");
		String val = map.put("b", "gxm");
		LOG.info("val:"+val);
		val = map.remove("b");
		LOG.info("val:"+val);
		for(Entry<String, String> e : map.entrySet()){
			LOG.info("key:"+e.getKey());
			LOG.info("val:"+e.getValue());
		}
		System.out.println(n);
	}
}
