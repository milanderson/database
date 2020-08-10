package com.bigdata.rdf.vocab.custom;

import org.openrdf.model.Namespace;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.NamespaceImpl;
import org.openrdf.model.impl.ValueFactoryImpl;

public class OpenPolicy {
    public static final String NAMESPACE = "http://ontotext.com/openpolicy#";
    public static final String PREFIX = "op";
    public static final Namespace NS = new NamespaceImpl("op", "http://ontotext.com/openpolicy#");
    public static final URI INDIRECTLYRELATEDTO;
    public static final URI HASGROUPTRANSITIVE;
    public static final URI HASDOCUMENT;

    public OpenPolicy() {
    }

    static {
        ValueFactory factory = ValueFactoryImpl.getInstance();
        INDIRECTLYRELATEDTO = factory.createURI("http://ontotext.com/openpolicy#", "indirectlyRelatedTo");
        HASGROUPTRANSITIVE = factory.createURI("http://ontotext.com/openpolicy#", "hasGroupTransitive");
        HASDOCUMENT = factory.createURI("http://ontotext.com/openpolicy#", "hasDocument");
    }
}
