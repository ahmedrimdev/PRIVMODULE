package com.rimdev.rimpriv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimpriv.entities.T_Group;
import com.rimdev.rimpriv.objects.BusinessMessage;
import com.rimdev.rimpriv.repo.T_Group_Repo;

/**
 * GroupService
 */
@Service
public class GroupService {

    @Autowired
    T_Group_Repo t_Group_Repo;

    public BusinessMessage getAllGroups(BusinessMessage msg) {

        Optional<List<T_Group>> allGroups = t_Group_Repo.findNotDele();

        if (allGroups.isPresent()) {
            List<T_Group> groups = allGroups.get();

            msg.getPara().put("groups", groups);
        }

        return msg;

    }

    public BusinessMessage updateGroupServ(BusinessMessage msg) {
        try {
            String groupid = (String) msg.getPara().get("groupid");
            String groupname = (String) msg.getPara().get("groupname");
            String grouppriority = (String) msg.getPara().get("grouppriority");
            String groupstatus = (String) msg.getPara().get("groupstatus");

            // get
            Optional<T_Group> group = t_Group_Repo.findByGroupId(groupid);

            if (group.isPresent()) {
                T_Group updateGroup = group.get();
                if (groupname != null)
                    updateGroup.setGroupname(groupname);
                if (grouppriority != null)
                    updateGroup.setGrouppriority(grouppriority);
                if (groupstatus != null)
                    updateGroup.setGroupstatus(groupstatus);

                t_Group_Repo.save(updateGroup);
            } else {
                // send error id is not correct

            }

            // reset para
            Map<String, Object> para = new HashMap<>();
            msg.setPara(para);

            msg = getAllGroups(msg);
        } catch (Exception e) {
            // send error with exception
        }

        return msg;

    }

    public BusinessMessage deleteGroupServ(BusinessMessage msg) {
        try {
            String groupid = (String) msg.getPara().get("groupid");
            // get
            Optional<T_Group> group = t_Group_Repo.findByGroupId(groupid);

            if (group.isPresent()) {
                T_Group delGroup = group.get();
                delGroup.setDeleted(1);
                t_Group_Repo.delete(delGroup);
            } else {
                // send error id is not correct

            }

            // reset para
            Map<String, Object> para = new HashMap<>();
            msg.setPara(para);

            msg = getAllGroups(msg);
        } catch (Exception e) {
            // send error with exception
        }

        return msg;

    }

    public BusinessMessage addGroupServ(BusinessMessage msg) {
        try {

            String groupid = (String) msg.getPara().get("groupid");
            String groupname = (String) msg.getPara().get("groupname");
            String grouppriority = (String) msg.getPara().get("grouppriority");
            String groupstatus = (String) msg.getPara().get("groupstatus");

            T_Group addGroup = new T_Group();

            if (groupid != null)
                addGroup.setGroupid(groupid);

            if (groupname != null)
                addGroup.setGroupname(groupname);

            if (grouppriority != null)
                addGroup.setGrouppriority(grouppriority);

            if (groupstatus != null)
                addGroup.setGroupstatus(groupstatus);

            t_Group_Repo.save(addGroup);

            // reset para
            Map<String, Object> para = new HashMap<>();
            msg.setPara(para);

            msg = getAllGroups(msg);
        } catch (Exception e) {
            // send error with exception
        }

        return msg;

    }

}