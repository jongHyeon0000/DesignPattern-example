package DesignPattern.example.abstract_factory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

abstract public class Monitor {
	protected int cost;
	protected int weight;
	protected int inch;

	Monitor(int cost, int weight, int inch) {
		this.cost = cost;
		this.weight = weight;
		this.inch = inch;
	}

	public final static class CreateMonitorRank {
		@FunctionalInterface
		private interface DefaultStarCalculus<T> {
			int getStar(T cost, T weight, T inch);
		}

		private static final DefaultStarCalculus<Integer> defalutStarCalculus = (cost, weight, inch) -> (inch * 100000)
				- cost - (weight * 10000);

		@SafeVarargs // item 3
		public static <V extends Monitor> Map<Integer, V> getRanking(DefaultStarCalculus<Integer> starCalculus,
				Comparator<? super Integer> comparator, V... monitors) {
			Map<Integer, V> map = new TreeMap<Integer, V>(comparator);

			for (V monitorElement : monitors) {
				map.put(starCalculus.getStar(monitorElement.cost, monitorElement.weight, monitorElement.inch), monitorElement);
			}

			return map;
		}

		@SafeVarargs
		public static <V extends Monitor> Map<Integer, V> getRanking(DefaultStarCalculus<Integer> starCalculus,
				V... monitors) {
			return CreateMonitorRank.getRanking(starCalculus, Integer::compare, monitors);
		}

		@SafeVarargs
		public static <V extends Monitor> Map<Integer, V> getRanking(Comparator<? super Integer> comparator,
				V... monitors) {
			return CreateMonitorRank.getRanking(defalutStarCalculus, comparator, monitors);
		}

		// 가변 파라미터만 사용은 위험 -> 수정 예정
		@SafeVarargs // item 4
		public static <V extends Monitor> Map<Integer, V> getRanking(V... monitors) {
			return CreateMonitorRank.getRanking(defalutStarCalculus, Integer::compare, monitors);
		}
	}

	public static void simplePushMethod() {
		Object[] intArray = new Long[20]; // OK
	}
}
