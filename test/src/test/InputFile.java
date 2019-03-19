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
	        	//����׷��
	            sb.append(text);
	        }
	        //�ر���
	        br.close();
	        // ��stringBufferתΪ�ַ���ת��ΪСд
	        String str = sb.toString().toLowerCase(); 
	        // ������ʽ    �ǵ��ʵ��ַ����ָ�õ����е���
	        String[] words = str.split("[^(a-zA-Z)]+"); 
	        Map<String ,Integer> map = new HashMap<String, Integer>() ;

	        for(String word :words){
	        	// ��������˵���ǵ�һ�Σ�����뵽map,���ִ���Ϊ1
	            if(map.get(word)==null){  
	                map.put(word,1);
	            }else{
	            	 // �����ڣ������ۼ�1
	                map.put(word,map.get(word)+1); 
	            }
	        }
	       
	        
	        // ����
	        List<Map.Entry<String ,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
	        Comparator<Map.Entry<String,Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
	                return (left.getValue().compareTo(right.getValue()));
	            }
	        };
	        //����
	        Collections.sort(list,comparator);
	        Map<String, Integer> m = new HashMap<String, Integer>();
	        for(int i=0;i<list.size();i++){// �ɸߵ������
	            System.err.println(list.get(list.size()-i-1).getKey() +":"+list.get(list.size()-i-1).getValue());
	            m.put(list.get(list.size()-i-1).getKey(),list.get(list.size()-i-1).getValue());
	        }
	        WriteStringToFile("D:/b.txt",list);//д���ı�
	        String word="";
	        while(true) {
	         word = inputWord();
	         word = word.replaceAll(" ", "");
	         //��ѯ���뵥��
	         if(map.get(word)!=null) {
	        	 System.out.println("����");
	        	 System.err.println("��ѯ�����\n"+"���ʣ�"+word+"���ֵĴ�����"+map.get(word)+"\n");
	         }else {
	        	 System.err.println("��ѯ�����\n"+"���ʣ�"+word+"���ֵĴ�����"+word+":0\n");
	         }
	        }

	    }
	 /**
	  * ���ʲ�ѯ
	  * @return
	  */
	 public static String inputWord() {
			 System.out.println("�����ѯ���ʣ�");
			 Scanner input = new Scanner(System.in);
			 String word = input.nextLine();
			 return word;
	 }
	 
	 /**
	  * д��txt�ĵ�
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
	            //д���ı�
		        //����
	            Collections.sort(list,comparator1);       
	            ps.append(list.toString());
	            ps.close();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	 }
}
