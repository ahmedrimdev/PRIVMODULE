package com.rimdev.rimpriv.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpriv.entities.T_Role;

@Repository
public interface T_Role_Repo extends JpaRepository<T_Role, Integer> {

    Optional<List<T_Role>> findNotDele();

    Optional<List<T_Role>> findByRoleId(String roleid);

    // Optional<List<T_Role>> findByRoleServiceCode(String roleservicecode);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roleservicecode IN ('?1' , '*', '-');", nativeQuery = true)
    List<T_Role> findByRoleServiceCodeNotNull(String roleservicecode);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roleservicecode IS NULL AND t.roleservicecode='*'; ", nativeQuery = true)
    List<T_Role> findByRoleServiceCodeNull(String roleservicecode);

    // Optional<List<T_Role>> findByRoledeviceid(String roledeviceid);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roledeviceid IN ('?1') AND t.roleservicecode = '?2'", nativeQuery = true)
    List<T_Role> findByRoledeviceidNotNull(List<String> roledeviceid, String roleservicecode);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roledeviceid IN ('?1') AND t.roleservicecode = '?2'", nativeQuery = true)
    List<T_Role> findByRoledeviceidNull(List<String> roledeviceid, String roleservicecode);

    // Optional<List<T_Role>> findByRoledevicetype(String roledevicetype);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roledevicetype IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3", nativeQuery = true)
    List<T_Role> findByRoledevicetypeNotNull(List<String> roledevicetype, String roleservicecode, String roledeviceid);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roledevicetype IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3", nativeQuery = true)
    List<T_Role> findByRoledevicetypeNull(List<String> roledevicetype, String roleservicecode, String roledeviceid);

    // Optional<List<T_Role>> findByRoleuserid(String roleuserid);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roleuserid IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3 AND t.roledevicetype= ?4", nativeQuery = true)
    List<T_Role> findByRoleuseridNotNull(List<String> roleuserid, String roleservicecode, String roledeviceid,
            String roledevicetype);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roleuserid IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3 AND t.roledevicetype= ?4", nativeQuery = true)
    List<T_Role> findByRoleuseridNull(List<String> roleuserid, String roleservicecode, String roledeviceid,
            String roledevicetype);

    // Optional<List<T_Role>> findByRoleusertype(String roleusertype);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roleusertype IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3 AND t.roledevicetype= ?4 AND t.roleuserid=?5", nativeQuery = true)
    List<T_Role> findByRoleusertypeNotNull(List<String> roleusertype, String roleservicecode, String roledeviceid,
            String roledevicetype, String roleuserid);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.roleusertype IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3 AND t.roledevicetype= ?4 AND t.roleuserid=?5", nativeQuery = true)
    List<T_Role> findByRoleusertypeNull(List<String> roleusertype, String roleservicecode, String roledeviceid,
            String roledevicetype, String roleuserid);

    // Optional<List<T_Role>> findByRolepagenum(String rolepagenum);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.rolepagenum IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3 AND t.roledevicetype= ?4 AND t.roleuserid=?5 AND t.roleusertype=?6", nativeQuery = true)
    List<T_Role> findByRolepagenumNotNull(List<String> rolepagenum, String roleservicecode, String roledeviceid,
            String roledevicetype, String roleuserid, String roleusertype);

    @Query(value = "SELECT * FROM tarm_devices.t_role t WHERE t.rolepagenum IN ('?1') AND t.roleservicecode = ?2 AND AND t.roledeviceid = ?3 AND t.roledevicetype= ?4 AND t.roleuserid=?5 AND t.roleusertype=?6", nativeQuery = true)
    List<T_Role> findByRolepagenumNull(List<String> rolepagenum, String roleservicecode, String roledeviceid,
            String roledevicetype, String roleuserid, String roleusertype);

}
