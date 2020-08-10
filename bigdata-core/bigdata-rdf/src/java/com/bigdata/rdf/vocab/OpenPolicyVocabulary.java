package com.bigdata.rdf.vocab;

import com.bigdata.rdf.vocab.decls.*;

public class OpenPolicyVocabulary extends BaseVocabulary {

    public OpenPolicyVocabulary() {
        super();
    }
    public OpenPolicyVocabulary(final String namespace) {
        super( namespace );
    }

    @Override
    protected void addValues() {

        addDecl(new RDFVocabularyDecl());

        addDecl(new RDFSVocabularyDecl());

        addDecl(new OWLVocabularyDecl());

        addDecl(new SKOSVocabularyDecl());

        addDecl(new XMLSchemaVocabularyDecl());

        addDecl(new OpenPolicyVocabularyDecl());

    }
}
