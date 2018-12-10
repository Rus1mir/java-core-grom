package lesson31;

public class Demo {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.countSymbols("I shell remember you every time"));
        System.out.println(solution.countSymbols("DDDDDFFFFFdddss123"));
        System.out.println(solution.countSymbols("@wer11 errfff567 : 12"));
        System.out.println(solution.countSymbols(""));
        System.out.println(solution.countSymbols("@453._="));

        System.out.println(solution.words("I shell remember you every time"));
        System.out.println(solution.words("don't trouble trouble until trouble troubles you"));
        System.out.println(solution.words("@wer11 errfff567 : 12 index of index of index of index1"));
        System.out.println(solution.words(""));
        System.out.println(solution.words("@453._="));
    }
}
