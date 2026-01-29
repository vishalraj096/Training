import java.util.Objects;

public class CustomHashMap<K, V> {
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private int capacity;
    private final float loadFactor = 0.75f;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        this.capacity = 16;
        this.buckets = (Node<K, V>[]) new Node[capacity];
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int idx = indexFor(key);
        Node<K, V> cur = buckets[idx];
        while (cur != null) {
            if (Objects.equals(cur.key, key))
                return cur.value;
            cur = cur.next;
        }
        return null;
    }

    public void put(K key, V value) {
        if ((size + 1f) / capacity > loadFactor)
            resize();
        int idx = indexFor(key);
        Node<K, V> cur = buckets[idx];
        while (cur != null) {
            if (Objects.equals(cur.key, key)) {
                cur.value = value;
                return;
            }
            cur = cur.next;
        }
        Node<K, V> n = new Node<>(key, value);
        n.next = buckets[idx];
        buckets[idx] = n;
        size++;
    }

    public V remove(K key) {
        int idx = indexFor(key);
        Node<K, V> cur = buckets[idx], prev = null;
        while (cur != null) {
            if (Objects.equals(cur.key, key)) {
                if (prev == null)
                    buckets[idx] = cur.next;
                else
                    prev.next = cur.next;
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    private int indexFor(K key) {
        int h = (key == null ? 0 : key.hashCode()) & 0x7fffffff;
        return h % capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCap = capacity * 2;
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[newCap];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> cur = buckets[i];
            while (cur != null) {
                Node<K, V> nxt = cur.next;
                int idx = ((cur.key == null ? 0 : cur.key.hashCode()) & 0x7fffffff) % newCap;
                cur.next = newBuckets[idx];
                newBuckets[idx] = cur;
                cur = nxt;
            }
        }
        buckets = newBuckets;
        capacity = newCap;
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        System.out.println(map.get("banana"));
        System.out.println(map.remove("apple"));
        System.out.println(map.get("apple"));
        System.out.println("size=" + map.size());
    }
}
