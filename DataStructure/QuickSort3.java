public class QuickSort {
    public static List<Integer> quickSort(List<Integer> list){
        if(list.size() <= 1) return list;
        int pivot = list.get(list.size()/2);

        List<Integer> left = new LinkedList<>();
        List<Integer> equal = new LinkedList<>();
        List<Integer> right = new LinkedList<>();

        for(int num : list){
            if(num < pivot) left.add(num);
            else if(num > pivot) right.add(num);
            else equal.add(num);
        }

        return Stream.of(quickSort(left), equal, quickSort(right)).flatMap(Collection::stream).collect(Collectors.toList());
    }
}