// Time Complexity: O((n *m)*l)
// Space Complexity: O(n * l) l - length of longest string

class Solution {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    TrieNode root;

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new TrieNode();
            curr = curr.children[c - 'a'];
        }

        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        for (String word : dictionary)
            insert(word);

        String[] strArray = sentence.split(" ");
        StringBuilder newSent = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {
            TrieNode curr = root;
            String word = strArray[i];
            if (i != 0)
                newSent.append(" ");
            StringBuilder replacement = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (curr.children[c - 'a'] == null || curr.isEnd)
                    break;
                curr = curr.children[c - 'a'];
                replacement.append(c);
            }
            if (curr.isEnd)
                newSent.append(replacement);
            else
                newSent.append(word);
        }
        return newSent.toString();

    }
}