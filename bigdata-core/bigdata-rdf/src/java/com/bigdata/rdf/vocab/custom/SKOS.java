package com.bigdata.rdf.vocab.custom;

import org.openrdf.model.Namespace;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.NamespaceImpl;
import org.openrdf.model.impl.ValueFactoryImpl;

public class SKOS {
    public static final String NAMESPACE = "http://www.w3.org/2004/02/skos/core#";
    public static final String PREFIX = "skos";
    public static final Namespace NS = new NamespaceImpl("skos", "http://www.w3.org/2004/02/skos/core#");
    public static final URI SYNONYM;

    public SKOS(){

    }

    static {
        ValueFactory factory = ValueFactoryImpl.getInstance();
        SYNONYM = factory.createURI("http://www.w3.org/2004/02/skos/core#", "synonym");
    }
}