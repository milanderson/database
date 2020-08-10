package com.bigdata.rdf.vocab.decls;

import com.bigdata.rdf.vocab.VocabularyDecl;
import com.bigdata.rdf.vocab.custom.*;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class OpenPolicyVocabularyDecl implements VocabularyDecl {
    static private final URI[] uris = new URI[]{
            new URIImpl(ProtonS.NAMESPACE),
            new URIImpl(ProtonKM.NAMESPACE),
            new URIImpl(ProtonT.NAMESPACE),
            new URIImpl(ProtonU.NAMESPACE),
            new URIImpl(OpenPolicy.NAMESPACE),
            new URIImpl(KimSO.NAMESPACE),
            ProtonS.TRANSITIVEOVER, //
            ProtonKM.MENTIONS, //
            ProtonT.AGENT, //
            ProtonT.DOCUMENT, //
            ProtonT.INVOLVEDIN, //
            ProtonT.ROLEHOLDER, //
            ProtonT.ROLEIN, //
            OpenPolicy.HASDOCUMENT, //
            OpenPolicy.HASGROUPTRANSITIVE, //
            OpenPolicy.INDIRECTLYRELATEDTO, //
            KimSO.VISIBILITYLEVEL1, //
    };

    public OpenPolicyVocabularyDecl() {
    }

    public Iterator<URI> values() {

        return Collections.unmodifiableList(Arrays.asList(uris)).iterator();

    }
}
