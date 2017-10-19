package algoprobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// http://practice.geeksforgeeks.org/problems/word-boggle/0
public class WordBoggle {

	static String[] dictionary = {"QUIZ", "FOR","GIZI",  "GO"};
	static char[][] matrix =   {{'G','I','Z'},
            					{'U','E','K'},
            					{'Q','I','D'}};
	
	static Map<Character, List<Point>> charindexesMap = new HashMap<Character, List<Point>>();
	
	public static void main(String[] args) {
		// Traverse through matrix
		// Map characters to a collection of its locations
		int row = 0; int cell = 0;
		for(char[] charRow: matrix) {
			cell = 0;
			for(char ch: charRow) {
				List<Point> locindexes = null;
				locindexes = charindexesMap.get(ch);
				if(locindexes == null) {
					locindexes = new ArrayList<Point>();
					locindexes.add(new Point(row, cell));
					charindexesMap.put(ch, locindexes);
				}else {
					locindexes.add(new Point(row, cell));
					// charindexesMap.put(ch, locindexes); // not necessary
				}
				cell++;
			}
			row++;
		}

		// Test Print
		for(Character c : charindexesMap.keySet()) {
			for(Point p: charindexesMap.get(c)) {
				System.out.print(c+ " ");
				System.out.println(p);
			}
		}

		// For each word in dictionary
		for(String word: dictionary) {
			// TBD Check if all characters and respective count exists

			boolean found = false;
			try {
				found = recursiveCheck(word, 0, charindexesMap.get(word.charAt(0)));
			} catch (Exception e) {
				System.out.println("Error " + word);
			}
			
			if(found) {
				System.out.println(word);
			}
		}
	}
	
	static boolean recursiveCheck(String word, int currentCharIndex, List<Point> currCharLocs) {
		boolean found = false;
		

		if(currentCharIndex == word.length()-1) {
			return true;
		}
		
		// List<Point> currCharLocs = charindexesMap.get(word.charAt(currentCharIndex));
		List<Point> nextCharLocs = charindexesMap.get(word.charAt(currentCharIndex+1));
		
		if(currCharLocs == null || nextCharLocs == null) {
			return false;
		}
		
		for(Point p: currCharLocs) {
			List<Point> validNextLocs = checkIfNextCharacterReachable(p, nextCharLocs);
			if(validNextLocs.size()> 0) {
				found = recursiveCheck(word, currentCharIndex+1, validNextLocs);
			}
		}
		
		return found;
	}
	
	static List<Point> checkIfNextCharacterReachable(Point p, List<Point> nextCharLocs) {
		List<Point> reachable = new ArrayList<>();
		for(Point pnext: nextCharLocs) {
			if(Math.abs(pnext.y - p.y)<=1) {
				if(Math.abs(pnext.x - p.x)<=1) {
					reachable.add(pnext);
				}
			}
		}
		return reachable;
	}
	
	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return("X = "+x+ " Y = "+y);
		}
	}
}
