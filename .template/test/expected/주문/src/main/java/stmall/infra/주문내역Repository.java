package stmall.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stmall.domain.*;

@RepositoryRestResource(collectionResourceRel = "주문내역", path = "주문내역")
public interface 주문내역Repository
    extends PagingAndSortingRepository<주문내역, Long> {}
