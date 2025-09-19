package ${package}.${packageName}.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${package}.${packageName}.service.${className}Service;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${pathName}")
public class ${className}Controller {

    private final ${className}Service ${varName}Service;

    //TODO: create your api here
}