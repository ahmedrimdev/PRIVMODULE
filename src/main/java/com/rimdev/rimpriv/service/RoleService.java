package com.rimdev.rimpriv.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
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
            String groupid = (String) msg.getPara().get("ptid2");

            // get
            Optional<List<T_Role>> rolesList = t_Role_Repo.findByRoleId(roleid);
            Optional<T_Group> group = t_Group_Repo.findByGroupId(groupid);

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

                    if (group.isPresent()) {
                        if (groupid != null) {
                            t_Role.setGroupid(groupid);
                        }
                    } else {
                        msg.setErrorflag(true);
                        t_Role.setGroupid(null);
                    }

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
            String roleservicecode = (String) msg.getServiceCode();
            String roledeviceid = (String) msg.getPara().get("roledeviceid");
            String roledevicetype = (String) msg.getPara().get("roledevicetype");
            String roleuserid = (String) msg.getPara().get("roleuserid");
            String roleusertype = (String) msg.getPara().get("roleusertype");
            String rolepagenum = (String) msg.getPara().get("rolepagenum");
            String groupid = (String) msg.getPara().get("groupid");

            T_Role addRole = new T_Role();

            Optional<T_Group> group = t_Group_Repo.findByGroupId(groupid);

            if (group.isPresent()) {

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
            } else {
                msg.setErrorflag(true);
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

    public BusinessMessage checkPrivilegeServ(BusinessMessage msg) {
        try {

            String roleservicecode = msg.getServiceCode();
            String roledeviceid = (String) msg.getPara().get("roledeviceid");
            String roledevicetype = (String) msg.getPara().get("roledevicetype");
            String roleuserid = (String) msg.getPara().get("roleuserid");
            String roleusertype = (String) msg.getPara().get("roleusertype");
            String rolepagenum = (String) msg.getPara().get("rolepagenum");

            int groupid = 0;

            List<T_Role> rolesList = new ArrayList<>();

            // working on roleservicecode
            if (roleservicecode != null) {
                rolesList = t_Role_Repo.findByRoleServiceCodeNotNull(roleservicecode);
            } else {
                rolesList = t_Role_Repo.findByRoleServiceCodeNull(roleservicecode);
            }
            System.out.println("roleservicecode: " + roleservicecode);

            if (rolesList.isEmpty()) {
                // insert error no privledge
                msg.setErrorflag(true);
                return msg;
            }

            // new T_Role().getRoledeviceid()
            if (roledeviceid != null && !roledeviceid.equals("*") && !roledeviceid.equals("NULL")) {
                rolesList = processRoleNotNullable(rolesList, "getRoledeviceid", roledeviceid);
            } else { // roledeviceid= *, "" , NULL will be denied
                msg.setErrorflag(true);
                return msg;
            }

            if (rolesList.isEmpty()) {
                // insert error no privledge
                msg.setErrorflag(true);
                return msg;
            }

            // new T_Role().getRoledevicetype()
            if (roledevicetype != null && !roledevicetype.equals("*") && !roledevicetype.equals("NULL")) {
                rolesList = processRoleNotNullable(rolesList, "getRoledevicetype", roledevicetype);
            } else { // roledevicetype= * , "" , NULL will be denied
                msg.setErrorflag(true);
                return msg;

            }
            if (rolesList.isEmpty()) {
                // insert error no privledge
                msg.setErrorflag(true);

                return msg;
            }

            // new T_Role().getRoleuserid()
            if (roleuserid != null && !roleuserid.equals("NULL")) {
                rolesList = processRoleNotNullable(rolesList, "getRoleuserid", roleuserid);
            } else {

            }

            if (rolesList.isEmpty()) {
                // insert error no privledge
                msg.setErrorflag(true);

                return msg;
            }

            // new T_Role().getRoleusertype()
            rolesList = processRoleNotNullable(rolesList, "getRoleusertype", roleusertype);

            if (rolesList.isEmpty()) {
                // insert error no privledge
                msg.setErrorflag(true);

                return msg;
            }

            // new T_Role().getRolepagenum()
            rolesList = processRoleNotNullable(rolesList, "getRolepagenum", rolepagenum);

            if (rolesList.isEmpty()) {
                // insert error no privledge
                msg.setErrorflag(true);

                return msg;
            }

            String roleid = "";
            for (int i = 0; i < rolesList.size(); i++) {
                roleid = rolesList.get(i).getRoleid();
            }

            // get
            Optional<List<T_Role>> role = t_Role_Repo.findByRoleId(roleid); // 2 roles

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
                    groupid = group.getPtid();

                }
            } else {
                // send error id is not correct

            }
            System.out.println("groupid: " + groupid);

            // reset para
            Map<String, Object> para = new HashMap<>();
            para.put("groupid", groupid);

            msg.setPara(para);

        } catch (Exception e) {
            // send error with exception
            msg.setErrorflag(true);
            e.printStackTrace();
        }
        return msg;
    }

    public List<T_Role> processRoleNotNullable(List<T_Role> rolesList, String methodName, String parmetercheck) {

        List<T_Role> roleafter = new ArrayList<>();

        Object result = null;

        for (int i = 0; i < rolesList.size(); i++) {
            try {
                Method m = null;

                m = rolesList.get(i).getClass().getDeclaredMethod(methodName);

                result = m.invoke(rolesList.get(i)); // field value
                // roleafter.add(result);
            } catch (Exception iae) {
                return roleafter;
            }

            if (result.equals("NULL") || parmetercheck == null) {
                roleafter.remove(rolesList.get(i));
            } else {
                if (parmetercheck != null
                        && !result.equals("-")
                        && !result.equals(parmetercheck)
                        && !result.equals("*")) {
                    roleafter.remove(rolesList.get(i));
                }

            }
        }

        return roleafter;
    }

    public List<T_Role> processRoleNullable(List<T_Role> rolesList, String methodName, String parmetercheck) {

        List<T_Role> roleafter = new ArrayList<>();

        String result = null;

        for (int i = 0; i < rolesList.size(); i++) {
            try {
                Method m = null;

                m = rolesList.get(i).getClass().getDeclaredMethod(methodName);

                result = (String) m.invoke(rolesList.get(i)); // field value
                // roleafter.add(result);
            } catch (Exception iae) {
                return roleafter;
            }

            if (result.equals("-") // not null
                    || (result != null && !result.equals("*") && !result.equals("NULL"))) {
                roleafter.remove(rolesList.get(i));
            } else {
                if (result != null
                        && !result.equals(parmetercheck)) {
                    roleafter.remove(rolesList.get(i));
                }

            }
        }

        return roleafter;
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