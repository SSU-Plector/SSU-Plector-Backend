package ssuPlector.repository.developer;

import static ssuPlector.domain.QDeveloper.developer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.Developer;

@RequiredArgsConstructor
public class DeveloperRepositoryImpl implements DeveloperRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Developer> findDevelopers(String sortType, Pageable pageable) {

        OrderSpecifier<?> orderSpecifiers = sortTypeEq(sortType);

        QueryResults<Developer> results =
                queryFactory
                        .select(developer)
                        .from(developer)
                        .orderBy(orderSpecifiers)
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private OrderSpecifier<?> sortTypeEq(String sortType) {

        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        if (sortType == null) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, developer.updatedDate));
        } else if (sortType.equals("recent")) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, developer.updatedDate));
        } else if (sortType.equals("old")) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, developer.updatedDate));
        } else if (sortType.equals("high")) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, developer.hits));
        } else if (sortType.equals("low")) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, developer.hits));
        }
        return orderSpecifiers.get(0);
    }
}
