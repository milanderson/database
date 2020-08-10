package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NE;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlEquivalentClassBySubclass extends Rule {

    private static final long serialVersionUID = -7423082674586471306L;

    public RuleOwlEquivalentClassBySubclass(String relationName, Vocabulary vocab) {

        super("owl_EquivClassBySubClass",//
                new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.EQUIVALENTCLASS), var("y")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(RDFS.SUBCLASSOF), var("y")),
                        new SPOPredicate(relationName, var("y"), vocab.getConstant(RDFS.SUBCLASSOF), var("x")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NE(var("x"),var("y")))
                }// constraints
        );
    }
}