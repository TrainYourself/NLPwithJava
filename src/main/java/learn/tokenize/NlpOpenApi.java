/**
 * @author TuanPM
 * @since Jan 14, 2016
 */

package learn.tokenize;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;
import ulti.QuickPrint;

public class NlpOpenApi extends QuickPrint{
	
	enum PRINT_MODE {DEFAULT, TOKENS, POSITIONS};
	
	/**
	 * Sử dụng SimpleTokenizer class, các punctuation được coi là một token.
	 * @param input
	 * @param pm PrintMode
	 */
	public static void simpleTokenizer(String input, PRINT_MODE pm) {
		SimpleTokenizer st = SimpleTokenizer.INSTANCE;
		prt("\n-Simple Tokenizer-");
		tokenize(st, input, pm);
		
	}
	
	/**
	 * Sử dụng WhitespaceTokenizer class, sử dụng delimeter là space 
	 * @param input
	 * @param pm PrintMode
	 */
	public static void whiteSpaceTokenizer(String input, PRINT_MODE pm) {
		WhitespaceTokenizer st = WhitespaceTokenizer.INSTANCE;
		prt("\n-WhiteSpace Tokenizer-");
		tokenize(st, input, pm);	
	}
	
	/**
	 * Sử dụng Maximum Entropy
	 * @param input
	 * @param pm PrintMode
	 */
	public static void tokenizerME(String input, PRINT_MODE pm) {
		
		prt("\n-Tokenizer using Maximum Entropy-");
		
		try{
			InputStream modelInputStream = new 
					FileInputStream( new File("src/main/resources/model", "en-token.bin"));
			TokenizerModel model = new TokenizerModel(modelInputStream);
			Tokenizer tokenizer = new TokenizerME(model); 
			tokenize(tokenizer, input, pm);
		}catch (IOException ex) {
			prt("Error at loading model!!!");
		}
	}
	
	/**
	 * In ra kết các token/position theo tùy chọn của người dùng.
	 * @param t Tokenizer class
	 * @param input
	 * @param pm PrintMode
	 */
	public static void tokenize(Tokenizer t, String input, PRINT_MODE pm) {
		if(!pm.equals(PRINT_MODE.POSITIONS)) {
			prt("List of Tokens: ");
			String tokens[] = t.tokenize(input);
			for(String token : tokens) 
				prt(token);
		}
		// In ra Position neu co
		if(!pm.equals(PRINT_MODE.TOKENS)) {
			prt("List of Positions: ");
			Span tokens[] = t.tokenizePos(input);
			for(Span token : tokens) 
				prt(token.toString());
		}
	}
	
	public static void main(String[] args) {
		String paragraph = "Let's pause, \nand then reflect.";
		
		NlpOpenApi.simpleTokenizer(paragraph, PRINT_MODE.DEFAULT);
		NlpOpenApi.whiteSpaceTokenizer(paragraph, PRINT_MODE.DEFAULT);
		NlpOpenApi.tokenizerME(paragraph, PRINT_MODE.DEFAULT);
		
	}

}

