package stmall.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stmall.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "배송", path = "배송")
public interface 배송Repository
    extends PagingAndSortingRepository<배송, Long> {}
