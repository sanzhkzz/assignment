package endterm.project;

public class Main {
    public static void main(String[] args) {
        mergeSort<Integer> sort = new mergeSort();
        Integer[] a = {3, 5, 2, 4, 5, 200, 4,56, 345, 1110, -334343, 434, 1111111, -3, 0};

        sort.sort(a, 0  , a.length - 1);
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
    }
}