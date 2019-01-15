package gromcode.main.lesson9.utils;

public class Checker {
    public int companyNameValidatedCount = 0;

    public boolean checkCompanyName(String companyName){
        if (companyNameValidatedCount > 10)
            return false;
        companyNameValidatedCount++;
        return (companyName!= "Google" && companyName != "Amazon");
    }
}
