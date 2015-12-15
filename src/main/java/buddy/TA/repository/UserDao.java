package buddy.TA.repository;

import buddy.TA.entity.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by zhihu on 15/12/15.
 */
@Component
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByLoginName(String loginName);
}
