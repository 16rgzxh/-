package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputFile {
	
	 public static void main(String [] args) throws Exception {
		 	
	        BufferedReader br = new BufferedReader(new FileReader("D:/a.txt"));
	        StringBuffer sb = new StringBuffer();
	        String text="";
	        System.out.println(br.readLine()!=null);
	        while ((text=br.readLine())!=null){
	        	//遍历追加
	            sb.append(text);
	        }
	        //关闭流
	        br.close();
	        // 将stringBuffer转为字符并转换为小写
	        String str = sb.toString().toLowerCase(); 
	        // 正则表达式    非单词的字符来分割，得到所有单词
	        String[] words = str.split("[^(a-zA-Z)]+"); 
	        Map<String ,Integer> map = new HashMap<String, Integer>() ;

	        for(String word :words){
	        	// 若不存在说明是第一次，则加入到map,出现次数为1
	            if(map.get(word)==null){  
	                map.put(word,1);
	            }else{
	            	 // 若存在，次数累加1
	                map.put(word,map.get(word)+1); 
	            }
	        }
	       
	        
	        // 排序
	        List<Map.Entry<String ,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
	        Comparator<Map.Entry<String,Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
	                return (left.getValue().compareTo(right.getValue()));
	            }
	        };
	        //升序
	        Collections.sort(list,comparator);
	        Map<String, Integer> m = new HashMap<String, Integer>();
	        for(int i=0;i<list.size();i++){// 由高到低输出
	            System.err.println(list.get(list.size()-i-1).getKey() +":"+list.get(list.size()-i-1).getValue());
	            m.put(list.get(list.size()-i-1).getKey(),list.get(list.size()-i-1).getValue());
	        }
	        WriteStringToFile("D:/b.txt",list);//写入文本
	        String word="";
	        while(true) {
	         word = inputWord();
	         word = word.replaceAll(" ", "");
	         //查询输入单词
	         if(map.get(word)!=null) {
	        	 System.out.println("进来");
	        	 System.err.println("查询结果：\n"+"单词："+word+"出现的次数："+map.get(word)+"\n");
	         }else {
	        	 System.err.println("查询结果：\n"+"单词："+word+"出现的次数："+word+":0\n");
	         }
	        }

	    }
	 /**
	  * 单词查询
	  * @return
	  */
	 public static String inputWord() {
			 System.out.println("输入查询单词：");
			 Scanner input = new Scanner(System.in);
			 String word = input.nextLine();
			 return word;
	 }
	 
	 /**
	  * 写入txt文档
	  * @param filePath
	  */
	 public static void WriteStringToFile(String filePath,List<Map.Entry<String ,Integer>> list) {
	        try {
	            File file = new File(filePath);
	            PrintStream ps = new PrintStream(new FileOutputStream(file));
	            Comparator<Map.Entry<String,Integer>> comparator1 = new Comparator<Map.Entry<String, Integer>>() {
		            public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
		                return (right.getValue().compareTo(left.getValue()));
		            }
		        };
	            //写入文本
		        //降序
	            Collections.sort(list,comparator1);       
	            ps.append(list.toString());
	            ps.close();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	 }
}
