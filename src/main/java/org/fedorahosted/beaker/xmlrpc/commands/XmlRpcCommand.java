package org.fedorahosted.beaker.xmlrpc.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XmlRpcCommand {
    public String name();
    public String xmlRpcCommand();
}
