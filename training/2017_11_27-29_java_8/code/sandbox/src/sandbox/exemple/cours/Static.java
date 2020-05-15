package sandbox.exemple.cours;

import java.util.function.Function;

public class Static {
	public static void main(String[] args) {
		Integer[] values = { -8, 12, 3, 0, -3, 9, -17 };
		Function<Integer, Integer> function = Math::abs;

		for (int v : values) {
			System.out.println(function.apply(v));
		}
	}
}