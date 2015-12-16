package bps.go.id.mrhandsdroid.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handi_000 on 7/1/2015.
 */
public class FilterList<E> {
    public  <T> List filterList(List<T> originalList, Filter filter, E text) {
        List<T> filterList = new ArrayList<T>();
        for (T object : originalList) {
            if (filter.isMatched(object, text)) {
                filterList.add(object);
            } else {
                continue;
            }
        }
        return filterList;
    }
}