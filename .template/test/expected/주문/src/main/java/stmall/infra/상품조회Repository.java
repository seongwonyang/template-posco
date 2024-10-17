package stmall.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stmall.domain.*;

@RepositoryRestResource(collectionResourceRel = "상품조회", path = "상품조회")
public interface 상품조회Repository
    extends PagingAndSortingRepository<상품조회, Long> {}
