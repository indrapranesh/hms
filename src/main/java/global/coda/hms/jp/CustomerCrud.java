package global.coda.hms.jp;
import global.coda.hms.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCrud extends CrudRepository<Customer,Integer> {

}
