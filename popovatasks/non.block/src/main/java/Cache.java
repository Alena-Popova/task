/**
 * @author HelenaPopova
 * 1. Необходимо сделать кеш для хранение моделей. в кеше должны быть методы
 * add(Base model), update(Base model) delete(Base model),
 *
 * 2. Для хранения данных в кеше нужно использовать ConcurrentHashMap<Integer, Base>.
 *
 * В качестве ключа используйте int id. в качестве значения Base модель
 *
 * 3. В кеше должна быть возможность проверять актуальность данных. Для этого в модели данных должно быть после int version.
 * Это после должно увеличиваться на единицу каждый раз, когда произвели изменения данных в модели.
 * Например. Два пользователя прочитали данные task_1
 * первый пользователь изменил поле имя и второй сделал тоже самое. нужно перед обновлением данных проверить. что текущий пользователь не затер данные другого пользователя. если данные затерты то выбросить OptimisticException - такая реализация достигается за счет введение в модель поля version. Перед обновлением данных необходимо проверять текущую версию и ту что обновляем и увеличивать на единицу каждый раз, когда произошло обновление. Если версии не равны -  кидать исключение.
 *
 */
public interface Cache {
    public void add(Base model);
    public void update(Base model);
    public boolean delete(Base model);
    public Base get(int _id);
}

