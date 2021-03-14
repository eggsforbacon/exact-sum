import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.close();
        bw.close();
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