package com.leetcode.twitter;

import java.util.*;

/*
   // [["a", ["b", "c"]], ["d"], "abc"]
   serializedString: ((a,(b,c))d,abc)
   2. (a, b, c)
*/
public class NestedListSerializeDeserialize {

    public static void main(String[] args) {
        new NestedListSerializeDeserialize().run();
    }

    private void run() {
        System.out.println("I'm compilable and runnable");
        List list3 = new ArrayList<NestedList>();
        list3.add(new NestedList("ABC"));
        list3.add(new NestedList("DEF"));
        List list4 = new ArrayList<NestedList>();
        list4.add(new NestedList("111"));
        list4.add(new NestedList("222"));
        List list2 = new ArrayList<NestedList>();
        list2.add(new NestedList("AA"));
        list2.add(new NestedList("BB"));
        list2.add(new NestedList(list3));
        list2.add(new NestedList(list4));
        list2.add(new NestedList("CC"));
        List list1 = new ArrayList<NestedList>();
        list1.add(new NestedList("A"));
        list1.add(new NestedList("B"));
        list1.add(new NestedList(list2));
        list1.add(new NestedList("C"));
        NestedList obj = new NestedList(list1);
        String str = serialize(obj);
        System.out.println(str);
        System.out.println(deserialize(str));
    }

    private String serialize(NestedList nestedList) {
        StringBuffer sb = new StringBuffer();
        serialize(nestedList, sb);
        return sb.toString();
    }

    private void serialize(NestedList nestedList, StringBuffer sb){
        if(nestedList == null){
            return;
        }
        if(nestedList.isString()){
            sb.append(nestedList.getValueAsString());
            return;
        } else if(nestedList.isList()){
            sb.append("(");
            for(NestedList child : nestedList.getValueAsList()){
                serialize(child, sb);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            /*if(sb.charAt(sb.length()-1) == ','){
                sb.deleteCharAt(sb.length()-1);
            }*/
            sb.append(")");
        }
    }

    private NestedList deserialize(String str) {

        Stack<NestedList> stk = new Stack<>();
        NestedList res = new NestedList();
        for(int i = 0; i < str.length(); i++){
            NestedList nl = new NestedList();
            if(str.charAt(i) == '('){
                stk.push(nl);
            } else if(str.charAt(i) == ')'){
                NestedList curr = stk.pop();
                stk.peek().getValueAsList().add(curr);
            }
            else{
                int j = i + 1;
                while(j < str.length() && str.charAt(j) != ')'){
                    j++;
                }
                for(String s : str.substring(i, j).split(",")){
                    nl.getValueAsList().add(new NestedList(s));
                }
                i = j-1;
                stk.push(nl);
            }
        }
        return stk.peek();
        // Push _
        // Pop
        // Initial ->
    }
}

/*
 * Implement serialization and deserialization for the class below.
 * Note: string elements will contain only Latin letters
 */
class NestedList {
    private final Object value;

    public NestedList() {
        this.value = new ArrayList<NestedList>();
    }

    public NestedList(List<NestedList> value) {
        this.value = value;
    }

    public NestedList(String value) {
        this.value = value;
    }

    public boolean isList() {
        return value instanceof List;
    }

    public List<NestedList> getValueAsList() {
        return (List<NestedList>) value;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public String getValueAsString() {
        return (String) value;
    }
}
// [["a", ["b", "c"]], ["d"], "abc"]

