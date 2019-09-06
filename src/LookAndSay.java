/**
 * File Name: LookAndSay.java
 * LookAndSay concrete class
 *
 *
 * To Compile: IntUtil.java RandomInt.java LookAndSay.java LookAndSayBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class LookAndSay extends LookAndSayBase{
    LookAndSay() {
        //NOTHING CAN BE CHANGED HERE
        testBench();
    }

    @Override
    protected String lookAndSay(String s) {
        //NOTHING CAN BE CHANGED HERE
        return alg(s) ;
    }

    @Override
    protected String lookAndSay(int n) {
        //NOTHING CAN BE CHANGED HERE
        return alg(n) ;
    }

    private String alg(String s) {
        //WRITE CODE
        //You can have any number of private functions and variables
        String n = s;
        String res = "";
        char say = n.charAt(0);
        int cnt = 1;
        for (int i = 1; i < n.length(); i++) {
            if (n.charAt(i) == say) {
                cnt++;
            } else {
                res += String.valueOf(cnt);
                res += say;
                say = n.charAt(i);
                cnt = 1;
            }
        }
        res += String.valueOf(cnt);
        res += say;
        return res;
    }

    private String alg(int n) {
        //WRITE CODE
        //You can have any number of private functions and variables
        String s = "1";
        String ns;
        for (int i = 0; i < n - 1; i++) {
            ns = "";
            char say = s.charAt(0);
            int cnt = 0;
            for (char ss : s.toCharArray()) {
                if (ss == say) {
                    cnt += 1;
                } else {
                    ns += String.valueOf(cnt);
                    ns += say;
                    cnt = 1;
                    say = ss;
                }
            }
            ns += String.valueOf(cnt);
            ns += say;
            s = ns;
        }
        return s;
    }

    public static void main(String[] args) {
        //NOTHING CAN BE CHANGED HERE
        System.out.println("LookAndSay problem STARTS");
        LookAndSay m = new LookAndSay() ;
        System.out.println("All LookAndSay tests passed. You are genius");
        System.out.println("LookAndSay problem ENDS");
    }
}