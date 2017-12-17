package com.Marissa.FAQ.repository;

import com.Marissa.FAQ.repository.po.Doc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Transactional
public interface DocRepository extends PagingAndSortingRepository<Doc, Long> {
//    @Query(value = "select d from Doc d where d.deleted = false")
//    List<Doc> getDocByPage(Pageable pageable);

}
