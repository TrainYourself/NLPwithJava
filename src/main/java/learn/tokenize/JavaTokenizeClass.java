package learn.tokenize;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * Sử dụng các lớp có sẵn trong Java để thực hiện tokenize dua 
 * tren cac ky tu phan cach (space, tab...)
 * 
 * Trong tài liệu có thể có nhiều phương pháp, ở đây tôi chỉ
 * tập trung vào các phương pháp mà tôi cho là phổ biến và "tốt"
 * hơn. Nếu bạn muốn biết thêm các phương pháp khác bạn có thể
 * tham khảo tài liệu.
 *
 */

import java.util.*;

import ulti.QuickPrint;

public class JavaTokenizeClass extends QuickPrint{
	
	/**
	 * Sử dụng StreamTokenizer
	 * Có thể sử dụng để lọc ra các number có trong một câu.
	 * Khi sử dụng cần chú ý các ký tự đặc biệt "'", "," cần
	 * chú ý sử dụng ordinaryChar.
	 * Let's go to see a movie -> Let
	 * set ordinaryChar('\'') -> Let ' s ...
	 * @param input
	 */
	
	public enum FLAGS {ONLY_NUMBERS, ONLY_WORDS, DEFAULT}
	
	public static void streamTokenizer(String input, FLAGS flags) {
		StreamTokenizer st = new StreamTokenizer( new StringReader(input));
		st.ordinaryChar('\'');
		boolean isEOF = false;
		try {
		while(!isEOF) {
			int token = st.nextToken();
			switch(token) {
				// Ket thuc Stream -> Ket thuc
				case StreamTokenizer.TT_EOF:
					isEOF = true;
					break;
				// Neu la ky tu xuong dong -> bo qua
				case StreamTokenizer.TT_EOL:
					break;
				// Neu la ky tu so -> in ra
				case StreamTokenizer.TT_NUMBER:
					// Neu cho phep in ra so -> in ket qua
					if(!flags.equals(FLAGS.ONLY_WORDS))
						prt("Number: "+st.nval);
					break;
				// Neu la tu -> in ra
				case StreamTokenizer.TT_WORD:
					// Neu cho phep in ra tu -> in ket qua
					if(!flags.equals(FLAGS.ONLY_NUMBERS))
						prt("Word: "+st.sval);
					break;
				// Khong xac dinh duoc loai -> in ra thong bao
				default:
					// Khong nhan dien duoc loai -> \n
					prt("\n");
			}
		}
		} catch (IOException e) {
			prt("IOException at StreamTokenizer!!!");
		}
	}
	/**
	 * Sử dụng StringTokenizer class, thường được sử dụng phổ biến hơn
	 * so với StreamTokenizer.
	 * @param input
	 */
	public static void stringTokenizer(String input) {
		StringTokenizer st = new StringTokenizer(input);
		while(st.hasMoreTokens()) {
			prt(st.nextToken());
		}	
	}
	
	public static void main(String args[]){
		JavaTokenizeClass.stringTokenizer("Today is Jan 22 2016, and teamperature is 37.5");
		JavaTokenizeClass.stringTokenizer("Let's go to see a movie!!");
		JavaTokenizeClass.streamTokenizer("Today is Jan 22 2016, and teamperature is 37.5", FLAGS.DEFAULT);
		JavaTokenizeClass.streamTokenizer("Let's go to see a movie!!", FLAGS.DEFAULT);
	}
}

