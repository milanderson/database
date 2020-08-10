package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NEConstant;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlSubClassByIntersection2 extends Rule {

    private static final long serialVersionUID = -7423082674586471316L;

    public RuleOwlSubClassByIntersection2(String relationName, Vocabulary vocab) {

        super("owl_subClassByInters2",//
                new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.INTERSECTIONOF), var("y")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("c"), vocab.getConstant(OWL.INTERSECTIONOF), var("x")),
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(RDF.REST), var("y")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NEConstant(var("n"),vocab.getConstant(RDF.NIL)))
                }
        );
    }
}