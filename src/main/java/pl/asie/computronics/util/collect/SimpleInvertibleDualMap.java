package pl.asie.computronics.util.collect;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class SimpleInvertibleDualMap<K, V> {

	private final Multimap<K, V> map = HashMultimap.create();
	private final Map<V, K> inverse = Maps.newHashMap();
	private Map<V, K> immutableInverse;

	private SimpleInvertibleDualMap() {

	}

	public static <K, V> SimpleInvertibleDualMap<K, V> create() {
		return new SimpleInvertibleDualMap<K, V>();
	}

	public Map<V, K> inverse() {
		return immutableInverse == null ? immutableInverse = Collections.unmodifiableMap(inverse) : immutableInverse;
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	public boolean containsValue(V value) {
		return map.containsValue(value);
	}

	public boolean containsEntry(K key, V value) {
		return map.containsEntry(key, value);
	}

	public boolean put(K key, V value) {
		inverse.put(value, key);
		return map.put(key, value);
	}

	public Collection<V> removeAll(K key) {
		Collection<V> vs = map.removeAll(key);
		for(V v : vs) {
			inverse.remove(v);
		}
		return vs;
	}

	public void clear() {
		map.clear();
		inverse.clear();
	}

	public Collection<V> get(K key) {
		return Collections.unmodifiableCollection(map.get(key));
	}

	public Set<K> keySet() {
		return Collections.unmodifiableSet(map.keySet());
	}

	public Multiset<K> keys() {
		return Multisets.unmodifiableMultiset(map.keys());
	}

	public Collection<V> values() {
		return Collections.unmodifiableCollection(map.values());
	}

	public Collection<Map.Entry<K, V>> entries() {
		return Collections.unmodifiableCollection(map.entries());
	}
}