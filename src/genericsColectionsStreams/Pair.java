package genericsColectionsStreams;

/*
 * generic-класс Pair, похожий на Optional, но содержащий пару элементов 
 * разных типов и не запрещающий элементам принимать значение null.
 */
public class Pair<T, T1> {
	
	private T first;
	private T1 second;
	
	private Pair(T first,T1 second) {
		this.first = first;
		this.second = second;
	}
	
	public static <T, T1>Pair<T, T1> of(T first, T1 second) {
		return new Pair<>(first, second);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}

	public T getFirst() {
		return first;
	}

	public T1 getSecond() {
		return second;
	}

	public static void main(String[] args) {
		Pair<Integer, String> pair = Pair.of(1, "hello");
		Integer i = pair.getFirst(); // 1
		String s = pair.getSecond(); // "hello"
		System.out.println(i + s);

		Pair<Integer, String> pair2 = Pair.of(1, "hello");
		boolean mustBeTrue = pair.equals(pair2); // true!
		boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
		System.out.println(mustBeTrue + " " +  mustAlsoBeTrue);
	}
}
