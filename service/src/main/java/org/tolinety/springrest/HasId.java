package org.tolinety.springrest;

/**
 * Created by ToliNeTy on 05.03.2017.
 */
public interface HasId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return (getId() == null);
    }
}
