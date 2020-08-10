package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlTypeByIntersection1 extends Rule {

    private static final long serialVersionUID = -7423082674586471317L;

    public RuleOwlTypeByIntersection1(String relationName, Vocabulary vocab) {

        super("owl_typeByIntersect_1",//
                new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("b")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("b"), vocab.getConstant(RDF.FIRST), var("c")),
                        new SPOPredicate(relationName, var("b"), vocab.getConstant(RDF.REST), vocab.getConstant(RDF.NIL)),
                        new SPOPredicate(relationName, var("z"), vocab.getConstant(OWL.INTERSECTIONOF), var("b")),
                        new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("c")),
                },//
                null
        );
    }
}
