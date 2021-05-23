package endterm.project;

import java.util.ArrayList;
import java.util.Scanner;

class mergeSort<T extends Comparable<T>> {

    public void sort(T[] a, int l, int r){
        if(l < r){
            int mid = (l + r) / 2;
            sort(a, l, mid);
            sort(a, mid + 1, r);
            merge(a, l, r, mid);
        }
    }

    void merge(T[] a, int l, int r, int mid){
        int szL = mid - l + 1;
        int szR = r - mid;

        ArrayList<T> L = new ArrayList<>();
        ArrayList<T> R = new ArrayList<>();
        for(int i = 0; i < szL; i++){
            L.add(a[l + i]);
        }
        for(int i = 0; i < szR; i++){
            R.add(a[mid + 1 + i]);
        }
        int i = 0;
        int j = 0;
        int cur = l;
        while(i < szL && j < szR){
            if(L.get(i).compareTo(R.get(j)) == -1){
                a[cur] = L.get(i);
                i++;
            } else {
                a[cur] = R.get(j);
                j++;
            }
            cur++;
        }
        while(i < szL){
            a[cur] = L.get(i);
            i++;
            cur++;
        }
        while(j < szR){
            a[cur] = R.get(j);
            j++;
            cur++;
        }
    }
}