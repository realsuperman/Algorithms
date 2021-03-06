package BaekJoon_11286;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int index=0;
    public static void main(String[] args) throws Exception{
        int[] array = new int[1000001];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String str;

        for(int i=1;i<=n;i++){
            str = br.readLine();
            if(str.equals("0")){ // 삭제한다
                sb.append(deleteHeap(array)+"\n");
            }else{ // 삽입한다
                addHeap(array,Integer.parseInt(str));
            }
        }
        System.out.print(sb);
    }

    public static void addHeap(int array[],int num){
        array[++index] = num;
        int root;
        int child = index;

        while(child>1){
            root = child/2;
            if( (Math.abs(array[root]) == Math.abs(array[child]) && array[root]>array[child]) || Math.abs(array[root]) > Math.abs(array[child]) ){
                int sw = array[root];
                array[root] = array[child];
                array[child] = sw;
            }else{
                break;
            }
            child = root;
        }
    }

    public static int deleteHeap(int array[]){
        if(index < 1) return 0;
        if(index == 1 ) return array[index--];
        int max = array[1];
        array[1] = array[index];
        index--;
        int start = 1;

        while(start*2<=index){
            int left = array[start*2];
            int right = array[start*2+1];
            int sw = array[start];
            if( ((Math.abs(sw) == Math.abs(left)&&sw<=left)||Math.abs(sw)<Math.abs(left)) &&  ((Math.abs(sw) == Math.abs(right)&&sw<=right)||Math.abs(sw)<Math.abs(right))) break;

            if(Math.abs(left) == Math.abs(right) && left >= right){
                array[start] = right;
                array[start*2+1] = sw;
                start = start*2+1;
            }else if(Math.abs(left) == Math.abs(right) && left <= right){
                array[start] = left;
                array[start*2] = sw;
                start = start*2;
            } else if ( Math.abs(left) < Math.abs(right)  ) {
                array[start] = left;
                array[start*2] = sw;
                start = start*2;
            }else if( Math.abs(left) > Math.abs(right) ){
                array[start] = right;
                array[start*2+1] = sw;
                start = start*2+1;
            }
        }
        return max;
    }
}