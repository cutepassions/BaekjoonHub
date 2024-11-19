import java.util.*;

class Solution {
    public int solution(String dartResult) {
        List<Integer> list = new ArrayList<>();
        int answer = 0;
        char[] arr = dartResult.toCharArray();
        int tmp = -1;
        boolean flag = false;
        for(char a:arr){
            // 숫자인 경우 초기화
            if(Character.isDigit(a)){
                if(flag) tmp = 10;
                else {
                    tmp = Integer.parseInt(String.valueOf(a));
                }
                flag = true;
            }else {
                flag = false;
                int idx = list.size()-1;
                if (a=='*'){
                    if(idx>0){
                        list.set(idx, list.get(idx)*2);
                        list.set(idx-1, list.get(idx-1)*2);
                    }else{
                        list.set(idx, list.get(idx)*2);
                    }                    
                } else if(a=='#'){
                    list.set(idx, list.get(idx)*-1);                
                } else if(a=='S'){
                    tmp = (int)Math.pow(tmp, 1);
                    System.out.println(tmp);
                    list.add(tmp);
                } else if(a=='D'){
                    tmp = (int)Math.pow(tmp, 2);
                    list.add(tmp);
                } else{
                    tmp = (int)Math.pow(tmp, 3);
                    list.add(tmp);
                }
            }
        }
        System.out.println(list.toString());
        return list.stream().mapToInt(a->a).sum();
    }
}