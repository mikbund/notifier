package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.Group;
import dk.notfound.notifier.model.ServiceEntity;
import dk.notfound.notifier.model.ServiceEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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


    @GetMapping(path="/serviceEntities/{id}")
    public @ResponseBody
    Optional<ServiceEntity> getServiceEntity(@PathVariable Long id) {
        Optional<ServiceEntity> serviceEntity;
        serviceEntity = serviceEntityRepository.findById(id);

        return serviceEntity;
    }


    @PutMapping(path="/serviceEntities/{id}")
    public @ResponseBody
    Optional<ServiceEntity> putServiceEntity(@PathVariable Long id, @RequestBody ServiceEntity newServiceEntity) {


        Optional<ServiceEntity> existingServiceEntity = serviceEntityRepository.findById(id);
        if(  existingServiceEntity.isPresent() ) {
            ServiceEntity se = existingServiceEntity.get();
            BeanUtils.copyProperties(newServiceEntity,se);
            serviceEntityRepository.save(se);
        }

        return serviceEntityRepository.findById(id);
    }





    @DeleteMapping(path="/serviceEntities/{id}")
    public @ResponseBody
    Optional<ServiceEntity> deleteServiceEntity(@PathVariable Long id) {
        Optional<ServiceEntity> serviceEntity;
        serviceEntity = serviceEntityRepository.findById(id);

        serviceEntityRepository.deleteById(id);

        return serviceEntity;
    }
}
