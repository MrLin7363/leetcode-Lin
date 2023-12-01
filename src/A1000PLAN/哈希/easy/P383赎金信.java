package A1000PLAN.哈希.easy;

/**
 *desc:
 *@author lin
 *@since 2023/11/15
 **/
public class P383赎金信 {
    /*
`   26个int数组的哈希
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ransom = new int[26];
        char[] ransomChars = ransomNote.toCharArray();
        for (int i = 0; i < ransomChars.length; i++) {
            ransom[ransomChars[i] - 'a']++;
        }
        char[] magezineChars = magazine.toCharArray();
        for (int i = 0; i < magezineChars.length; i++) {
            ransom[magezineChars[i] - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (ransom[i] > 0) {
                return false;
            }
        }
        return true;
    }
}
