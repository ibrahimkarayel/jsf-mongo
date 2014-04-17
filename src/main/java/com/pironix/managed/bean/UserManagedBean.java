package com.pironix.managed.bean;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.pironix.model.mongodb.User;
import com.pironix.service.mongodb.impl.UserService;

/**
 * @user ikarayel
 * <p/>
 * User Managed Bean
 */
@ManagedBean(name = "userMB")
@SessionScoped
public class UserManagedBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final Logger logger = Logger.getLogger(UserManagedBean.class);

    /* @PostConstruct
     void initialiseSession() {
         FacesContext.getCurrentInstance().getExternalContext().getSession(true);
     }*/

    /**
     * inject userservice
     */
    @ManagedProperty(value = "#{userMongoService}")
    UserService userService;
    List<User> userList;
    private User selectedUser;
    private String name;
    private String surname;
    private String phone;

    public UserManagedBean() {
    }

    public void goToMainPage() {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        try {
            extContext.redirect(extContext.getRequestContextPath() + "/pages/index.xhtml");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Add User
     */
    public void addUser() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            User user = new User();
            user.setName(getName());
            user.setSurname(getSurname());
            user.setPhone(getPhone());
            getUserService().addUser(user);
            logger.info("user :" + user.getName() + " saved.");
            reset();
            refresh();
            context.addCallbackParam(SUCCESS, true);
        } catch (DataAccessException e) {
            reset();
            refresh();
            context.addCallbackParam(SUCCESS, false);
            logger.error(e.getMessage());
        }
    }

    /**
     * Delete User
     *
     * @param User user
     */
    public void deleteUser(User user) {
        FacesMessage msg = null;
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            getUserService().deleteUser(user);
            context.addCallbackParam(SUCCESS, true);
            logger.info("user :" + user.getName() + " deleted.");
            // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User " + getName() + " is deleted"));
        } catch (DataAccessException e) {
            context.addCallbackParam(SUCCESS, false);
            logger.error(e.getMessage());
        }

    }

    /**
     * Update User
     */
    public void updateUser() {
        FacesMessage msg = null;
        RequestContext context = RequestContext.getCurrentInstance();
        User user = getSelectedUser();
        try {
            if (null != user) {
                List<String> param = new ArrayList<String>();
                param.add(getName());
                param.add(getSurname());
                param.add(getPhone());
                getUserService().updateUser(user, param);
                //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User " + getName() + " is updated"));
                context.addCallbackParam("success", true);
                logger.info("user :" + user.getName() + " updated.");
                user = null;
            }
        } catch (DataAccessException e) {
            context.addCallbackParam("success", false);
            logger.error(e.getMessage());
        }

    }

    /**
     * Get User List
     *
     * @return List - User list
     */
    public List<User> getUserList() {
        return userService.getUsers();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Clear User parameters
     */
    protected void reset() {
        this.setName("");
        this.setSurname("");
        this.setPhone("");
    }

    /**
     * Refresh view
     */
    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context
                .getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }


}