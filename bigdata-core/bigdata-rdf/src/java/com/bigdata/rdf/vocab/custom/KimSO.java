package com.bigdata.rdf.vocab.custom;

import org.openrdf.model.Namespace;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.NamespaceImpl;
import org.openrdf.model.impl.ValueFactoryImpl;

public class KimSO {
    public static final String NAMESPACE = "http://www.ontotext.com/kim/2006/05/kimso#";
    public static final String PREFIX = "kimso";
    public static final Namespace NS = new NamespaceImpl("kimso", "http://www.ontotext.com/kim/2006/05/kimso#");
    public static final URI VISIBILITYLEVEL1;

    public KimSO(){

    }

    static {
        ValueFactory factory = ValueFactoryImpl.getInstance();
        VISIBILITYLEVEL1 = factory.createURI("http://www.ontotext.com/kim/2006/05/kimso#", "visibilityLevel1");
    }
}
