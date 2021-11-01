package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.Group;
import dk.notfound.notifier.model.ServiceEntity;
import dk.notfound.notifier.model.ServiceEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ServiceEntityController {

    @Autowired
    ServiceEntityRepository serviceEntityRepository;

    @GetMapping(path="/serviceEntities")
    public @ResponseBody
    Iterable<ServiceEntity> listServiceEntities() {
        return serviceEntityRepository.findAll();
   }

    @PostMapping(path="/serviceEntities")
    public @ResponseBody
    ServiceEntity createServiceEntity(@RequestBody ServiceEntity serviceEntity) {

        serviceEntityRepository.save(serviceEntity);
        return serviceEntity;
    }

    @PutMapping(path="/serviceEntities")
    public @ResponseBody
    ServiceEntity put_createServiceEntity(@RequestBody ServiceEntity serviceEntity) {

        serviceEntityRepository.save(serviceEntity);
        return serviceEntity;
    }


}
