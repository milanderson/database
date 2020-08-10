package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NEConstant;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlSubClassByUnion1_1 extends Rule {

    private static final long serialVersionUID = -7423082674586471320L;

    public RuleOwlSubClassByUnion1_1(String relationName, Vocabulary vocab) {

        super("owl_subClassByUnion1_1",//
                new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.UNIONOF), var("y")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("c"), vocab.getConstant(OWL.UNIONOF), var("x")),
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(RDF.REST), var("y")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NEConstant(var("y"), vocab.getConstant(RDF.NIL)))
                }
        );
    }
}