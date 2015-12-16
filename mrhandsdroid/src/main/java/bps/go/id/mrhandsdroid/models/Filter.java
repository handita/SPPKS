package bps.go.id.mrhandsdroid.models;

/**
 * Created by handi_000 on 7/1/2015.
 */
public interface Filter<T,E> {
    public boolean isMatched(T object, E text);
}