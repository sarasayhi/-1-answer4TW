package com.Marissa.FAQ.repository;

import com.Marissa.FAQ.repository.po.DocContent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DocContentRepository extends PagingAndSortingRepository<DocContent, Long> {
//    @Query(value = "select d from Doc d where d.deleted = false")
//    List<Doc> getDocByPage(Pageable pageable);

}
