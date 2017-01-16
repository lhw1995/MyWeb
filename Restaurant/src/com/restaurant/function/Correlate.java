package com.restaurant.function;


import com.restaurant.entity.*;

import java.util.*;
import java.util.Map.Entry;

/*
 * 顾客口味相似度计算
 */
public class Correlate {

	/*
	 * 欧几里得距离计算每一对用户之间的相似度
	 */
	public double sim_distance(IdentityHashMap<Integer,Map<Integer,Double>> dataMap, Integer consumer1, Integer consumer2) {
		Map<Integer, Integer> si = new HashMap<>();  //菜品对应评分
		Integer dishID;    //菜品
		List<Double> grade1 = new ArrayList<>();  //用户评分
		List<Double> grade2 = new ArrayList<>();  //用户评分
		double result = 0;

		if (dataMap.containsKey(consumer1) && dataMap.containsKey(consumer2)) {
			Set<Entry<Integer, Map<Integer, Double>>> outSet = dataMap.entrySet();
			for(Entry<Integer, Map<Integer, Double>> keyEntry : outSet){
				if (keyEntry.getKey().equals(consumer1)){
					Set<Entry<Integer, Double>> inSet = keyEntry.getValue().entrySet();
					for (Entry<Integer, Double> key : inSet){
						dishID = key.getKey();
						si.put(dishID, 0);
						grade1.add(key.getValue());
					}
					break;
				}
			}

			for(Entry<Integer, Map<Integer, Double>> keyEntry : outSet){
				if (keyEntry.getKey().equals(consumer2)) {
					Set<Entry<Integer, Double>> inSet = keyEntry.getValue().entrySet();
					for (Entry<Integer, Double> key : inSet){
						dishID = key.getKey();
						if (si.containsKey(dishID)) {
							si.put(dishID, 1);
							grade2.add(key.getValue());
						} else {
							grade1.remove(key.getValue());
						}
					}
				}
			}

		}

		if (si.isEmpty() || !si.containsValue(1)) {
			return result;
		} else {
			for (int i = 0; i < grade1.size(); i++) {
				result += Math.pow(grade1.get(i) - grade2.get(i), 2);
			}
		}

		result = 1 / (1 + Math.sqrt(result));
		return result;
	}

	/*
	 * 利用皮尔逊相关系数计算每一对用户之间的相似度
	 */
	public double sim_pearson(IdentityHashMap<Integer,Map<Integer,Double>> dataMap, Integer consumer1, Integer consumer2) {
		double result = 0;	//相似度
		double sum1 = 0;	//用户评分和
		double sum2 = 0;
		double sum1Sq = 0;   //评分的平方和
		double sum2Sq = 0;
		double pSum = 0;	//亮用户评分的乘积和
		double num,den;

		Map<Integer, Integer> si = new HashMap<>();  //菜品对应评分
		Integer dish1 = null;    //菜品
		Map<Integer, Double> grade1 = new HashMap<>();  //用户评分
		Map<Integer, Double> grade2 = new HashMap<>();  //用户评分



		if (dataMap.containsKey(consumer1) && dataMap.containsKey(consumer2)) {
			Set<Entry<Integer, Map<Integer, Double>>> outSet = dataMap.entrySet();
			for(Entry<Integer, Map<Integer, Double>> keyEntry : outSet){
				if (keyEntry.getKey().equals(consumer1)){
					Set<Entry<Integer, Double>> inSet = keyEntry.getValue().entrySet();
					for (Entry<Integer, Double> key : inSet){
						si.put(key.getKey(), 0);
						grade1.put(key.getKey(), key.getValue());
					}
					break;
				}
			}

			for(Entry<Integer, Map<Integer, Double>> keyEntry : outSet){
				if (keyEntry.getKey().equals(consumer2)) {
					Set<Entry<Integer, Double>> inSet = keyEntry.getValue().entrySet();
					for (Entry<Integer, Double> key : inSet){
						if (grade1.keySet().contains(key.getKey())) {
							si.put(key.getKey(), 1);
							grade2.put(key.getKey(), key.getValue());
							System.out.println("这里的grade2是"+grade2);
						} else {
							grade1.remove(dish1);
						}
					}
				}
			}


		}

		if (si.size() == 0) {
			return result;
		}

		/*保留两个用户都评分的项*/
		Set<Entry<Integer, Double>> set1 = grade1.entrySet();
		Set<Entry<Integer, Double>> set2 = grade2.entrySet();

		for(Entry<Integer, Double> key : set1){
			sum1 += key.getValue();
			sum1Sq += Math.pow(key.getValue() , 2);
		}

		for(Entry<Integer, Double> key : set2){
			sum2 += key.getValue();
			sum2Sq += Math.pow(key.getValue(), 2);
		}

		for(Integer dishID : grade1.keySet()){
			pSum += grade1.get(dishID) * grade2.get(dishID);
		}

		/*皮尔逊相关系数计算*/
		num = Math.abs(pSum - (sum1 * sum2 / grade1.size()));
		den=Math.sqrt((sum1Sq-Math.pow(sum1,2)/grade1.size())*(sum2Sq-Math.pow(sum2,2)/grade1.size()));
		if (den == 0) {
			return 0;
		}
		result = num / den;
		return result;
	}

	/*
	 * 找出相似用户
	 */
	public Map<Integer, Double> topMatches(IdentityHashMap<Integer,Map<Integer,Double>> dataMap,Integer Integer,int matchCount) {
		Map<Integer,Double> matches = new HashMap<>();  //相似用户
		Map<Integer, Double> topMatches = new HashMap<>();  //相似度最高的前matchCount位用户
		double result;
		int i = 1;
		Set<Entry<Integer, Map<Integer, Double>>> outSet = dataMap.entrySet();
		for(Entry<Integer, Map<Integer, Double>> keyEntry : outSet){
			if (keyEntry.getKey().equals(Integer)) {//若搜索用户与当前用户为同一个则跳出
				continue;
			} else {
				result = sim_pearson(dataMap,Integer,keyEntry.getKey());
				matches.put(keyEntry.getKey(),result);
			}
		}

		/*按matches的value排序*/
		List<Entry<Integer, Double>> list = new LinkedList<Entry<Integer, Double>>(matches.entrySet());
		Collections.sort( list, new Comparator<Entry<Integer, Double>>(){
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				if (o1.getValue() > o2.getValue()) {
					return 1;
				} else if (o1.getValue() < o2.getValue()) {
					return -1;
				}
				return (o1.getValue()).compareTo( o2.getValue() );
			}
		} );

		//匹配对应个数的consumer
		for (Entry<Integer, Double> entry : list)
		{
			topMatches.put( entry.getKey(), entry.getValue() );
			i++;
			if (i == matchCount) {
				break;
			}
		}
		return topMatches;
	}

	/*
	 * 利用所有人的打分及相关度的不同获取推荐列表
	 */
	public List<Integer> getRecommendations(IdentityHashMap<Integer,Map<Integer,Double>> dataMap,Integer Integer) {
		Map<Integer, Double> results = new HashMap<>();
		Map<Integer, Double> totals = new HashMap<>();
		double sim;
		double simSum = 0;
		Set<Entry<Integer, Map<Integer, Double>>> outSet = dataMap.entrySet();
		for(Entry<Integer, Map<Integer, Double>> keyEntry : outSet){
			if (keyEntry.getKey().equals(Integer) ) {//若搜索用户与当前用户为同一个则跳出
				continue;
			}
			//利用皮尔逊计算相关度
			sim = sim_pearson(dataMap, Integer, keyEntry.getKey());
			System.out.println(sim + "!!!!!!!!!!!!!!!");
			if (sim <= 0) {//忽略相似度为0或者是小于0的情况
				continue;
			}
			simSum += sim; //相似度之和
			//对未进行评分或评分为0的菜品进行操作
			//if (keyEntry.getValue() == null) {
			double temp;
			Set<Entry<Integer, Double>> inSet = keyEntry.getValue().entrySet();
			for (Entry<Integer, Double> key : inSet){//打过分的评分与相似度的乘积求和
				if (key.getValue() == null) {
					totals.put(key.getKey(), (double) 0);
					temp = 0;
				} else {
					totals.put(key.getKey(), key.getValue());
					temp = key.getValue();
				}
				temp += key.getValue()*sim;
				totals.put(key.getKey(), temp);
			}
			//}
		}

		Set<Entry<Integer, Double>> set = totals.entrySet();
		for(Entry<Integer, Double> key : set){
			results.put(key.getKey(), key.getValue() / simSum);
		}


		//对结果排序
		List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(results.entrySet());
		Collections.sort( list, new Comparator<Map.Entry<Integer, Double>>(){
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				if (o1.getValue() > o2.getValue()) {
					return -1;
				} else if(o1.getValue() < o2.getValue()){
					return 1;
				}
				return 0;
			}
		});
		System.out.println("results="+results);
		System.out.println("datamap="+dataMap);
		System.out.println("list="+list);
		List<Integer> dishList = new ArrayList<>();
		for (Map.Entry<Integer,Double> entry:list){
			dishList.add(entry.getKey());
		}
		return dishList;
	}
}
