package com.bigdata.rdf.vocab.custom;

import org.openrdf.model.Namespace;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.NamespaceImpl;
import org.openrdf.model.impl.ValueFactoryImpl;

public class ProtonS {
    public static final String NAMESPACE = "http://proton.semanticweb.org/2006/05/protons#";
    public static final String PREFIX = "protons";
    public static final Namespace NS = new NamespaceImpl("protons", "http://proton.semanticweb.org/2006/05/protons#");
    public static final URI TRANSITIVEOVER;

    public ProtonS(){

    }

    static {
        ValueFactory factory = ValueFactoryImpl.getInstance();
        TRANSITIVEOVER = factory.createURI("http://proton.semanticweb.org/2006/05/protons#", "transitiveOver");
    }
}
