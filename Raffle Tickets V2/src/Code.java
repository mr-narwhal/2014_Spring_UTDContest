import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.lang.System.out;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Code {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("in.txt"));

		while (input.hasNextLine()) {
			int reps = parseInt(input.nextLine());
			if (reps == 0)
				break;

			for (int ix = 0; ix < reps; ix++) {
				String line = input.nextLine();
				if (isInteresting(line))
					out.println(line);
			}
			out.println();
		}
		input.close();
	}

	public static boolean isInteresting(String string) {
		if (string.length() < 2)
			return false;
		int intNum = parseInt(string);
		if (string.length() == 2)
			return parseInt(string.substring(0, 1))
					* parseInt(string.substring(1, 2)) == intNum;
		String[] perms = permutations(string);

		for (String number : perms)
			for (int ix = 1; ix < number.length(); ix++)
				if (parseInt(number.substring(0, ix))
						* parseInt(number.substring(ix, number.length())) == intNum)
					return true;
		return false;
	}

	public static String[] permutations(String string) {
		List<String> permutations = new LinkedList<>();
		List<String> words = new LinkedList<>();

		permutations.add(valueOf(string.charAt(0)));

		for (int ix = 1; ix < string.length(); ix++) {
			for (int iy = 0; iy < permutations.size(); iy++)
				words.addAll(merge(string.charAt(ix), permutations.get(iy)));
			permutations.clear();
			permutations.addAll(words);
			words.clear();
		}

		return permutations.toArray(new String[permutations.size()]);
	}

	private static Set<String> merge(Character c, String s) {
		int len = s.length();
		StringBuilder sb = new StringBuilder();
		Set<String> list = new HashSet<String>();

		for (int i = 0; i <= len; i++) {
			sb.delete(0, sb.length());
			sb.append(s.substring(0, i) + c + s.substring(i, len));
			list.add(sb.toString());
		}

		return list;
	}

}