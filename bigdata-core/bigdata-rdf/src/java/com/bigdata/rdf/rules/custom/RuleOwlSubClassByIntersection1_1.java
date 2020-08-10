package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlSubClassByIntersection1_1 extends Rule {

    private static final long serialVersionUID = -7423082674586471314L;

    public RuleOwlSubClassByIntersection1_1(String relationName, Vocabulary vocab) {

        super("owl_subClassByInters1.1",//
                new SPOPredicate(relationName, var("c"), vocab.getConstant(RDFS.SUBCLASSOF), var("x")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("c"), vocab.getConstant(OWL.INTERSECTIONOF), var("x")),
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(RDF.FIRST), var("y")),
                },//
                null
        );
    }
}