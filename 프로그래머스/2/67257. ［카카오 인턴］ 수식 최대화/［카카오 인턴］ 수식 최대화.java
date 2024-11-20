import java.util.*;

class Solution {
    static long answer = 0;
    public long solution(String expression) {
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        Set<Character> opSet = new HashSet<>(); // 사용된 연산자들

        // 1. 숫자와 연산자 분리
        StringBuilder numBuffer = new StringBuilder();
        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                numBuffer.append(ch);
            } else {
                nums.add(Long.parseLong(numBuffer.toString()));
                numBuffer.setLength(0); // 초기화
                ops.add(ch);
                opSet.add(ch);
            }
        }
        nums.add(Long.parseLong(numBuffer.toString())); // 마지막 숫자 추가

        // 2. 연산자 순열 생성
        List<Character> opList = new ArrayList<>(opSet);
        List<List<Character>> perms = new ArrayList<>();
        perm(opList, new ArrayList<>(), new boolean[opList.size()], perms);

        // 3. 각 우선순위에 대해 계산
        for (List<Character> priority : perms) {
            answer = Math.max(answer, Math.abs(calculate(new ArrayList<>(nums), new ArrayList<>(ops), priority)));
        }

        return answer;
    }

    // 순열 생성
    private void perm(List<Character> opList, List<Character> temp, boolean[] visited, List<List<Character>> result) {
        if (temp.size() == opList.size()) {
            result.add(new ArrayList<>(temp)); // 완성된 순열 추가
            return;
        }
        for (int i = 0; i < opList.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            temp.add(opList.get(i));
            perm(opList, temp, visited, result);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }


    // 주어진 우선순위에 따라 계산 수행
    private long calculate(List<Long> nums, List<Character> ops, List<Character> priority) {
        for (char op : priority) {
            for (int i = 0; i < ops.size(); ) {
                if (ops.get(i) == op) {
                    long result = operate(nums.get(i), nums.get(i + 1), op);
                    nums.remove(i + 1); // 다음 인덱스 제거
                    nums.set(i, result); // 현재 인덱스 값 수정
                    ops.remove(i); // 연산자 제거
                } else {
                    i++; // 계산해야 할 연산자가 아니라면 넘어가기
                }
            }
        }
        return nums.get(0); // 최종 계산 결과
    }

    // 두 숫자와 연산자를 받아 계산
    private long operate(long a, long b, char op) {
        if(op=='+'){
            return a+b;
        }else if(op=='-'){
            return a-b;
        }else{
            return a*b;
        }
    }
}
