package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NE;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;

public class RuleOwlSameAsByMaxCardinality extends Rule {

    private static final long serialVersionUID = -7423082674586471337L;

    public RuleOwlSameAsByMaxCardinality(String relationName, Vocabulary vocab) {

        super("owl_sameAsByMaxCard1",//
                new SPOPredicate(relationName, var("j"), vocab.getConstant(OWL.SAMEAS), var("k")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        //new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.MINCARDINALITY), ),
                        new SPOPredicate(relationName, var("i"), var("p"), var("j")),
                        new SPOPredicate(relationName, var("i"), var("p"), var("k")),
                },//
                new IConstraint[]{
                        Constraint.wrap(new NE(var("j"),var("k")))
                }
        );
    }
}