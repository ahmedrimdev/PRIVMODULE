package com.rimdev.rimpriv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.rimpriv.objects.BusinessMessage;
import com.rimdev.rimpriv.service.GroupService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/API/V1")
public class GroupController {

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/LISTGRP", method = RequestMethod.GET, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> allGroups(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = groupService.getAllGroups(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @RequestMapping(value = "/UPDGRP", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> updateGroup(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = groupService.updateGroupServ(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @RequestMapping(value = "/ADDGRP", method = RequestMethod.PUT, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> addGroup(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = groupService.addGroupServ(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @RequestMapping(value = "/DELGRP", method = RequestMethod.DELETE, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, })
    public @ResponseBody ResponseEntity<BusinessMessage> deleteGroup(HttpServletRequest request,
            @RequestBody BusinessMessage msg) {

        try {
            msg = groupService.deleteGroupServ(msg);
        } catch (Exception e) {
            // return error exception

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

}
