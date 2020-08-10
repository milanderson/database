package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlOneOf2 extends Rule {

    private static final long serialVersionUID = -7423082674586471325L;

    public RuleOwlOneOf2(String relationName, Vocabulary vocab) {

        super("owl_oneOf2",//
                new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("c")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("c"), vocab.getConstant(OWL.ONEOF), var("m")),
                        new SPOPredicate(relationName, var("m"), vocab.getConstant(RDF.FIRST), var("i")),
                },//
                null
        );
    }
}
