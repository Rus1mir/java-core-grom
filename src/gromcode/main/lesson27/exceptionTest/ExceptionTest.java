package gromcode.main.lesson27.exceptionTest;

import gromcode.main.lesson22.lesson22hw.exception.BadRequestException;

public class ExceptionTest {
    public static void main(String[] args) {

    }

    void tryCatch () throws BadRequestException {
        try {
            throwEx(false);
            throw new BadRequestException("");
        }catch (Exception e){

        }
    }

    void throwEx (Boolean a) throws Exception {
         if (a) throw new Exception("dascadcad");
    }
}
