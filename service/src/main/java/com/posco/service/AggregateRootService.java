forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}RepositoryService.java
path: {{boundedContext.name}}/s20a01-service/src/main/java/com/posco/{{boundedContext.name}}/s20a01/service
---
package com.posco.{{boundedContext.name}}.s20a01.service;

import com.posco.{{boundedContext.name}}.s20a01.domain.{{namePascalCase}};
import com.posco.{{boundedContext.name}}.s20a01.domain.{{namePascalCase}}Repository;
{{#commands}}
import com.posco.{{boundedContext.name}}.s20a01.service.{{namePascalCase}}Command;
{{/commands}}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value="/{{namePlural}}")
@Service
@Transactional
public class {{namePascalCase}}RepositoryService {

    private final {{namePascalCase}}Repository {{nameCamelCase}}Repository;

    @Autowired
    public {{namePascalCase}}RepositoryService({{namePascalCase}}Repository {{nameCamelCase}}Repository) {
        this.{{nameCamelCase}}Repository = {{nameCamelCase}}Repository;
    }

    {{#commands}}
    {{#isRestRepository}}
    {{/isRestRepository}}
    {{^isRestRepository}}
    {{#checkMethod controllerInfo.method}}
    @RequestMapping(value = "/{{../namePlural}}/{id}/{{controllerInfo.apiPath}}",
        method = RequestMethod.{{controllerInfo.method}},
        produces = "application/json;charset=UTF-8")
    public {{../namePascalCase}} {{nameCamelCase}}(@PathVariable(value = "id") {{../keyFieldDescriptor.className}} id, {{#if (hasFields fieldDescriptors)}}@RequestBody {{namePascalCase}}Command {{nameCamelCase}}Command, {{/if}}HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /{{../nameCamelCase}}/{{nameCamelCase}}  called #####");
            Optional<{{../namePascalCase}}> optional{{../namePascalCase}} = {{../nameCamelCase}}Repository.findById(id);
            
            optional{{../namePascalCase}}.orElseThrow(()-> new Exception("No Entity Found"));
            {{../namePascalCase}} {{../nameCamelCase}} = optional{{../namePascalCase}}.get();
            {{../nameCamelCase}}.{{nameCamelCase}}({{#if (hasFields fieldDescriptors)}}{{nameCamelCase}}Command{{/if}});
            
            {{../nameCamelCase}}Repository.{{#methodConvert controllerInfo.method}}{{/methodConvert}}({{../nameCamelCase}});
            return {{../nameCamelCase}};
            
    }
    {{#each ../aggregateRoot.entities.relations}}
    {{#if (isGeneralization targetElement.namePascalCase ../../namePascalCase relationType)}}
    @RequestMapping(value = "/{{#toURL sourceElement.nameCamelCase}}{{/toURL}}/{id}/{{../controllerInfo.apiPath}}",
            method = RequestMethod.{{../controllerInfo.method}},
            produces = "application/json;charset=UTF-8")
    public {{../../namePascalCase}} {{../nameCamelCase}}{{sourceElement.namePascalCase}}(
        @PathVariable(value = "id") {{../../keyFieldDescriptor.className}} id, {{#if (hasFields ../fieldDescriptors)}}@RequestBody {{../namePascalCase}}Command {{../nameCamelCase}}Command, {{/if}}HttpServletRequest request, HttpServletResponse response) throws Exception {
            return {{../nameCamelCase}}(id, {{#if (hasFields ../fieldDescriptors)}}{{../nameCamelCase}}Command,{{/if}} request, response);
    }
    {{/if}}
    {{/each}}
    {{/checkMethod}}
    {{^checkMethod controllerInfo.method}}
    @RequestMapping(value = "/{{../namePlural}}{{#if controllerInfo.apiPath}}{{controllerInfo.apiPath}}{{/if}}",
            method = RequestMethod.{{controllerInfo.method}},
            produces = "application/json;charset=UTF-8")
    public {{../namePascalCase}} {{nameCamelCase}}(HttpServletRequest request, HttpServletResponse response, 
        {{#if fieldDescriptors}}@RequestBody {{namePascalCase}}Command {{nameCamelCase}}Command{{/if}}) throws Exception {
            System.out.println("##### /{{aggregate.nameCamelCase}}/{{nameCamelCase}}  called #####");
            {{aggregate.namePascalCase}} {{aggregate.nameCamelCase}} = new {{aggregate.namePascalCase}}();
            {{aggregate.nameCamelCase}}.{{nameCamelCase}}({{#if fieldDescriptors}}{{nameCamelCase}}Command{{/if}});
            {{aggregate.nameCamelCase}}Repository.save({{aggregate.nameCamelCase}});
            return {{aggregate.nameCamelCase}};
    }
    {{/checkMethod}}    
    {{/isRestRepository}}
    {{/commands}}

    {{#aggregateRoot.operations}}
    {{#setOperations ../commands name}}
    {{#isOverride}}
    @Override
    {{/isOverride}}
    {{^isRootMethod}}
    public {{returnType}} {{name}}({{../namePascalCase}} {{../nameCamelCase}}){
        // 비즈니스 로직 호출
        {{returnType}} result = {{../nameCamelCase}}.{{name}}();
        
        // 필요한 경우 레포지토리에 저장
        {{nameCamelCase}}Repository.save({{../nameCamelCase}});
        
        return result;
    }
    {{/isRootMethod}}
    {{/setOperations}}
    {{/aggregateRoot.operations}}
}