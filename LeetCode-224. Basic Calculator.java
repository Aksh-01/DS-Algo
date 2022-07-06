/**
 * LeetCode-224. Basic Calculator
 * Hard
 */

/**
 * Submission Detail
 * 42 / 42 test cases passed.
 * Runtime: 182 ms -- beats 6.14 % of java submissions.
 * Memory Usage: 62.8 MB -- beats 5.42 % of java submissions.
 */

/**
 * 1. Converted the expression given to correct format for Postfix conversion.
 * 2. Convert into Postfix expression.
 * 3. Evaluate the Postfix expression.
 */

class Solution {
    public static ArrayList correctString(String s) {
        ArrayList<String> strArray = new ArrayList<>();
        String st = "";
        if (s.charAt(0) == '-') {
            strArray.add("0");
            strArray.add("-");
        }

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            } else if (c == '-') {
                if (st != "") {
                    strArray.add(st);
                    st = "";
                }
                int n = strArray.size();
                String val = strArray.get(n - 1);
                if (val == "-")
                    continue;
                else if (val == "(") {
                    strArray.add("0");
                }
                strArray.add("-");
            } else if (c == '+') {
                if (st != "") {
                    strArray.add(st);
                    st = "";
                }
                strArray.add("+");
            } else if (c == '(') {
                if (st != "") {
                    strArray.add(st);
                    st = "";
                }
                strArray.add("(");
            } else if (c == ')') {
                if (st != "") {
                    strArray.add(st);
                    st = "";
                }
                strArray.add(")");
            } else {
                st += c;
            }
        }
        if (st != "") {
            strArray.add(st);
        }
        return strArray;
    }

    public static ArrayList toPostfix(String s) {

        ArrayList<String> arr = correctString(s);
        
        Stack<String> st = new Stack<>();
        String postfixString = "";
        ArrayList<String> postfixAList = new ArrayList<>();
        
        for (String c : arr) {
            if (c == "(") {
                st.push(c);
            } else if (c == "+" || c == "-") {
                while (!st.isEmpty() && st.peek() != "(") {
                    // postfixString += st.pop();
                    postfixAList.add(st.pop());
                }
                st.push(c);
            } else if (c == ")") {
                while (st.peek() != "(") {
                    // postfixString += st.pop();
                    postfixAList.add(st.pop());
                }
                st.pop();
            } else {
                // postfixString += c;
                postfixAList.add(c);
            }
        }
        while (!st.isEmpty()) {
            // postfixString += st.pop();
            postfixAList.add(st.pop());
        }
        // return postfixString;
        return postfixAList;
    }

    public static int evaluatePostfix(ArrayList<String> sList) {
        Stack<Integer> st = new Stack<>();
        for (String op : sList) {
            if (op == "+") {
                int x2 = st.pop();
                int x1 = st.pop();
                st.push(x1 + x2);
            } else if (op == "-") {
                int x2 = st.pop();
                int x1 = st.pop();
                st.push(x1 - x2);
            } else {
                st.push(Integer.parseInt(op));
            }
        }
        return st.pop();

    }
    public int calculate(String s) {
        return evaluatePostfix(toPostfix(s));
        
    }
}
