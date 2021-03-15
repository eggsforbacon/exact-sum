import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<ArrayList<ArrayList<Integer>>> inputALs = readInput(br);
        inputALs = sort(inputALs);
        System.out.println(inputALs.toString());
        br.close();
        bw.close();
    }

    private static ArrayList<ArrayList<ArrayList<Integer>>> sort(ArrayList<ArrayList<ArrayList<Integer>>> inputALs) {
        for (ArrayList<ArrayList<Integer>> cases: inputALs) {
            for (ArrayList<Integer> n_Arr_m: cases) {
                Collections.sort(n_Arr_m);
            }
        }
        return inputALs;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> readInput(BufferedReader br) throws IOException {
        StringBuilder input = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            input.append(line).append("\n");
            line = br.readLine();
        }
        ArrayList<ArrayList<ArrayList<Integer>>> nWeeks = new ArrayList<>();
        String[] control = input.toString().split("\\n");
        System.out.println(Arrays.toString(control));
        int i = 0;
        String prev = "\n";
        int week = 0;
        do {
            line = control[i];
            if (i == 0 || prev.equals("\n")) { //N setup
                nWeeks.add(new ArrayList<>());
                nWeeks.get(week).add(new ArrayList<>());
                nWeeks.get(week).get(0).add(Integer.parseInt(line));
            } else if (prev.length() == 1 && !prev.contains(" ") && !line.isEmpty()) { //Prices setup
                nWeeks.get(week).add(new ArrayList<>());
                String[] unParsedBooks = line.split("\\s");
                for (String book: unParsedBooks) nWeeks.get(week).get(1).add(Integer.parseInt(book));
            } else if (prev.contains(" ")) { //M setup
                nWeeks.add(new ArrayList<>());
                nWeeks.get(week).add(new ArrayList<>());
                nWeeks.get(week).get(2).add(Integer.parseInt(line));
            } else { //Empty line
                week++;
                line = "\n";
            }
            prev = line;
            i++;
        } while (i < control.length);
        return nWeeks;
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