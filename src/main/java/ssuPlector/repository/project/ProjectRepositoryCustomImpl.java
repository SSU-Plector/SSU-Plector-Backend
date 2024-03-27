package ssuPlector.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.Project;
import ssuPlector.domain.QProject;
import ssuPlector.domain.category.Category;

@RequiredArgsConstructor
public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Project> findProjects(
            String searchString, String category, String sortType, Pageable pageable) {
        QProject project = QProject.project;
        JPAQuery<Project> query = queryFactory.selectFrom(project);

        if (searchString != null) {
            query.where(project.name.containsIgnoreCase(searchString));
        }

        if (category != null) {
            query.where(project.category.eq(Category.valueOf(category)));
        }

        if (sortType != null) {
            if (sortType.equals("recent")) {
                query.orderBy(project.updatedDate.desc());
            } else if (sortType.equals("old")) {
                query.orderBy(project.updatedDate.asc());
            } else if (sortType.equals("high")) {
                query.orderBy(project.hits.desc());
            } else if (sortType.equals("low")) {
                query.orderBy(project.hits.asc());
            }
        }

        QueryResults<Project> results =
                query.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
