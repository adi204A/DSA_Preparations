class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        String prevSig = "";
        
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String currentSig = new String(chars);
            
            if (!currentSig.equals(prevSig)) {
                result.add(word);
                prevSig = currentSig;
            }
        }
        
        return result;
    }
}