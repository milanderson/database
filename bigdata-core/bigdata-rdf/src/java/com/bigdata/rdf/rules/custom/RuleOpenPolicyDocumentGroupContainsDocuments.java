package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.rdf.vocab.custom.OpenPolicy;
import com.bigdata.rdf.vocab.custom.ProtonT;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.RDF;

public class RuleOpenPolicyDocumentGroupContainsDocuments extends Rule {

    private static final long serialVersionUID = -7423082674586471334L;

    public RuleOpenPolicyDocumentGroupContainsDocuments(String relationName, Vocabulary vocab) {

        super("documentGroupContainsDocuments",//
                new SPOPredicate(relationName, var("group"), vocab.getConstant(OpenPolicy.HASDOCUMENT), var("doc")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("group"), vocab.getConstant(OpenPolicy.HASGROUPTRANSITIVE), var("doc")),
                        new SPOPredicate(relationName, var("doc"), vocab.getConstant(RDF.TYPE), vocab.getConstant(ProtonT.DOCUMENT)),
                },//
                null
        );
    }
}