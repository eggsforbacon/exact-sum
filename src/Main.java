import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<ArrayList<ArrayList<Integer>>> inputALs = readInput(br);
        sort(inputALs);
        System.out.println(exc(inputALs) + "\n");
        br.close();
        bw.close();
    }

    private static String exc(ArrayList<ArrayList<ArrayList<Integer>>> inputALs) {
        StringBuilder msg = new StringBuilder();
        try {
            for (ArrayList<ArrayList<Integer>> cases : inputALs) {
                int m = cases.get(2).get(0);
                int n = cases.get(0).get(0);
                ArrayList<Integer> arrayList = cases.get(1);
                ArrayList<Integer> correctPair = lesser(findPair(m, arrayList, n));
                msg.append("Peter should buy books whose prices are ").append(correctPair.get(0)).append(" and ").append(correctPair.get(1)).append(".\n\n");
            }
        } catch (IndexOutOfBoundsException e) {
            e.fillInStackTrace();
        }
        return msg.toString();
    }

    private static ArrayList<Integer> lesser(ArrayList<ArrayList<Integer>> pairs) {
        ArrayList<Integer> differences = new ArrayList<>();
        ArrayList<Integer> lesserPair = new ArrayList<>();
        lesserPair.add(0);
        lesserPair.add(0);
        for (ArrayList<Integer> pair: pairs) {
            int difference = pair.get(1) - pair.get(0);
            if (difference >= 0) differences.add(difference);
            Collections.sort(differences);
            if (differences.get(0) == difference) {
                lesserPair.set(0,pair.get(0));
                lesserPair.set(1,pair.get(1));
            }
        }
        return lesserPair;
    }

    private static void sort(ArrayList<ArrayList<ArrayList<Integer>>> inputALs) {
        for (ArrayList<ArrayList<Integer>> cases: inputALs) {
            for (ArrayList<Integer> n_Arr_m: cases) {
                Collections.sort(n_Arr_m);
            }
        }
    }

    private static ArrayList<ArrayList<ArrayList<Integer>>> readInput(BufferedReader br) throws IOException {
        StringBuilder input = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            input.append(line).append("\n");
            line = br.readLine();
        }
        ArrayList<ArrayList<ArrayList<Integer>>> nWeeks = new ArrayList<>();
        String[] control = input.toString().split("\\n");
        int i = 0;
        String prev = "\n";
        int week = 0;
        do {
            line = control[i];
            if (i == 0 || prev.equals("\n")) { //N setup
                nWeeks.add(new ArrayList<>());
                nWeeks.get(week).add(new ArrayList<>());
                nWeeks.get(week).get(0).add(Integer.parseInt(line));
            } else if (!prev.contains(" ") && !line.isEmpty()) { //Prices setup
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

    private static ArrayList<ArrayList<Integer>> findPair(int m, ArrayList<Integer> arrayList, int n) {
        ArrayList<ArrayList<Integer>> pairList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = arrayList.get(i);
            ArrayList<Integer> pair = new ArrayList<>();
            int b;
            boolean existsTwice;
            try {
                b = arrayList.get(binSearch(arrayList, (m - a)));
                existsTwice = i != binSearch(arrayList, (m - a));
            } catch (IndexOutOfBoundsException e) {
                b = -1;
                existsTwice = false;
            }
            if (b != -1 && ((b == a && existsTwice) ^ b != a)) {
                pair.add(a);
                pair.add(b);
                pairList.add(pair);
            }
        }
        return pairList;
    }

    private static int binSearch(ArrayList<Integer> arrayList, int x) {
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