package com.rimdev.rimpriv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.rimpriv.objects.BusinessMessage;
import com.rimdev.rimpriv.service.RoleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/API/V1")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/LISTRULE", method = RequestMethod.GET, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> allGroups(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = roleService.getAllRoles(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @RequestMapping(value = "/UPDRULE", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> updateRole(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = roleService.updateRoleServ(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @RequestMapping(value = "/ADDRULE", method = RequestMethod.PUT, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> addRole(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = roleService.addRoleServ(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @RequestMapping(value = "/DELRULE", method = RequestMethod.DELETE, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> deleteRole(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = roleService.deleteRoleServ(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @RequestMapping(value = "/CHKRULE", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> checkPriv(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = roleService.checkPrivilegeServ(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

}
