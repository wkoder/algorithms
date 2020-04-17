import java.io.PrintWriter;

class SuffixTrie {
  static PrintWriter out;
  static int pos;
  static char[] st = new char[1100];
  int N;
  char[][] dict;
  TrieNode root;

  public SuffixTrie(int maxSize) {
    dict = new char[maxSize][];
  }

  void clear() {
    N = 0;
    root = new TrieNode(0, -1, -1);
  }

  void add(char[] word) {
    TrieNode cur, part, parent;
    int p, id = N++, l, len1 = word.length, i, j;
    dict[id] = word;
    for (j = 0; j < len1; j++) {
      cur = root;
      for (i = j; i < len1; i++) {
        p = word[i] - 'a';
        if (cur.sons[p] == null) {
          cur.sons[p] = new TrieNode(i, len1 - 1, id);
          i = len1 - 1;
        } else {
          parent = cur;
          cur = cur.sons[p];
          for (l = 1; cur.a + l <= cur.b && i + l < len1; l++)
            if (dict[cur.own][cur.a + l] != word[i + l])
              break;
          if (cur.a + l <= cur.b) {
            part = cur;
            cur = new TrieNode(cur.a, cur.a + l - 1, cur.own);
            cur.count = part.count;
            part.a += l;
            parent.sons[dict[cur.own][cur.a] - 'a'] = cur;
            cur.sons[dict[part.own][part.a] - 'a'] = part;
          }
          i += l - 1;
        }
        if (cur.last != id) {
          cur.count++;
          cur.last = id;
        }
      }
    }
  }

  void printIfLenght(int lenght, int minCount, TrieNode node) {
    if (node.count < minCount || lenght < pos)
      return;
    int i;
    for (i = node.a; i <= node.b; i++, pos++)
      st[pos] = dict[node.own][i];
    if (pos == lenght) {
      for (i = 0; i < pos; i++)
        out.print(st[i]);
      out.println();
    } else
      for (i = 0; i < 26; i++)
        if (node.sons[i] != null)
          printIfLenght(lenght, minCount, node.sons[i]);
    pos -= node.b - node.a + 1;
  }

  void print(TrieNode node, int l) {
    for (int i = 0; i < l; i++)
      System.out.print(' ');
    for (int i = node.a; i <= node.b; i++)
      System.out.print(dict[node.own][i]);
    System.out.println(" = " + node.count);
    for (int i = 0; i < 26; i++)
      if (node.sons[i] != null)
        print(node.sons[i], l + 1);
  }
}

class TrieNode {
  int a, b, own;
  int last, count;
  TrieNode[] sons;

  public TrieNode(int from, int to, int owner) {
    a = from;
    b = to;
    last = own = owner;
    count = 1;
    sons = new TrieNode[26];
  }

  int getMaxLenght(int minCount) {
    if (count < minCount)
      return 0;
    int max = 0;
    for (int i = 0; i < 26; i++)
      if (sons[i] != null)
        max = Math.max(max, sons[i].getMaxLenght(minCount));
//		System.out.println(a + " -> " + b + " (" + own + ") = " + (max+b-a+1));
    return max + b - a + 1;
  }
}
