import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;











public class Hauffman {
    private class Node {
        private int d;
        private char c;
        private int w;
        private Node left;
        private Node right;

        private Node(int v, Node l, Node r) {
            d = ++uniqueno;
            c = '\0';
            w = v;
            left = l;
            right = r;
        }

        private Node(char ch, int v) {
            d = ++uniqueno;
            c = ch;
            w = v;
            left = null;
            right = null;
        }

        public boolean isLeaf() {
            return (c == '\0') ? false : true;
        }
    }

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node x, Node y) {
            return (x.w - y.w);                     ////////////////////
        }
    }

    private int uniqueno;
    private String s;
    private String d;
    private boolean display;
    private String dotfilename;
    private HashMap<Character, Integer> hm;
    private PriorityQueue<Node> minheap;
    private HashMap<Character, StringBuffer> hc;
    private Node root;
    private static final IntUtil u = new IntUtil();
    public Hauffman(String s, boolean show, String dotfilename) {
        this.s = s;
        this.display = show;
        root = null;
        uniqueno = 0;
        this.dotfilename = dotfilename;
    }

    private void printOccurrenceTable() {
        if (display) {
            System.out.println("=========================== " + s + " ============================");
            Set<Character> keys = hm.keySet();
            for (Character c : keys) {
                System.out.println(c + " occurs  " + hm.get(c) + " times");
            }
            System.out.println("================================================================");
        }
    }

    private void printCodeTable() {
        if (display) {
            System.out.println("============ Code for each character in " + s + " ===============");
            Set<Character> keys = hc.keySet();
            for (Character c : keys) {
                System.out.println(c + " Has Code  " + hc.get(c));
            }
            System.out.println("================================================================");
        }
    }

    private void buildCharOccurrenceTable() {
        hm = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            boolean x = hm.containsKey(ch);
            if (x) {
                int v = hm.get(ch);
                v++;
                hm.put(ch, v);                  //////////////
            } else {
                hm.put(ch, 1);                  //////////////
            }
        }
        printOccurrenceTable();
    }

    private void buildMinHeapAndLeafNode() {
        Comparator<Node> comparator = new NodeComparator();
        minheap = new PriorityQueue<>(10, comparator);
        Set<Character> keys = hm.keySet();
        if (display) {
            System.out.println("=================== Tree built in this order ====================");
        }
        int k = 0;
        for (Character c : keys) {
            int v = hm.get(c);
            Node n = new Node(c, v);
            minheap.add(n);
            if (display) {
                System.out.println("Leaf    node " + (++k) + " Character is " + c + "Weight is");   /////////////
            }
        }
    }

    private void buildTree() {
        int size = minheap.size();
        int k = size;
        boolean first = true;
        while (first == true || size > 1) {
            first = false;
            Node l = minheap.peek();
            minheap.remove();
            int v = l.w;
            size = minheap.size();
            Node r = null;
            if (size > 0) {
                r = minheap.peek();
                minheap.remove();
                v = v + r.w;
            }
            Node n = new Node(v, l, r);
            ++k;
            minheap.add(n);
            root = n;
            if (display) {
                char ch = ' ';          ////////char ch = l.r; /////////
                if (ch == '\0') {
                    ch = ' ';
                }
                System.out.print("Internal node " + k + " left " + ch + "(" + l.w + ") right");    ////////////
                if (r != null) {
                    char chr = r.c;
                    if (chr == '\0') {
                        chr = ' ';
                    }
                    System.out.print(chr + "(" + r.w + ")");
                } else {
                    System.out.print("null");
                }
                System.out.println(" Weight = " + v);
            }
        }
        if (display) {
            System.out.println("==== Tree has " + k + "nodes ===============");
        }
    }

    //
    //
    //
    //
    private void postorderLRV_r(Node n, char[] acode, int l) {
        if (n != null) {
            if (n.left != null) {
                acode[l] = '0';
                postorderLRV_r(n.left, acode, l + 1);
            }
            if (n.right != null) {
                acode[l] = '1';
                postorderLRV_r(n.right, acode, l + 1);
            }
            if (n.isLeaf()) {
                //
                StringBuffer s = new StringBuffer();
                for (int i = 0; i < 1; ++i) {
                    s.append(acode[i]);
                }
                hc.put(n.c, s);
            }
        }
    }

    private void generateCode() {
        int size = uniqueno;
        //
        char[] acode = new char[size];
        int level = 0;
        postorderLRV_r(root, acode, level);
    }

    private void buildCodeForEachCharacter() {
        hc = new HashMap<>();
        generateCode();
        u.myassert(hc.size() == hm.size());
        printCodeTable();
    }

    public void buildDecodeString() {
        d = new String();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            StringBuffer c = hc.get(ch);
            u.myassert(c.length() >= 1);
            d = d + c;
        }
        if (display) {
            System.out.println("======= Original String ========");
            System.out.println(s);
            System.out.println("======= Decoded String =========");
            System.out.println(d);
        }
    }

    public String encode() {
        buildCharOccurrenceTable();
        buildMinHeapAndLeafNode();
        buildTree();
        writeDot(dotfilename, "");
        buildCodeForEachCharacter();
        buildDecodeString();
        return d;
    }

    public char encodeACharacter(int[] start, int n) {
        Node t = root;
        int i = start[0];
        do {
            if (i == n) {
                //
                u.myassert(t.isLeaf() == true);
                start[0] = i;
                return t.c;
            }
            char c = d.charAt(i++);
            u.myassert(c == '0' || c == '1');
            if (t.isLeaf()) {
                start[0] = i - 1;
                return t.c;
            }
            if (c == '0') {
                t = t.left;
            } else {
                t = t.right;
            }
        } while (true);
    }

    public String decode() {
        String f = new String();
        int s = d.length();
        int[] i = new int[1];
        i[0] = 0;
        do {
            char e = encodeACharacter(i, s);
            f = f + e;
        } while (i[0] < s);
        if (display) {
            System.out.println("======== Recovered String ==========");
            System.out.println(f);
        }
        return f;
    }

    //
    //
    //
    private void writeDot(String fname, String s) {
//        try {
//            FileWriter o = new FileWriter(fname);
//            GraphTest.GraphType t = g.getType();
//            o.write("digraph g {\n");
//            if (t == GraphTest.GraphType.UNDIRECTED || t == GraphTest.GraphType.WEIGHTED_UNDIRECTED) {
//                o.write("edge [dir=none, color=red]\n");
//            } else {
//                o.write("edge [color=red]\n");
//            }
//
//            int n = g.getnumV();
//            g.u.myassert(n == g.getnumV());
//            for (int i =0; i < g.getnumV(); ++i) {
//                String p1 = g.getNodeRealName(i) ;
//                int nf = g.numFanout(i);
//                for(int j = 0; j < nf; ++j) {
//                    int k = g.getNodeFanout(i, j);
//                    String p2= g.getNodeRealName(k);
//                    double w = g.getNodeFanoutEdgeWeight(i, j);
//                    if ( (t == GraphTest.GraphType.WEIGHTED_UNDIRECTED)
//                            || ( t == GraphTest.GraphType.WEIGHTED_DIRECTED) ) {
//                        if ( ( t == GraphTest.GraphType.WEIGHTED_DIRECTED) || (i < k)) {
//                            o.write("    " + p1 + " -> " + p2 + " [label = " + w + "] \n");
//                        }
//                    } else {
//                        if ( ( t == GraphTest.GraphType.DIRECTED) || (i < k)) {
//                            o.write("    " + p1 + " -> " + p2 + "\n");
//                        }
//                    }
//                } //end for
//            } //end for
//            o.write("}\n");
//            o.close();
//            // debug
//            System.out.println("You can see dot file at " + fname);
//            System.out.println("Run the following command to get pdf file");
//            System.out.println("dot - T pdf " + fname + " -o " + fname + ".pdf");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }//end try catch
    } //end writeDot






















    public static void main(String[] args) {
        System.out.println("hauffman java");
        System.out.println("done Hauffman");
    }
}
