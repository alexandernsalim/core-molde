package com.ta.coremolde.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

public class ResponseMapper {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private static MapperFacade mapper = mapperFactory.getMapperFacade();

    public static <S, C> C map(S source, Class<C> referenceClass) {
        return mapper.map(source, referenceClass);
    }

    public static <S, C> List mapAsList(Iterable source, Class referenceClass) {
        return mapper.mapAsList(source, referenceClass);
    }

}
