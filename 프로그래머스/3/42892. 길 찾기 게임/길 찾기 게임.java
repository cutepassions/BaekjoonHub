import java.util.*;

class Solution {
    class Node{
        int x,y,num;
        Node left, right;
        Node(int x, int y, int num){
            this.x=x;
            this.y=y;
            this.num=num;
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        int[][] tmp = new int[nodeinfo.length][3];
        for(int i=0; i<tmp.length; i++){
            tmp[i][0] = nodeinfo[i][0]; // x
            tmp[i][1] = nodeinfo[i][1]; // y
            tmp[i][2] = i+1;            // 번호
        }
        
        // 정렬
        Arrays.sort(tmp, (a, b)->{
            if(b[1]!=a[1]) return b[1]-a[1];
            else return a[0]-b[0];
        });
        
        // 이진 트리
        Node root = new Node(tmp[0][0], tmp[0][1], tmp[0][2]);
        for(int i=1; i<tmp.length; i++){
            addNode(root, new Node(tmp[i][0], tmp[i][1], tmp[i][2])); // 노드 추가
        }
        
        // 전위 순회
        List<Integer> before = new ArrayList<>();
        goBefore(root, before);

        // 후위 순회
        List<Integer> after = new ArrayList<>();
        goAfter(root, after);
        
        int[][] answer = new int[2][nodeinfo.length];
        for(int i=0; i<nodeinfo.length; i++){
            answer[0][i] = before.get(i);
            answer[1][i] = after.get(i);
        }
        
        return answer;
    }
    
    // 전위 순회
    void goBefore(Node node, List<Integer> list){
        if(node == null) return;
        list.add(node.num); // 위
        goBefore(node.left, list); // 왼쪽
        goBefore(node.right, list); // 오른쪽        
    }
    
    // 후위 순회
    void goAfter(Node node, List<Integer> list){
        if(node == null) return;
        goAfter(node.left, list); // 왼쪽
        goAfter(node.right, list); // 오른쪽
        list.add(node.num); // 위
    }
    
    // 노드 추가
    void addNode(Node p, Node c){
        // 왼쪽
        if(c.x < p.x){
            if(p.left == null) p.left = c;
            else addNode(p.left, c);
        }
        // 오른쪽
        else{
            if(p.right == null) p.right = c;
            else addNode(p.right, c);
        }
    }
}