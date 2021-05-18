package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBinderRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.NonStandardField;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Indexed
public class OneAddress extends PanacheEntity implements Comparable {

    @FullTextField(analyzer = "address")
    @NonStandardField(name = "address_suggest", valueBinder = @ValueBinderRef(type = CompletionBinder.class))
    public String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OneAddress)) {
            return false;
        }
        OneAddress other = (OneAddress) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof OneAddress)) {
            return 1;
        }
        OneAddress other = (OneAddress) o;
        return this.address.compareTo(other.address);
    }
}
