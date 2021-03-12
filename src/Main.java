import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;
class Main {

    private static ArrayList<Integer> prices = new ArrayList<>();
    private static int[] foundThem = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        System.out.println("------------------------------------------------------");
        System.out.println("-Enter input:                                        -");
        fill(br);
        System.out.println("------------------------------------------------------");
        System.out.println(prices.toString());
        System.out.println("-Peter should buy the books with prices " + foundThem[0] + " and " + foundThem[1]+"\n");


        bw.close();
    }

    public static void fill(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] unParsedBooks = br.readLine().split("\\s");
        for (int i = 0; i < n; i++) {
            prices.add(Integer.parseInt(unParsedBooks[i]));
        }
        int m = Integer.parseInt(br.readLine());
        binSearch(m);
        sort(prices);
        br.close();
    }

    public static void binSearch(int m) {
        ArrayList<Integer> differences = new ArrayList<>();
        for (int i : prices) {
            differences.add(m - i);
        }
        Collections.reverse(differences);
    }

    public static void sort(ArrayList<Integer> arraylist) {
        for (int i = 1; i < arraylist.size(); ++i)
        {
            int element = arraylist.get(i);
            int j = i - 1;
            while (j >= 0 && arraylist.get(j) > element)
            {
                arraylist.set(j + 1, arraylist.get(j));
                j = j - 1;
            }
            arraylist.set(j + 1, element);
        }
    }


}
