package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

@Service
@Repository
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Transactional(readOnly = true)
    @Override
    public Role roleAdmin() {
        return roleDao.getAdmin();
    }

    @Transactional(readOnly = true)
    @Override
    public Role roleUser() {
        return roleDao.getUser();
    }
}
