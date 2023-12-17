package com.hoon.review.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestaurantEntity is a Querydsl query type for RestaurantEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurantEntity extends EntityPathBase<RestaurantEntity> {

    private static final long serialVersionUID = -237302304L;

    public static final QRestaurantEntity restaurantEntity = new QRestaurantEntity("restaurantEntity");

    public final StringPath address = createString("address");

    public final DateTimePath<java.time.ZonedDateTime> createAt = createDateTime("createAt", java.time.ZonedDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.ZonedDateTime> updateAt = createDateTime("updateAt", java.time.ZonedDateTime.class);

    public QRestaurantEntity(String variable) {
        super(RestaurantEntity.class, forVariable(variable));
    }

    public QRestaurantEntity(Path<? extends RestaurantEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurantEntity(PathMetadata metadata) {
        super(RestaurantEntity.class, metadata);
    }

}

