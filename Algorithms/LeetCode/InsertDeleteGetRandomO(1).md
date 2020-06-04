## [Problem](https://leetcode.com/problems/insert-delete-getrandom-o1/)
Design a data structure that supports all following operations in average **O(1)** time.

1. `insert(val)`: Inserts an item val to the set if not already present.
2. `remove(val)`: Removes an item val from the set if present.
3. `getRandom`: Returns a random element from current set of elements. Each element must have the **same probability** of being returned.

## Example
```
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
```

## Solution
```
public class RandomizedSet {

    private Dictionary<int, int> dict;
    private List<int> list;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        dict = new Dictionary<int, int>();
        list = new List<int>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public bool Insert(int val) {
        if(dict.ContainsKey(val)) return false;
        list.Add(val); //O(1) as amortised
        dict.Add(val, list.Count -1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public bool Remove(int val) {
        if(!dict.ContainsKey(val)) return false;
        //List.Remove(obj) will shift elements after removing the element which gives O(N). This can be O(1) when the last one is removed at every attempts.
        int temp = dict[val];
        list[temp] = list[temp.Count-1];
        dict[list[temp]] = temp;
        list.RemoveAt(list.Count -1);
        dict.Remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int GetRandom() {
        Random random = new Random();
        int num = random.Next(0, list.Count);
        return list[num];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * bool param_1 = obj.Insert(val);
 * bool param_2 = obj.Remove(val);
 * int param_3 = obj.GetRandom();
 */
 ```

## Note
Needs to avoid O(N)!
The goal here is to keep O(1)