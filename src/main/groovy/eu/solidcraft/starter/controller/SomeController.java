package eu.solidcraft.starter.controller;

import com.google.common.collect.ImmutableMap;
import eu.solidcraft.starter.domain.some.SomeEntity;
import eu.solidcraft.starter.domain.some.SomeEntityRepository;
import eu.solidcraft.starter.infrastructure.security.LoggedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
class SomeController {
    private SomeEntityRepository someEntityRepository;
    private LoggedUserRepository loggedUserRepository;

    @Autowired
    SomeController(SomeEntityRepository someEntityRepository, LoggedUserRepository loggedUserRepository) {
        this.someEntityRepository = someEntityRepository;
        this.loggedUserRepository = loggedUserRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index() {
        return "redirect:/some/mine";
    }

    @RequestMapping(value = "/some/mine", method = RequestMethod.GET)
    Map mine() {
        List<SomeEntity> entities = someEntityRepository.findByUsername(loggedUserRepository.getLoggedUserName());
        return ImmutableMap.of("entities", entities);
    }

    @RequestMapping(value = "/some/add")
    String add(@RequestParam("amount") BigDecimal amount) {
        Assert.notNull(amount);
        SomeEntity entity = new SomeEntity(loggedUserRepository.getLoggedUserName(), amount, new Date());
        someEntityRepository.save(entity);
        return "redirect:/some/mine";
    }

}