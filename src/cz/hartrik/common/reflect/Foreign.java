
package cz.hartrik.common.reflect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotace slouží k řádnému označení cizího kódu, který byl v projektu použit.
 * Je možné ji umístit na třídu, výčtový typ, rozhraní, metodu a konstruktor.
 * Hodnoty mohou zůstat prázdné - většinou jsou již obsaženy v dokumentaci.
 * Anotace se odstraní při kompilaci, v dokumentaci zůstává.
 * 
 * @version 2014-06-28
 * @author Patrik Harag
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR })
@Documented
public @interface Foreign {
    
    String value()   default "";
    String link()    default "";
    String license() default "";
    
}