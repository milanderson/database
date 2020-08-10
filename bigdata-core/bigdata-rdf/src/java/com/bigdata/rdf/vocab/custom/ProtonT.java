package com.bigdata.rdf.vocab.custom;

import org.openrdf.model.Namespace;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.NamespaceImpl;
import org.openrdf.model.impl.ValueFactoryImpl;

public class ProtonT {
    public static final String NAMESPACE = "http://proton.semanticweb.org/2006/05/protont#";
    public static final String PREFIX = "protont";
    public static final Namespace NS = new NamespaceImpl("protont", "http://proton.semanticweb.org/2006/05/protont#");
    public static final URI ROLEHOLDER;
    public static final URI ROLEIN;
    public static final URI AGENT;
    public static final URI INVOLVEDIN;
    public static final URI DOCUMENT;

    public ProtonT(){

    }

    static {
        ValueFactory factory = ValueFactoryImpl.getInstance();
        ROLEHOLDER = factory.createURI("http://proton.semanticweb.org/2006/05/protont#", "roleHolder");
        ROLEIN = factory.createURI("http://proton.semanticweb.org/2006/05/protont#", "roleIn");
        AGENT = factory.createURI("http://proton.semanticweb.org/2006/05/protont#", "Agent");
        INVOLVEDIN = factory.createURI("http://proton.semanticweb.org/2006/05/protont#", "involvedIn");
        DOCUMENT = factory.createURI("http://proton.semanticweb.org/2006/05/protont#", "Document");
    }
}
