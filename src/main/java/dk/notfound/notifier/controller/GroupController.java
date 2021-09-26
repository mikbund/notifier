package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.Group;
import dk.notfound.notifier.model.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class GroupController {

    @Autowired
    GroupRepository groupRepository;


    @PostMapping(path="/groups")
    public @ResponseBody
    Group createGroup(@RequestBody Group group) {

        groupRepository.save(group);
        return group;
    }


    @GetMapping(path="/groups")
    public @ResponseBody Iterable<Group> listGroups() {

        return groupRepository.findAll();

    }

    /*
    @PostMapping(path="/groups/{id}/")
    public @ResponseBody
    Group createGroup(@RequestBody Group group) {

        groupRepository.save(group);
        return group;
    }
*/


}
