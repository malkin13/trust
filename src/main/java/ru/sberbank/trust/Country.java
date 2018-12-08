package ru.sberbank.trust;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import ru.sberbank.trust.dao.Identifiable;

import static lombok.AccessLevel.PRIVATE;


@Data
@Builder
@FieldNameConstants
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)

public class Country implements Identifiable<Integer> {
    @Default
    Integer id = 1;

    String name;

    String groupName;

    public Country(String name, String groupName) {
        this.name = name;
        this.groupName = groupName;
    }

}
