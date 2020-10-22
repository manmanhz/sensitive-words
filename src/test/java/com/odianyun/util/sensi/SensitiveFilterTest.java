package com.odianyun.util.sensi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;

public class SensitiveFilterTest extends TestCase{
	
	public void test() throws Exception{
		
		// 使用默认单例（加载默认词典）
		SensitiveFilter filter = SensitiveFilterManager.filter();
		// 向过滤器增加一个词
		filter.put("婚礼上唱春天在哪里");
		
		// 待过滤的句子
		String sentence = "然后，市长在婚礼上唱春天在哪里。";
		// 进行过滤
		String filted = filter.filter(sentence, '*');
		
		// 如果未过滤，则返回输入的String引用
		if(sentence != filted){
			// 句子中有敏感词
			System.out.println(filted);
		}
		
	}
	
	public void testLogic(){
		
		DefaultSensitiveFilter filter = new DefaultSensitiveFilter();
		
		filter.put("你好");
		filter.put("你好1");
		filter.put("你好2");
		filter.put("你好3");
		filter.put("你好4");
		
		System.out.println(filter.filter("你好3天不见", '*'));
		
	}
	
	public void testSpeed() throws Exception{
		
		PrintStream ps = new PrintStream("/data/敏感词替换结果.txt");
		
		File dir = new File("/data/穿越小说2011-10-14");
		
		List<String> testSuit = new ArrayList<String>(1048576);
		long length = 0;
		
		for(File file: dir.listFiles()){
			if(file.isFile() && file.getName().endsWith(".txt")){
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb18030"));
				for(String line = br.readLine(); line != null; line = br.readLine()){
					if(line.trim().length() > 0){
						testSuit.add(line);
						length += line.length();
					}
				}
				br.close();
			}
		}
		
		System.out.println(String.format("待过滤文本共 %d 行，%d 字符。", testSuit.size(), length));


		SensitiveFilter filter = SensitiveFilterManager.filter();
		
		int replaced = 0;
		
		for(String sentence: testSuit){
			String result = filter.filter(sentence, '*');
			if(result != sentence){
				ps.println(sentence);
				ps.println(result);
				ps.println();
				replaced ++;
			}
		}
		ps.close();
		
		long timer = System.currentTimeMillis();
		for(String line: testSuit){
			filter.filter(line, '*');
		}
		timer = System.currentTimeMillis() - timer;
		System.out.println(String.format("过滤耗时 %1.3f 秒， 速度为 %1.1f字符/毫秒", timer * 1E-3, length / (double) timer));
		System.out.println(String.format("其中 %d 行有替换", replaced));
		
	}

	public void test2() {

		String sensitiveWords = "习近平,\n" + "平近习,\n" + "xjp,\n" + "习太子,\n" + "习明泽,\n" + "老习,\n" + "温家宝,\n" + "温加宝,\n"
			+ "温x,\n" + "温jia宝,\n" + "温宝宝,\n" + "温加饱,\n" + "温加保,\n" + "张培莉,\n" + "温云松,\n" + "温如春,\n" + "温jb,\n"
			+ "胡温,\n" + "胡x,\n" + "胡jt,\n" + "胡boss,\n" + "胡总,\n" + "胡王八,\n" + "hujintao,\n" + "胡jintao,\n" + "胡j涛,\n"
			+ "胡惊涛,\n" + "胡景涛,\n" + "胡紧掏,\n" + "湖紧掏,\n" + "胡紧套,\n" + "锦涛,\n" + "hjt,\n" + "胡派,\n" + "胡主席,\n" + "刘永清,\n"
			+ "胡海峰,\n" + "胡海清,\n" + "江泽民,\n" + "民泽江,\n" + "江胡,\n" + "江哥,\n" + "江主席,\n" + "江书记,\n" + "江浙闽,\n" + "江沢民,\n"
			+ "江浙民,\n" + "择民,\n" + "则民,\n" + "茳泽民,\n" + "zemin,\n" + "ze民,\n" + "老江,\n" + "老j,\n" + "江core,\n" + "江x,\n"
			+ "江派,\n" + "江zm,\n" + "jzm,\n" + "江戏子,\n" + "江蛤蟆,\n" + "江某某,\n" + "江贼,\n" + "江猪,\n" + "江氏集团,\n" + "江绵恒,\n"
			+ "江绵康,\n" + "王冶坪,\n" + "江泽慧,\n" + "邓小平,\n" + "平小邓,\n" + "xiao平,\n" + "邓xp,\n" + "邓晓平,\n" + "邓朴方,\n"
			+ "邓榕,\n" + "邓质方,\n" + "毛泽东,\n" + "猫泽东";
		DefaultSensitiveFilter filter = new DefaultSensitiveFilter();
		for (String sensitiveWord : sensitiveWords.split(",\n")) {
			filter.put(sensitiveWord);
		}

		String text1 = "这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子这是一部黄色电影，江江江江将，温抱抱，习太子";

		System.out.println(filter.filter(text1, '*'));

	}

	public void testRebuild() {
		List<String> sensitiveWords = Arrays.asList("aaa", "bbb", "ccc");
		SensitiveFilterManager.init(sensitiveWords);
		Set<String> filted = SensitiveFilterManager.filter().findWords("aaaaaabcccc");
		filted.forEach(x->{
			System.out.println(x);
		});

		sensitiveWords = Arrays.asList("ddd", "mmmm", "nnnnn");
		SensitiveFilterManager.filter().rebuild(sensitiveWords);
		filted = SensitiveFilterManager.filter().findWords("aaaaaabcccc");
		filted.forEach(x->{
			System.out.println(x);
		});

		sensitiveWords = Arrays.asList("aaa", "bbb", "ccc");
		SensitiveFilterManager.filter().rebuild(sensitiveWords);
		filted = SensitiveFilterManager.filter().findWords("aaaaaabcccc");
		filted.forEach(x->{
			System.out.println(x);
		});
	}
}
