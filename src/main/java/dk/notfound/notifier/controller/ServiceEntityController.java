package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.ServiceEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ServiceEntityController {

    @Autowired
    ServiceEntityRepository serviceEntityRepository;



}
