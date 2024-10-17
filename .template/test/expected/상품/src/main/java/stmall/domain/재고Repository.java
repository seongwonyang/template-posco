package stmall.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stmall.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "재고", path = "재고")
public interface 재고Repository
    extends PagingAndSortingRepository<재고, Long> {}
