package algo;

import java.util.Set;
import java.util.TreeSet;

public class AutomatesFinis {
	private String text;
	private String pattern;
	private String alphabet;
	private int array[][];

	public AutomatesFinis(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		buildAlphabet();
		buildRobot();
	}

	public void printArray() {
		System.out.print("\t");
		for (int i = 0; i < alphabet.length(); i++) {
			System.out.print(alphabet.charAt(i) + "\t");
		}
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			System.out.print(i + ":\t");
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private void buildAlphabet() {
		Set<Character> alphabetSet = new TreeSet<>();
		for (int i = 0; i < text.length(); i++) {
			Character letter = text.charAt(i);
			if (pattern.contains(letter.toString())) {
				alphabetSet.add(letter);
			}
		}
		StringBuilder alphabetBuilder = new StringBuilder(alphabetSet.size());
		for (Character c : alphabetSet) { alphabetBuilder.append(c); }
		alphabet = alphabetBuilder.toString();
	}

	private void buildRobot() {
		// Le tableau avec les états en ligne et l'alphabet en colonne
		array = new int[pattern.length() + 1][alphabet.length()];
		// Pour l'état 0, on initialise tout l'alphabet à 0 (tout le monde pointe sur l'état 0)
		for (int i = 0; i < alphabet.length(); i++) {
			array[0][i] = 0;
		}
		for (int i = 1; i <= pattern.length(); i++) {
			int patternIndex = alphabet.indexOf(pattern.charAt(i - 1));
			int tempState = array[i - 1][patternIndex];
			array[i - 1][patternIndex] = i;
			for (int j = 0; j < alphabet.length(); j++) {
				array[i][j] = array[tempState][j];
			}
		}
	}
}
