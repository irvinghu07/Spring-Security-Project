package com.imooc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.domain.QueryCondition;
import com.imooc.domain.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(QueryCondition condition, @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable){
        logger.info("query condition:{}", ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        logger.info("pageable information:{}", ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
        return new ArrayList<User>(){{
            add(new User("irving","0707"));
            add(new User("sarah", "520"));
            add(new User("pipi", "0610"));
        }};
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable("id") String id){
        User u = new User("irving", "0707");
        return u;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(objectError -> logger.error(objectError.getDefaultMessage()));
        }
        user.setId(1);
        logger.info("user:{}", user);
        return user;
    }

    @PutMapping("/{uid:\\d+}")
    @JsonView(User.UserSimpleView.class)
//    @PathVariable(name = "uid") Integer id,
    public User updateUser(@Valid @RequestBody User user, BindingResult result){
//        logger.info("user in updateUser():{}", user);
        if (result.hasErrors()){
            result.getAllErrors().stream().forEach(objectError -> logger.error(objectError.getDefaultMessage()));
        }
        user.setId(1);
        return user;
    }

    @DeleteMapping("{uid:\\d+}")
    public void delete(@PathVariable(value = "uid") Integer id){
        logger.info("id:{}", id);
    }
}
