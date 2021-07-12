public class rtest {
    public static void main(String[] args) {
        String s = "Testing";
        String e = new String("Testing");
        System.out.println(s == e);
        System.out.println(s.equals(e));


        String word = "Palindromo";
        char[] chars = word.toCharArray();

        for(int i = 0; i < chars.length ; i++) {
            int count = 0;
            for (int j = 0; j < chars.length; j++) {
                if (chars[i] ==chars[j]){
                    count++;
                }
            }
            System.out.println(chars[i] + "-" + count);
        }

    }
}
