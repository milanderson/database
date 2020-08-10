package com.bigdata.rdf.vocab.custom;

import org.openrdf.model.Namespace;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.NamespaceImpl;
import org.openrdf.model.impl.ValueFactoryImpl;

public class ProtonKM {
    public static final String NAMESPACE = "http://proton.semanticweb.org/2006/05/protonkm#";
    public static final String PREFIX = "protonkm";
    public static final Namespace NS = new NamespaceImpl("protonkm", "http://proton.semanticweb.org/2006/05/protonkm#");
    public static final URI MENTIONS;

    public ProtonKM(){

    }

    static {
        ValueFactory factory = ValueFactoryImpl.getInstance();
        MENTIONS = factory.createURI("http://proton.semanticweb.org/2006/05/protonkm#", "mentions");
    }
}
