package song.sj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryItem is a Querydsl query type for CategoryItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryItem extends EntityPathBase<CategoryItem> {

    private static final long serialVersionUID = 2082205790L;

    public static final QCategoryItem categoryItem = new QCategoryItem("categoryItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCategoryItem(String variable) {
        super(CategoryItem.class, forVariable(variable));
    }

    public QCategoryItem(Path<? extends CategoryItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryItem(PathMetadata metadata) {
        super(CategoryItem.class, metadata);
    }

}

