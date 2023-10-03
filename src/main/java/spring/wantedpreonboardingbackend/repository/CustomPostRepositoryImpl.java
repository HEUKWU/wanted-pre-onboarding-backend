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
                .where(post.deleted.isFalse().and(
                        eqCompanyName(search.getSearch())
                        .or(eqCountry(search.getSearch()))
                        .or(eqLocation(search.getSearch()))
                        .or(eqPosition(search.getSearch()))
                        .or(eqSkill(search.getSearch())))
                );

        List<Post> posts = this.getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(posts, pageable, query.stream().count());
    }

    private BooleanExpression eqCompanyName(String companyName) {
        if (companyName == null || companyName.isEmpty()) {
            return null;
        }

        return post.company.companyName.eq(companyName);
    }

    private BooleanExpression eqCountry(String country) {
        if (country == null || country.isEmpty()) {
            return null;
        }

        return post.company.country.eq(country);
    }

    private BooleanExpression eqLocation(String location) {
        if (location == null || location.isEmpty()) {
            return null;
        }

        return post.company.location.eq(location);
    }

    private BooleanExpression eqPosition(String position) {
        if (position == null || position.isEmpty()) {
            return null;
        }

        return post.position.eq(position);
    }

    private BooleanExpression eqSkill(String skill) {
        if (skill == null || skill.isEmpty()) {
            return null;
        }

        return post.skill.eq(skill);
    }
}
