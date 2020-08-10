package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlTypeByIntersection2 extends Rule {

    private static final long serialVersionUID = -7423082674586471318L;

    public RuleOwlTypeByIntersection2(String relationName, Vocabulary vocab) {

        super("owl_typeByIntersect_2",//
                new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("n")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("b"), vocab.getConstant(RDF.FIRST), var("c")),
                        new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("c")),
                        new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("b")),
                        new SPOPredicate(relationName, var("n"), vocab.getConstant(OWL.INTERSECTIONOF), var("b")),
                },//
                null
        );
    }
}