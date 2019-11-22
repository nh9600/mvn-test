package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharTest {
	public static void main(String[] args) {
		String sPath = "c:\\test.txt";
		String tPath = "c:\\test1.txt";
		try {
			FileInputStream fis = new FileInputStream(sPath);//파일을 싹다 가져옴? 
			InputStreamReader isr = new InputStreamReader(fis,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			String string = "";
			while((str=br.readLine())!=null) {//라인별로 읽어서 
				System.out.println(str);//찍음
				string += str;
			}
			FileOutputStream fos = new FileOutputStream(tPath);
			OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");	
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(string);
			bw.flush();
			//int s = fis.read();//한번씩 끄집어 냄<=이렇게 할 필요는 없다 
			//System.out.println((char)s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
