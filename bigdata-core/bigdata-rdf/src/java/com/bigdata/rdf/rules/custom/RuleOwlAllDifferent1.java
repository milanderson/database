package com.bigdata.rdf.rules.custom;

import com.bigdata.bop.IConstraint;
import com.bigdata.bop.constraint.Constraint;
import com.bigdata.bop.constraint.NEConstant;
import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlAllDifferent1 extends Rule {

    private static final long serialVersionUID = -7423082674586471313L;

    public RuleOwlAllDifferent1(String relationName, Vocabulary vocab) {

        super("owl_allDiff1",//
                new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.DISTINCTMEMBERS), var("n")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.DISTINCTMEMBERS), var("m")),
                        new SPOPredicate(relationName, var("m"), vocab.getConstant(RDF.REST), var("n")),
                },//
                new IConstraint[] {
                        Constraint.wrap(new NEConstant(var("n"), vocab.getConstant(RDF.NIL)) )
                }// constraints
        );
    }
}