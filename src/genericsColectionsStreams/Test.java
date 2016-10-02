package genericsColectionsStreams;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Test {
	/*
	 * метод, вычисляющий симметрическую разность двух множеств.
	 * Пример
	 * Симметрическая разность множеств {1, 2, 3} и {0, 1, 2} равна {0, 3}.
	 */
	public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
		Set<T> set = new HashSet<T>(set1);		
		set.removeAll(set2);
		for (T obj : set2) {
			if (!set1.contains(obj)) set.add(obj);
		}
		return set;
	}
	
	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<>();
		set.add("string1");
		set.add("string2");
		set.add("astring3");
		set.add("string4");
		Iterator<String> iterator = set.iterator();
		
		while (iterator. hasNext ()) {
			String element = iterator.next();
			System . out. println (element);
		}

		Set<Integer> set11 = new HashSet<Integer>();
		Set<Integer> set22 = new HashSet<Integer>();
		set11.add(1);
		set11.add(2);
		set11.add(3);
		set22.add(0);
		set22.add(1);
		set22.add(2);
		System.out.println(symmetricDifference(set11, set22));
	}

}
