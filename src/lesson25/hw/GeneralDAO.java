package lesson25.hw;

import lesson25.hw.exception.BadRequestException;
import lesson25.hw.exception.InternalServerException;

public class GeneralDAO<T extends EntityId> {

    private T[] array = (T[]) new EntityId[10];

    public T save (T t) throws Exception{
        validate(t);

        int index = 0;
        for (T item : array) {
            if (item == null) {
                array[index] = t;
                return array[index];
            }
            index++;
        }
        throw new InternalServerException("no enough space for " + t.getClass().getName() +
                "id: " + t.getId() + " ,not be saved");
    }

    public T[] getAll() throws Exception{
        int count = 0;

        for(T t : array) {
            if (t != null)
                count++;
        }

        if (count == 0)
            throw new InternalServerException("no items to get");

        T[] res = (T[]) new EntityId[count];
        int index = 0;

        for(T t : array) {
            if (t != null)
                res[index] = t;
            index++;
        }
        return res;
    }

    private void validate(T t) throws Exception {

        if (t == null)
            throw new BadRequestException("null can't be save");

        for (T item : array) {
            if (item != null && item.getId() == t.getId())
                throw new BadRequestException("object with same id: " + t.getId() +
                        " was found, not be saved");
        }
    }
}
