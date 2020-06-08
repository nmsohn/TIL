public class MergeSort{
    private static List<Integer> targetList = new ArrayList<>(Arrays.asList(100, 40, 60, 75, 77, 34, 51, 24, 24, 37, 88));

    public static void main(String[] args){
        MergeSort a = new MergeSort();
        List<Integer> result = a.sort(targetList);
        MergeSort.print(result);
        System.out.println(result.size() == targetList.size());
    }

    public static void print(List<Integer> result) {
        result.stream().forEach(item -> System.out.print(String.format("%d ", item)));
        System.out.println();
    }

    public List<Integer> sort(List<Integer) targetList){
        if(targetList.size() > 1){
            return merge(
                sort(targetList.subList(0, targetList.size()/2)), sort(targetList.subList(targetList.size()/2, targetList.subList.size()))
            );
        }else{
            return targetList;
        }
    }

    public List<Integer> merge(List<Integer> left, List<Integer> right){
        List<Integer> result = new ArrayList<>();
        int rightIdx = 0;

        for(Integer l : left){
            while(right.size() > rightIdx && l > right.get(rightIdx)){
                result.add(right.get(rightIdx));
                rightIdx++;
            }
            result.add(l);
        }

        for(int i = rightIdx; i < right.size(); i++){
            result.add(right.get(i));
        }

        return result;
    }
}