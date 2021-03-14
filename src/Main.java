import java.io.*;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        int m = 7;
        for (ArrayList<Integer> ali: findPair(m,arrayList)) {
            System.out.println(ali.toString());
        }
        System.out.println();
        br.close();
        bw.close();
    }

    public static ArrayList<ArrayList<Integer>> findPair(int m, ArrayList<Integer> arrayList) {
        ArrayList<ArrayList<Integer>> pairList = new ArrayList<>();
        for (int a: arrayList) {
           ArrayList<Integer> pair = new ArrayList<>();
            int b;
            try {
                b = arrayList.get(binSearch(arrayList, (m - a)));
            } catch (IndexOutOfBoundsException e) {
                b = -1;
            }
           if (b != -1) {
               pair.add(a);
               pair.add(b);
               pairList.add(pair);
           }
        }
        return pairList;
    }

    public static int binSearch(ArrayList<Integer> arrayList, int x) {
        int head = 0;
        int tail = arrayList.size() - 1;
        while   ( head <= tail) {
            int mid = (tail + head) / 2;
            if (arrayList.get(mid) == x) return mid;
            else if (x < arrayList.get(mid)) tail = mid - 1;
            else head = mid + 1;
        }
        return -1;
    }
}