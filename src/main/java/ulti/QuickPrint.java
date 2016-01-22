/**
 * @author TuanPM
 * @since Jan 14, 2016
 */

package ulti;

import java.util.List;

public class QuickPrint {
	// In 1 xau ra man hinh
	public static void prt(String s){System.out.println(s);}
	
	// In ra object
	public static void prtObj(Object obj){
		if(obj != null)
			prt(obj.toString());
		else
			prt("null");
		prt("\n");
	}
	
	// In ra list object
	public static void prtList(List<? extends Object> lst){
		if(lst != null)
			for(Object obj: lst) prt(obj.toString());
		else
			prt("null");
		prt("\n");
	}
}

