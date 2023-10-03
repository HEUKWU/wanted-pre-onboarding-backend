package spring.wantedpreonboardingbackend.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import spring.wantedpreonboardingbackend.entity.Post;
import spring.wantedpreonboardingbackend.entity.Search;

import java.util.List;

import static spring.wantedpreonboardingbackend.entity.QPost.post;

public class CustomPostRepositoryImpl extends QuerydslRepositorySupport implements CustomPostRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public CustomPostRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Post> findBySearchOption(Pageable pageable, Search search) {
        JPAQuery<Post> query = jpaQueryFactory.selectFrom(post)
                .where(post.deleted.isFalse(),
                        eqSearch(search.getSearch())
                );

        List<Post> posts = this.getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(posts, pageable, query.stream().count());
    }

    private BooleanExpression eqSearch(String search) {
        if (search == null || search.isEmpty()) {
            return null;
        }

        return post.company.companyName.containsIgnoreCase(search)
                .or(post.company.companyName.containsIgnoreCase(search))
                .or(post.company.country.containsIgnoreCase(search))
                .or(post.company.location.containsIgnoreCase(search))
                .or(post.position.containsIgnoreCase(search))
                .or(post.skill.containsIgnoreCase(search));
    }
}
