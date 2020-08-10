package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlSubClassByUnion2 extends Rule {

    private static final long serialVersionUID = -7423082674586471322L;

    public RuleOwlSubClassByUnion2(String relationName, Vocabulary vocab) {

        super("owl_subClassByUnion2",//
                new SPOPredicate(relationName, var("c"), vocab.getConstant(RDFS.SUBCLASSOF), var("a")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("a"), vocab.getConstant(OWL.UNIONOF), var("m")),
                        new SPOPredicate(relationName, var("m"), vocab.getConstant(RDF.FIRST), var("c")),
                },//
                null
        );
    }
}