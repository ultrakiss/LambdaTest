package com.pandaspark.testlambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 
 * @author chiu_
 *
 * B has a Long Id and String Code
 * Map of String Code to B object
 * A has a String Code
 * Given A, find B's Long Id that B's String Code that match A's String Code
 */
public class MapFromAToB {
	public <ST, L, B, A> L lookup(Map<ST, B> map, A srcObj, Function<A, ST> f1,
			Function<B, L> f2) {
		ST s = f1.apply(srcObj);
		
		B b = map.get(s);
		
		if (null == b) return null;
		
		L l = f2.apply(b);
		
		return l;
	}
	
	public static void main(String [] args) {
		AnObjB b1 = new AnObjB(11L, "one");
		AnObjB b2 = new AnObjB(12L, "twelve");
		
		Map<String, AnObjB> map = new HashMap<>();
		map.put(b1.getbCode(), b1);
		map.put(b2.getbCode(), b2);
		
		MapFromAToB mfatb = new MapFromAToB();
		

		AnObjA a1 = new AnObjA(1L, "one");
		AnObjA a2 = new AnObjA(2L, "two");
		
		//
		// Should be 11
		Long tLong = mfatb.lookup(map, a1, AnObjA::getCode, AnObjB::getbId);
		System.out.println("1:" + tLong);
		
		//
		// Should be null
		tLong = mfatb.lookup(map, a2, AnObjA::getCode, AnObjB::getbId);
		System.out.println("2:" + tLong);
	}
}
