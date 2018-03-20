package by.kozik.quest.service.impl.dozer;

import org.dozer.DozerConverter;

import java.util.List;

/**
 * Created by Serge_Kozik on 3/3/2017.
 */
public class ListToSizeConverter extends DozerConverter<List,Integer> {

    public ListToSizeConverter() {
        super(List.class, Integer.class);
    }

    @Override
    public Integer convertTo(List list, Integer integer) {
        if (list==null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public List convertFrom(Integer integer, List list) {
        return null;
    }
}
