package As7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static boolean checkSquares(String FileName) throws IOException {
        FileReader fr = new FileReader(FileName);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        ArrayList<String[]> list = new ArrayList<>();

        int count = 0;
        int sum = 0;
        boolean row = true;
        boolean col = true;
        boolean diag = true;

        while ((line = br.readLine()) != null) {
            if(line.length() != 0){
                String[] s = line.split("\t");
                list.add(s);
                count = s.length;
            }

        }

        br.close();

        int[][] matrix = new int[count][count];
        for(int i = 0; i < count; i++){
            for(int j = 0; j < count; j++){
                matrix[i][j] = Integer.parseInt(list.get(i)[j]);
                if(i == 0){
                    sum += matrix[i][j];
                }
            }
        }

        //row
        for (int i = 0; i < count; i++) {
            int tmpSum = 0;
            for (int j = 0; j < count; j++) {
                tmpSum += matrix[i][j];
            }
            if(tmpSum != sum){
                row = false;
            }
        }

        //col
        for(int j = 0; j < count; j++){
            int tmpSum = 0;
            for(int i = 0; i < count; i++){
                tmpSum += matrix[i][j];
            }
            if(tmpSum != sum){
                col = false;
            }
        }

        //diag
        int diagSum = 0;
        int rdiagSum = 0;
        for(int i = 0; i < count; i++){
            diagSum += matrix[i][i];
        }
        for(int i = 0; i < count; i++){
            rdiagSum += matrix[i][count-i-1];
        }
        if(diagSum != sum || rdiagSum != sum){
            diag = false;
        }

        return row && col && diag;
    }
    public static void main(String[] args) throws IOException {

        if(checkSquares("src/main/resources/Luna.txt")){
            System.out.println("It's a magic square");
        }else {
            System.out.println("It's not a magic square");
        }

        if(checkSquares("src/main/resources/Mercury.txt")){
            System.out.println("It's a magic square");
        }else {
            System.out.println("It's not a magic square");
        }


    }
}
