package com.culticare.posts.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategories is a Querydsl query type for Categories
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategories extends EntityPathBase<Categories> {

    private static final long serialVersionUID = 1354977393L;

    public static final QCategories categories = new QCategories("categories");

    public final com.culticare.QBaseTimeEntity _super = new com.culticare.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public QCategories(String variable) {
        super(Categories.class, forVariable(variable));
    }

    public QCategories(Path<? extends Categories> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategories(PathMetadata metadata) {
        super(Categories.class, metadata);
    }

}

