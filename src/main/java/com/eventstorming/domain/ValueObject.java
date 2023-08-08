forEach: ValueObject
fileName: {{namePascalCase}}.java
path: {{boundedContext.name}}/{{{options.packagePath}}}/domain
mergeType: {{#mergeType _type}}{{/mergeType}}
---
package {{options.package}}.domain;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;
{{#checkBigDecimal fieldDescriptors}}{{/checkBigDecimal}}

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class {{namePascalCase}} {
    {{#aggregateRoot.fieldDescriptors}}
    {{^isVO}}{{#isKey}}
    {{#checkClassType ../aggregateRoot.fieldDescriptors}}{{/checkClassType}}
    {{/isKey}}{{/isVO}}
    {{#fieldDescriptors}}
    {{#isLob}}@Lob{{/isLob}}
    {{#if (isPrimitive className)}}{{#isList}}@ElementCollection{{/isList}}{{/if}}
    {{#checkRelations ../relations className isVO}} {{/checkRelations}}
    private {{{className}}} {{nameCamelCase}};
    {{/fieldDescriptors}}

{{#operations}}
    {{#isOverride}}
    @Override
    {{/isOverride}}
    public {{returnType}} {{name}}() {
    }
{{/operations}}


}

//>>> DDD / Value Object
<function>
window.$HandleBars.registerHelper('checkClassType', function (fieldDescriptors) {
    for(var i = 0; i < fieldDescriptors.length; i ++ ){
        if(fieldDescriptors[i] && fieldDescriptors[i].className == 'Long'){
            return "@GeneratedValue(strategy=GenerationType.AUTO)";
        }
    }
    return "";
});
window.$HandleBars.registerHelper('mergeType', function (type) {
    if(type.includes('enum')) {
        return 'template'
    } else {
        return 'merge'
    }
});

window.$HandleBars.registerHelper('checkBigDecimal', function (fieldDescriptors) {
    for(var i = 0; i < fieldDescriptors.length; i ++ ){
        if(fieldDescriptors[i] && fieldDescriptors[i].className.includes('BigDecimal')){
            return "import java.math.BigDecimal;";
        }
    }
});

window.$HandleBars.registerHelper('isPrimitive', function (className) {
    if(className.includes("String") || className.includes("Integer") || className.includes("Long") || className.includes("Double") || className.includes("Float")
            || className.includes("Boolean") || className.includes("Date")){
        return true;
    } else {
        return false;
    }
});
window.$HandleBars.registerHelper('checkRelations', function (relations, className, isVO, referenceClass) {
    try {
        if(typeof relations === "undefined") {
            return 
        } else {
            // primitive type
            if(className.includes("String") || className.includes("Integer") || className.includes("Long") || className.includes("Double") || className.includes("Float")
                    || className.includes("Boolean") || className.includes("Date")) {
                if(className.includes("List")) {
                    return "@ElementCollection"
                }
            } else {
                // ValueObject
                if(isVO) {
                    if(className.includes("List")) {
                        return "@ElementCollection"
                    } else {
                        return "@Embedded"
                    }
                } else {
                    for(var i = 0; i < relations.length; i ++ ) {
                        if(relations[i] != null) {
                            if(className.includes(relations[i].targetElement.name) && !relations[i].relationType.includes("Generalization")) {
                                // Enumeration
                                if(relations[i].targetElement._type.endsWith('enum') || relations[i].targetElement._type.endsWith('Exception')) {
                                    return
                                }
                                // complex type
                                if(relations[i].sourceMultiplicity == "1" &&
                                        (relations[i].targetMultiplicity == "1..n" || relations[i].targetMultiplicity == "0..n") || className.includes("List")
                                ) {
                                    return "@OneToMany"

                                } else if((relations[i].sourceMultiplicity == "1..n" || relations[i].sourceMultiplicity == "0..n") && relations[i].targetMultiplicity == "1"){
                                    return "@ManyToOne"
                                
                                } else if(relations[i].sourceMultiplicity == "1" && relations[i].targetMultiplicity == "1"){
                                    return "@OneToOne"
                                
                                } else if((relations[i].sourceMultiplicity == "1..n" || relations[i].sourceMultiplicity == "0..n") &&
                                        (relations[i].targetMultiplicity == "1..n" || relations[i].targetMultiplicity == "0..n") || className.includes("List")
                                ) {
                                    return "@ManyToMany"
                                }
                            }
                        }
                    }
                    if(referenceClass) {
                        return "@OneToOne"
                    }
                }
            }
        }
    } catch (e) {
        console.log(e)
    }
});

</function>