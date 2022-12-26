package com.rimdev.rimpriv.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimpriv.entities.T_Group;
import com.rimdev.rimpriv.entities.T_Role;
import com.rimdev.rimpriv.objects.BusinessMessage;
import com.rimdev.rimpriv.repo.T_Group_Repo;
import com.rimdev.rimpriv.repo.T_Role_Repo;

/**
 * RoleService
 */
@Service
public class RoleService {

    @Autowired
    T_Role_Repo t_Role_Repo;

    @Autowired
    T_Group_Repo t_Group_Repo;

    public BusinessMessage getAllRoles(BusinessMessage msg) {

        Optional<List<T_Role>> allRoles = t_Role_Repo.findNotDele();

        if (allRoles.isPresent()) {
            List<T_Role> roles = allRoles.get();
            msg.getPara().put("roles", roles);

        }

        return msg;

    }

    public BusinessMessage updateRoleServ(BusinessMessage msg) {
        try {
            String roleid = (String) msg.getPara().get("roleid");
            String roleservicecode = (String) msg.getPara().get("roleservicecode");
            String roledeviceid = (String) msg.getPara().get("roledeviceid");
            String roledevicetype = (String) msg.getPara().get("roledevicetype");
            String roleuserid = (String) msg.getPara().get("roleuserid");
            String roleusertype = (String) msg.getPara().get("roleusertype");
            String rolepagenum = (String) msg.getPara().get("rolepagenum");
            String groupid = (String) msg.getPara().get("groupid");

            // get
            Optional<List<T_Role>> rolesList = t_Role_Repo.findByRoleId(roleid);

            if (rolesList.isPresent()) {

                List<T_Role> updateRoles = rolesList.get();
                for (T_Role t_Role : updateRoles) {

                    if (roleservicecode != null)
                        t_Role.setRoleservicecode(roleservicecode);

                    if (roledeviceid != null)
                        t_Role.setRoledeviceid(roledeviceid);

                    if (roledevicetype != null)
                        t_Role.setRoledevicetype(roledevicetype);

                    if (roleuserid != null)
                        t_Role.setRoleuserid(roleuserid);

                    if (roleusertype != null)
                        t_Role.setRoleusertype(roleusertype);

                    if (rolepagenum != null)
                        t_Role.setRolepagenum(rolepagenum);

                    if (groupid != null)
                        t_Role.setGroupid(groupid);

                    t_Role_Repo.save(t_Role);
                }
            } else {
                // send error id is not correct

            }

            // reset para
            Map<String, Object> para = new HashMap<>();
            msg.setPara(para);

            msg = getAllRoles(msg);
        } catch (Exception e) {
            // send error with exception
        }

        return msg;

    }

    public BusinessMessage deleteRoleServ(BusinessMessage msg) {
        try {
            String roleid = (String) msg.getPara().get("roleid");
            // get
            Optional<List<T_Role>> role = t_Role_Repo.findByRoleId(roleid);

            if (role.isPresent()) {
                List<T_Role> delRoles = role.get();
                for (T_Role t_Role : delRoles) {
                    t_Role.setDeleted(1);
                    t_Role_Repo.save(t_Role);
                }
            } else {
                // send error id is not correct

            }

            // reset para
            Map<String, Object> para = new HashMap<>();
            msg.setPara(para);

            msg = getAllRoles(msg);
        } catch (Exception e) {
            // send error with exception
        }

        return msg;

    }

    public BusinessMessage addRoleServ(BusinessMessage msg) {
        try {

            String roleid = (String) msg.getPara().get("roleid");
            String roleservicecode = (String) msg.getPara().get("roleservicecode");
            String roledeviceid = (String) msg.getPara().get("roledeviceid");
            String roledevicetype = (String) msg.getPara().get("roledevicetype");
            String roleuserid = (String) msg.getPara().get("roleuserid");
            String roleusertype = (String) msg.getPara().get("roleusertype");
            String rolepagenum = (String) msg.getPara().get("rolepagenum");
            String groupid = (String) msg.getPara().get("groupid");

            T_Role addRole = new T_Role();

            if (roleid != null)
                addRole.setRoleid(roleid);

            if (roleservicecode != null)
                addRole.setRoleservicecode(roleservicecode);

            if (roledeviceid != null)
                addRole.setRoledeviceid(roledeviceid);

            if (roledevicetype != null)
                addRole.setRoledevicetype(roledevicetype);

            if (roleuserid != null)
                addRole.setRoleuserid(roleuserid);

            if (roleusertype != null)
                addRole.setRoleusertype(roleusertype);

            if (rolepagenum != null)
                addRole.setRolepagenum(rolepagenum);

            if (groupid != null)
                addRole.setGroupid(groupid);

            t_Role_Repo.save(addRole);

            // reset para
            Map<String, Object> para = new HashMap<>();
            msg.setPara(para);

            msg = getAllRoles(msg);
        } catch (Exception e) {
            // send error with exception
        }

        return msg;

    }

    public BusinessMessage checkPrivilegeServ(BusinessMessage msg) {
        try {

            String roleservicecode = (String) msg.getPara().get("roleservicecode");
            String roledeviceid = (String) msg.getPara().get("roledeviceid");
            String roledevicetype = (String) msg.getPara().get("roledevicetype");
            String roleuserid = (String) msg.getPara().get("roleuserid");
            String roleusertype = (String) msg.getPara().get("roleusertype");
            String rolepagenum = (String) msg.getPara().get("rolepagenum");

            String groupid = "";

            List<T_Role> rolesList = new ArrayList<>();
            // working on roleservicecode
            if (roleservicecode != null) {
                rolesList = t_Role_Repo.findByRoleServiceCodeNotNull(roleservicecode);
            } else {
                rolesList = t_Role_Repo.findByRoleServiceCodeNull(roleservicecode);
            }

            // After filtering with RoleServiceCode, getting RoleDeviceId
            List<String> roleDeviceIdsNotNUll = new ArrayList<>();
            List<String> roleDeviceIdsNUll = new ArrayList<>();
            for (int i = 0; i < rolesList.size(); i++) {
                if ((rolesList.get(i).getRoledeviceid().equals("-")
                        || rolesList.get(i).getRoledeviceid() != null)
                        && !(rolesList.get(i).getRoledeviceid().equals("*"))) {

                    roleDeviceIdsNotNUll.add(rolesList.get(i).getRoledeviceid());
                } else {
                    roleDeviceIdsNUll.add(rolesList.get(i).getRoledeviceid());
                    // the permission will be denied
                }

            }

            // Working on roledeviceid
            if (roledeviceid != null) {
                rolesList = t_Role_Repo.findByRoledeviceidNotNull(roleDeviceIdsNotNUll, roleservicecode);
            } else {
                rolesList = t_Role_Repo.findByRoledeviceidNull(roleDeviceIdsNotNUll, roleservicecode);
            }

            // After filtering by RoleDeviceId, getting RoleDeviceType
            List<String> roleDeviceTypesNotNUll = new ArrayList<>();
            List<String> roleDeviceTypesNUll = new ArrayList<>();
            for (int i = 0; i < rolesList.size(); i++) {
                if (rolesList.get(i).getRoledevicetype().equals("-")
                        || rolesList.get(i).getRoledevicetype() != null
                        || !(rolesList.get(i).getRoledevicetype().equals("*"))) {

                    roleDeviceTypesNotNUll.add(rolesList.get(i).getRoledevicetype());
                } else {
                    roleDeviceTypesNUll.add(rolesList.get(i).getRoledevicetype());
                    // the permission will be denied
                }

            }

            // Working on roledevicetype
            if (roledevicetype != null) {
                rolesList = t_Role_Repo.findByRoledevicetypeNotNull(roleDeviceTypesNotNUll, roleservicecode,
                        roledeviceid);
            } else {
                rolesList = t_Role_Repo.findByRoledevicetypeNull(roleDeviceTypesNUll, roleservicecode, roledeviceid);
            }

            // After filtering by RoleDeviceType, getting RoleUserId
            List<String> roleUserIdsNotNUll = new ArrayList<>();
            List<String> roleUserIdsNUll = new ArrayList<>();
            for (int i = 0; i < rolesList.size(); i++) {
                if (rolesList.get(i).getRoleuserid().equals("-")
                        || rolesList.get(i).getRoleuserid() != null
                        || rolesList.get(i).getRoleuserid().equals("*")) {

                    roleUserIdsNotNUll.add(rolesList.get(i).getRoleuserid());
                }
                if (rolesList.get(i).getRoleuserid() == null
                        || rolesList.get(i).getRoleuserid().equals("*")) {

                    roleUserIdsNUll.add(rolesList.get(i).getRoleuserid());
                    // the permission will be denied
                }

            }

            // Working on roleuserid
            if (roleuserid != null) {
                rolesList = t_Role_Repo.findByRoleuseridNotNull(roleUserIdsNotNUll, roleservicecode, roledeviceid,
                        roledevicetype);
            } else {
                rolesList = t_Role_Repo.findByRoleuseridNull(roleUserIdsNUll, roleservicecode, roledeviceid,
                        roledevicetype);
            }

            // After filtering by RoleUserId, getting RoleUserType
            List<String> roleUserTypesNotNUll = new ArrayList<>();
            List<String> roleUserTypesNUll = new ArrayList<>();
            for (int i = 0; i < rolesList.size(); i++) {
                if (rolesList.get(i).getRoleusertype().equals("-")
                        || rolesList.get(i).getRoleusertype() != null
                        || rolesList.get(i).getRoleusertype().equals("*")) {

                    roleUserTypesNotNUll.add(rolesList.get(i).getRoleusertype());
                }
                if (rolesList.get(i).getRoleusertype() == null
                        || rolesList.get(i).getRoleusertype().equals("*")) {

                    roleUserTypesNUll.add(rolesList.get(i).getRoleusertype());
                    // the permission will be denied
                }

            }

            // Working on roleusertype
            if (roleusertype != null) {
                rolesList = t_Role_Repo.findByRoleusertypeNotNull(roleUserTypesNotNUll, roleservicecode, roledeviceid,
                        roledevicetype, roleuserid);
            } else {
                rolesList = t_Role_Repo.findByRoleusertypeNull(roleUserTypesNUll, roleservicecode, roledeviceid,
                        roledevicetype, roleuserid);
            }

            // After filtering by RoleUserType, getting RolePageNum
            List<String> rolePageNumNotNUll = new ArrayList<>();
            List<String> rolePageNumNUll = new ArrayList<>();
            for (int i = 0; i < rolesList.size(); i++) {
                if (rolesList.get(i).getRolepagenum().equals("-")
                        || rolesList.get(i).getRolepagenum() != null
                        || rolesList.get(i).getRolepagenum().equals("*")) {

                    rolePageNumNotNUll.add(rolesList.get(i).getRolepagenum());
                }
                if (rolesList.get(i).getRolepagenum() == null
                        || rolesList.get(i).getRolepagenum().equals("*")) {

                    rolePageNumNUll.add(rolesList.get(i).getRolepagenum());
                    // the permission will be denied
                }

            }

            // Working on rolepagenum
            if (rolepagenum != null) {
                rolesList = t_Role_Repo.findByRolepagenumNotNull(rolePageNumNotNUll, roleservicecode, roledeviceid,
                        roledevicetype, roleuserid, roleusertype);
            } else {
                rolesList = t_Role_Repo.findByRolepagenumNull(rolePageNumNUll, roleservicecode, roledeviceid,
                        roledevicetype, roleuserid, roleusertype);
            }

            // After finishing the filtering, getting RoleId to be able to get the group_id
            List<String> roleIds = new ArrayList<>();
            for (int i = 0; i < rolesList.size(); i++) {
                roleIds.add(rolesList.get(i).getRoleid());
            }

            // get
            Optional<List<T_Role>> role = t_Role_Repo.findByRoleId(roleIds.get(0)); // 2 roles

            if (role.isPresent()) {
                List<T_Role> roles = role.get();
                HashMap<String, Integer> priorityGroupsMap = new HashMap<>();
                for (int i = 0; i < roles.size(); i++) {
                    Optional<T_Group> groups = t_Group_Repo.findByGroupId(roles.get(i).getGroupid());
                    if (groups.isPresent()) {
                        T_Group group = groups.get();
                        priorityGroupsMap.put(group.getGroupid(), Integer.valueOf(group.getGrouppriority()));
                    }
                }
                Map<String, Integer> values = sortByValue(priorityGroupsMap);

                List<Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(values.entrySet());
                Entry<String, Integer> lastEntry = entryList.get(entryList.size() - 1);

                Optional<T_Group> groups = t_Group_Repo.findByGroupId(lastEntry.getKey());
                if (groups.isPresent()) {
                    T_Group group = groups.get();
                    groupid = group.getGroupid();

                }
            } else {
                // send error id is not correct

            }
            System.out.println("groupid: " + groupid);

            // reset para
            Map<String, Object> para = new HashMap<>();
            para.put("groupid", groupid);

            msg.setPara(para);

            // msg = getAllRoles(msg);
        } catch (Exception e) {
            // send error with exception
        }

        return msg;

    }

    // function to sort hashmap by values
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new ArrayList<>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}