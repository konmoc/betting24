package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.Role;
import pl.coderslab.betting.repository.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void saveRole(Role role){
        roleRepository.save(role);
    }

    public Role findRoleById(Long id){
        return  roleRepository.findRoleById(id);
    }

    public void deleteRoleWithId(Long id){
        roleRepository.deleteRoleById(id);
    }
}
